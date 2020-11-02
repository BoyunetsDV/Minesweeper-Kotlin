package minesweeper

import java.util.*
import kotlin.random.Random

class Minesweeper {
    var mineField: Array<Array<String>> = emptyArray()
    var visibleMineField: Array<Array<String>> = emptyArray()
    var isGameEnded: Boolean = false
    var minesCount = 0

    companion object {
        private val scanner = Scanner(System.`in`)
        const val WIDTH = 9
        const val HEIGHT = 9
    }

    fun start() {
        getInitData()
        createMineField()
        fillMineFields()
        while (!isGameEnded) {
            displayVisibleMineField()
            makeTurn()
            checkIfGameIsEnded()
        }
        displayResult()
    }

    private fun getInitData() {
        print("How many mines do you want on the field? ")
        minesCount = scanner.nextInt()
    }

    private fun createMineField() {
        mineField = Array(HEIGHT) { Array(WIDTH) { "." } }
        visibleMineField = Array(HEIGHT) { Array(WIDTH) { "." } }
    }

    private fun fillMineFields() {
        fillWithMines()
        fillWithMinesCount()
    }

    private fun fillWithMines() {
        for (k in 0 until minesCount) {
            do {
                var isMinePlaced = false
                val x = Random.nextInt(0, WIDTH)
                val y = Random.nextInt(0, HEIGHT)
                if (mineField[y][x] == ".") {
                    mineField[y][x] = "X"
                    isMinePlaced = true
                }
            } while (!isMinePlaced)
        }
    }

    private fun fillWithMinesCount() {
        for (i in mineField.indices) {
            for (j in mineField[i].indices) {
                if (mineField[i][j] == "X") {
                    visibleMineField[i][j] = "."
                    continue
                }
                var nearMinesCount = 0
                if (i - 1 >= 0 && j - 1 >= 0 && mineField[i - 1][j - 1] == "X") nearMinesCount++
                if (i - 1 >= 0 && mineField[i - 1][j] == "X") nearMinesCount++
                if (i - 1 >= 0 && j + 1 < WIDTH && mineField[i - 1][j + 1] == "X") nearMinesCount++

                if (j - 1 >= 0 && mineField[i][j - 1] == "X") nearMinesCount++
                if (j + 1 < WIDTH && mineField[i][j + 1] == "X") nearMinesCount++

                if (i + 1 < HEIGHT && j - 1 >= 0 && mineField[i + 1][j - 1] == "X") nearMinesCount++
                if (i + 1 < HEIGHT && mineField[i + 1][j] == "X") nearMinesCount++
                if (i + 1 < HEIGHT && j + 1 < WIDTH && mineField[i + 1][j + 1] == "X") nearMinesCount++
                if (nearMinesCount > 0) {
                    mineField[i][j] = nearMinesCount.toString()
                    visibleMineField[i][j] = nearMinesCount.toString()
                } else {
                    mineField[i][j] = "."
                    visibleMineField[i][j] = "."
                }
            }
        }
    }

    private fun makeTurn() {
        while (true) {
            print("Set/delete mines marks (x and y coordinates): ")
            val y = scanner.nextInt() - 1
            val x = scanner.nextInt() - 1
            if (visibleMineField[x][y] != "." && visibleMineField[x][y] != "*") {
                println("There is a number here!")
            } else if (visibleMineField[x][y] == ".") {
                visibleMineField[x][y] = "*"
                break
            } else {
                visibleMineField[x][y] = "."
                break
            }
        }
    }

    private fun checkIfGameIsEnded() {
        var marksCount = 0
        var minesMarked = 0
        for (i in visibleMineField.indices) {
            for (j in visibleMineField[i].indices) {
                if (visibleMineField[i][j] == "*" && mineField[i][j] == "X") {
                    minesMarked++
                    marksCount++
                } else if (visibleMineField[i][j] == "*") {
                    marksCount++
                }
            }
        }
        isGameEnded = marksCount == minesMarked && marksCount == minesCount
    }

    private fun displayVisibleMineField() {
        var rowNumber = 1
        println(" |123456789|")
        println("-|---------|")
        visibleMineField.forEach { println("${rowNumber++}|${it.joinToString("")}|") }
        println("-|---------|")
    }

    private fun displayResult() {
        if (isGameEnded) {
            println("Congratulations! You found all the mines!")
        }
    }
}

fun main() {
    val minesweeper = Minesweeper()
    minesweeper.start()
}