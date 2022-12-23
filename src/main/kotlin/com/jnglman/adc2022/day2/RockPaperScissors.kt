package com.jnglman.adc2022.day2

fun calculateScore(): Int {
    return Figure::class.java.getResource("input")!!.readText()
        .lines()
        .sumOf {
            val figures = it.split(" ")
            if (figures.size != 2) {
                throw IllegalArgumentException("Wrong input")
            }
            val opponentFigure = parseFigure1(figures[0].first())
            val myFigure = parseFigure1(figures[1].first())
            myFigure.showdown(opponentFigure).points + myFigure.defaultPoints()
        }
}

fun calculateRiggedScore(): Int {
    return Figure::class.java.getResource("input")!!.readText()
        .lines()
        .sumOf {
            val figures = it.split(" ")
            if (figures.size != 2) {
                throw IllegalArgumentException("Wrong input")
            }
            val opponentFigure = parseFigure2(figures[0].first())
            val myFigure = opponentFigure.rigged(parseResult(figures[1].first()))
            myFigure.showdown(opponentFigure).points + myFigure.defaultPoints()
        }
}

sealed class Result(val points: Int)
object Win : Result(6)
object Draw : Result(3)
object Lose : Result(0)

sealed interface Figure {
    fun defaultPoints(): Int
    fun showdown(other: Figure): Result
    fun rigged(result: Result): Figure
}

object Rock : Figure {
    override fun defaultPoints() = 1

    override fun showdown(other: Figure): Result {
        return when (other) {
            is Rock -> Draw
            is Paper -> Lose
            is Scissors -> Win
        }
    }

    override fun rigged(result: Result): Figure {
        return when(result) {
            is Win -> Paper
            is Draw -> Rock
            is Lose -> Scissors
        }
    }
}

object Paper : Figure {
    override fun defaultPoints() = 2

    override fun showdown(other: Figure): Result {
        return when (other) {
            is Rock -> Win
            is Paper -> Draw
            is Scissors -> Lose
        }
    }

    override fun rigged(result: Result): Figure {
        return when(result) {
            is Win -> Scissors
            is Draw -> Paper
            is Lose -> Rock
        }
    }
}

object Scissors : Figure {
    override fun defaultPoints() = 3

    override fun showdown(other: Figure): Result {
        return when (other) {
            is Rock -> Lose
            is Paper -> Win
            is Scissors -> Draw
        }
    }

    override fun rigged(result: Result): Figure {
        return when(result) {
            is Win -> Rock
            is Draw -> Scissors
            is Lose -> Paper
        }
    }
}

fun parseFigure1(char: Char): Figure {
    return when (char) {
        'X', 'A' -> Rock
        'Y', 'B' -> Paper
        'Z', 'C' -> Scissors
        else -> throw IllegalArgumentException("Unknown figure character")
    }
}

fun parseFigure2(char: Char): Figure {
    return when (char) {
        'A' -> Rock
        'B' -> Paper
        'C' -> Scissors
        else -> throw IllegalArgumentException("Unknown figure character")
    }
}

fun parseResult(char: Char): Result {
    return when (char) {
        'X' -> Lose
        'Y' -> Draw
        'Z' -> Win
        else -> throw IllegalArgumentException("Unknown result character")
    }
}