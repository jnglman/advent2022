package com.jnglman.adc2022.day4

class Test

fun findFullOverlaps(): Int {
    return Test::class.java.getResource("input")!!.readText()
        .lines()
        .map { parseRangesLine(it) }
        .map { it.second.isFullOverlap(it.first) || it.first.isFullOverlap(it.second) }
        .count { it }
}

fun findOverlaps(): Int {
    return Test::class.java.getResource("input")!!.readText()
        .lines()
        .map { parseRangesLine(it) }
        .map { it.second.isOverlap(it.first) }
        .count { it }
}

fun IntRange.isFullOverlap(other: IntRange): Boolean {
    return this.first <= other.first && this.last >= other.last
}

fun IntRange.isOverlap(other: IntRange): Boolean {
    return this.first <= other.last && this.last >= other.first
}

private fun parseRangesLine(it: String): Pair<IntRange, IntRange> {
    val ranges = it.split(",")
        .map { rangeData -> textToRange(rangeData) }
    return ranges[0] to ranges[1]
}

private fun textToRange(rangeData: String): IntRange {
    val split = rangeData.split("-")
    return split[0].toInt()..split[1].toInt()
}