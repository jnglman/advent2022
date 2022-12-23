package com.jnglman.adc2022.day1

class Solution

fun findMaxCalories(): Int? {
    return Solution::class.java.getResource("input")!!.readText()
        .split(System.lineSeparator() + System.lineSeparator())
        .filter { it.isNotBlank() }
        .maxOfOrNull { it -> it.lines().sumOf { it.toInt() } }
}

fun findMaxOfTopNCalories(): Int {
    return Solution::class.java.getResource("input")!!.readText()
        .split(System.lineSeparator() + System.lineSeparator())
        .filter { it.isNotBlank() }
        .map { it -> it.lines().sumOf { it.toInt() } }
        .sorted()
        .takeLast(3)
        .sum()
}
