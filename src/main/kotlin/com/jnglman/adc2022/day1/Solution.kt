package com.jnglman.adc2022.day1

class Solution

fun findMaxCalories(): Int? {
    return Solution::class.java.getResource("input")!!.readText()
        .split(System.lineSeparator() + System.lineSeparator())
        .filter { it.isNotBlank() }
        .maxOfOrNull { it -> it.split(System.lineSeparator()).sumOf { it.toInt() } }
}

fun findMaxOfTopNCalories(): Int {
    return Solution::class.java.getResource("input")!!.readText()
        .split(System.lineSeparator() + System.lineSeparator())
        .filter { it.isNotBlank() }
        .map { it -> it.split(System.lineSeparator()).sumOf { it.toInt() } }
        .sorted()
        .takeLast(3)
        .sum()
}
