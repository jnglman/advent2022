package com.jnglman.adc2022.day7

sealed class FileSystemContent(val name: String)
sealed class AbstractDirectory(
    name: String,
    val directories: MutableList<AbstractDirectory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf(),
    val parent: AbstractDirectory?
) : FileSystemContent(name) {
    fun getRoot(): AbstractDirectory {
        return when (this) {
            is Root -> this
            is Directory -> directoryRoot(this)
        }
    }

    fun toChild(name: String): AbstractDirectory {
        return directories.first { it.name == name }
    }

    fun size(): Long {
        return files.sumOf { it.size }
    }

    private tailrec fun directoryRoot(directory: Directory): AbstractDirectory {
        return when (val nonNullParent = directory.parent!!) {
            is Root -> nonNullParent
            is Directory -> directoryRoot(nonNullParent)
        }
    }
}

class Root : AbstractDirectory("/", parent = null)
class Directory(name: String, parent: AbstractDirectory) : AbstractDirectory(name, parent = parent)
class File(name: String, val size: Long) : FileSystemContent(name) {
    override fun toString(): String {
        return "File(name=$name, size=$size)"
    }
}

fun sumSizeOfLessThan100000(): Long {
    return getSizes().values.filter { it < 100000 }.sum()
}

fun freeSpace(): Long {
    val sizes = getSizes()
    val totalSpace = 70000000
    val requiredSpace = 30000000
    val unusedSpace = totalSpace - sizes.asIterable().first { it.key is Root }.value
    val spaceToFree = requiredSpace - unusedSpace
    return sizes.values.filter { it > spaceToFree }.min()
}

fun getSizes(): Map<AbstractDirectory, Long> {
    return mutableMapOf<AbstractDirectory, Long>().also {
        directoriesSize(FileSystemContent::class.java.getResource("input")!!.readText()
            .lines()
            .fold(Root() as AbstractDirectory) { acc, line -> parseLine(acc, line) }.getRoot(), it)
    }
}

fun parseLine(currentDir: AbstractDirectory, string: String): AbstractDirectory {
    val directoryPattern = "dir \\w+".toRegex()
    val filePattern = "^\\d+ \\S+".toRegex()
    val commandPattern = "^\\$.*".toRegex()
    return when {
        filePattern.matches(string) -> {
            val sizeToName = string.split(" ")
            currentDir.files.add(File(sizeToName[1], sizeToName[0].toLong()))
            return currentDir
        }

        directoryPattern.matches(string) -> {
            currentDir.directories.add(Directory(string.removePrefix("dir "), currentDir))
            return currentDir
        }

        commandPattern.matches(string) -> parseCommand(string.removePrefix("$ "), currentDir)
        else -> throw IllegalArgumentException("Unsupported input: $string")
    }
}

fun parseCommand(command: String, currentDir: AbstractDirectory): AbstractDirectory {
    val lsCommandPattern = "^ls".toRegex()
    val cdCommandPattern = "^cd.*".toRegex()
    return when {
        lsCommandPattern.matches(command) -> currentDir
        cdCommandPattern.matches(command) -> {
            when (val dirNameToFollow = command.removePrefix("cd ")) {
                ".." -> {
                    return currentDir.parent
                        ?: throw IllegalArgumentException("Invoked on directory without parent: $currentDir")
                }

                "/" -> {
                    return currentDir.getRoot()
                }

                else -> {
                    return currentDir.toChild(dirNameToFollow)
                }
            }
        }

        else -> throw IllegalArgumentException("Unsupported command: $command")
    }
}

fun directoriesSize(directory: AbstractDirectory = Root(),
                    map: MutableMap<AbstractDirectory, Long>): Long {
    return if (map.containsKey(directory)) {
        map[directory]!!
    } else if (directory.directories.isEmpty()) {
        map[directory] = directory.size()
        directory.size()
    } else {
        map[directory] = directory.size() + directory.directories.sumOf { directoriesSize(it, map) }
        map[directory]!!
    }
}