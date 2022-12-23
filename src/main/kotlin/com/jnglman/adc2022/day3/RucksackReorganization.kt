package com.jnglman.adc2022.day3

class Rucksack(content: String) {

    private val compartments: Pair<CharSequence, CharSequence>

    init {
        val half = content.length / 2
        compartments = content.subSequence(0, half) to content.subSequence(half, content.length)
    }

    fun content(): Set<Char> {
        return compartments.first.toSet() + compartments.second.toSet()
    }

    fun calculatePriority(): Int {
        val intersect = compartments.first.toSet().intersect(compartments.second.toSet())
        if (intersect.size != 1) {
            throw IllegalStateException("Wrong conditions")
        }
        return Priorities.chars.indexOf(intersect.first())
    }

}

fun sumPriorities(): Int {
        return Rucksack::class.java.getResource("input")!!.readText()
            .lines()
            .map { Rucksack(it) }
            .sumOf { it.calculatePriority() }
}

fun sumPrioritiesByGroupOf3(): Int {
    return Rucksack::class.java.getResource("input")!!.readText()
        .lines()
        .map { Rucksack(it) }
        .chunked(3)
        .map { it.map { r -> r.content() }.reduce { chars2, chars -> chars.intersect(chars2) } }
        .sumOf { Priorities.chars.indexOf(it.first()) }
}

object Priorities {
    val chars = generateSequence('a'.dec()) { if (it < 'z') it.inc() else null } +
            generateSequence('A') { if (it < 'Z') it.inc() else null }
}