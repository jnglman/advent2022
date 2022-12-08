package com.jnglman.adc2022.day5

fun moveCargo(): String {
    """
        [V]     [B]                     [C]
        [C]     [N] [G]         [W]     [P]
        [W]     [C] [Q] [S]     [C]     [M]
        [L]     [W] [B] [Z]     [F] [S] [V]
        [R]     [G] [H] [F] [P] [V] [M] [T]
        [M] [L] [R] [D] [L] [N] [P] [D] [W]
        [F] [Q] [S] [C] [G] [G] [Z] [P] [N]
        [Q] [D] [P] [L] [V] [D] [D] [C] [Z]
         1   2   3   4   5   6   7   8   9 
    """.trimIndent()

    val cargoMap = mapOf(
        1 to mutableListOf('Q', 'F', 'M', 'R', 'L', 'W', 'C', 'V'),
        2 to mutableListOf('D', 'Q', 'L'),
        3 to mutableListOf('P', 'S', 'R', 'G', 'W', 'C', 'N', 'B'),
        4 to mutableListOf('L', 'C', 'D', 'H', 'B', 'Q', 'G'),
        5 to mutableListOf('V', 'G', 'L', 'F', 'Z', 'S'),
        6 to mutableListOf('D', 'G', 'N', 'P'),
        7 to mutableListOf('D', 'Z', 'P', 'V', 'F', 'C', 'W'),
        8 to mutableListOf('C', 'P', 'D', 'M', 'S'),
        9 to mutableListOf('Z', 'N', 'W', 'T', 'V', 'M', 'P', 'C')
    )

    val moves = Move::class.java.getResource("input")!!.readText()
        .split(System.lineSeparator())
        .map { Move.parse(it) }
    moves.forEachIndexed { index, move ->
        try {
            repeat(move.count) { cargoMap[move.to]!!.add(cargoMap[move.from]!!.removeLast()) }
        } catch (e: NoSuchElementException) {
            println("Failed at move ${index + 1}, $move")
            throw e
        }
    }
    return cargoMap.values.map { it.last() }.joinToString(separator = "")
}

data class Move(val count: Int, val from: Int, val to: Int) {

    companion object Parser {
        private val regex = "\\d+".toRegex()
        fun parse(description: String): Move {
            val placeholders = regex.findAll(description)
                .map { it.value.toInt() }
                .toList()
            return Move(placeholders[0], placeholders[1], placeholders[2])
        }
    }

}