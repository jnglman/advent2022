package com.jnglman.adc2022.day5

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
