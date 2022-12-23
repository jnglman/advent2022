package com.jnglman.adc2022.day6

class Test

const val startPacketLength: Int = 4
const val startMessageLength: Int = 14

fun scanStartPacket(): Int {
    val signal = Test::class.java.getResource("input")!!.readText()
    return scanSignal(signal, 0, startPacketLength)
}

fun scanStartMessage(): Int {
    val signal = Test::class.java.getResource("input")!!.readText()
    return scanSignal(signal, 0, startMessageLength)
}

tailrec fun scanSignal(signal: String, index: Int, length: Int): Int {
    if (signal.length < length) {
        return -1
    } else if (signal.take(length).chars().distinct().count() == length.toLong()) {
        return index + length
    }
    return scanSignal(signal.drop(1), index.inc(), length)
}
