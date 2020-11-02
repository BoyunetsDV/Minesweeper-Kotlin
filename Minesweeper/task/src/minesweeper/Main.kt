package minesweeper

import java.util.*
import kotlin.random.Random

class Minesweeper {
    private val scanner = Scanner(System.`in`)
    var mineField: Array<Array<String>> = emptyArray()
    var width = 9
    var height = 9
    var minesCount = 0

    fun start() {
        getInitData()
        createMineField()
        fillMineField()
        displayMineField()
    }

    private fun getInitData() {
        print("How many mines do you want on the field? ")
        minesCount = scanner.nextInt()
    }

    private fun createMineField() {
        mineField = Array(height) { Array(width) { "." } }
    }

    private fun fillMineField() {
        fillWithMines()
        fillWithMinesCount()
    }

    private fun fillWithMines() {
        for (k in 0 until minesCount) {
            do {
                var isMinePlaced = false
                val x = Random.nextInt(0, width)
                val y = Random.nextInt(0, height)
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
                    continue
                }
                var nearMinesCount = 0
                if (i - 1 >= 0 && j - 1 >= 0 && mineField[i - 1][j - 1] == "X") nearMinesCount++
                if (i - 1 >= 0 && mineField[i - 1][j] == "X") nearMinesCount++
                if (i - 1 >= 0 && j + 1 < width && mineField[i - 1][j + 1] == "X") nearMinesCount++

                if (j - 1 >= 0 && mineField[i][j - 1] == "X") nearMinesCount++
                if (j + 1 < width && mineField[i][j + 1] == "X") nearMinesCount++

                if (i + 1 < height && j - 1 >= 0 && mineField[i + 1][j - 1] == "X") nearMinesCount++
                if (i + 1 < height && mineField[i + 1][j] == "X") nearMinesCount++
                if (i + 1 < height && j + 1 < width && mineField[i + 1][j + 1] == "X") nearMinesCount++
                mineField[i][j] = if (nearMinesCount > 0) {
                    nearMinesCount.toString()
                } else {
                    "."
                }
            }
        }
    }

    private fun displayMineField() {
        mineField.forEach { println(it.joinToString("")) }
    }
}

fun main() {
    val minesweeper = Minesweeper()
    minesweeper.start()
}