package minesweeper

import java.util.*
import kotlin.random.Random

class Minesweeper {
    var mineField: Array<Array<String>> = emptyArray()
    var visibleMineField: Array<Array<String>> = emptyArray()
    var isGameEnded: Boolean = false
    var isGameOver: Boolean = false
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
        displayVisibleMineField()
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
        val fields = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until HEIGHT) {
            for (j in 0 until WIDTH) {
                fields.add(Pair(i, j))
            }
        }
        for (k in 0 until minesCount) {
            if (fields.isEmpty()) {
                break
            }
            val minePoint = fields.random()
            fields.remove(minePoint)
            mineField[minePoint.first][minePoint.second] = "X"
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
                if (i - 1 >= 0 && j + 1 < WIDTH && mineField[i - 1][j + 1] == "X") nearMinesCount++

                if (j - 1 >= 0 && mineField[i][j - 1] == "X") nearMinesCount++
                if (j + 1 < WIDTH && mineField[i][j + 1] == "X") nearMinesCount++

                if (i + 1 < HEIGHT && j - 1 >= 0 && mineField[i + 1][j - 1] == "X") nearMinesCount++
                if (i + 1 < HEIGHT && mineField[i + 1][j] == "X") nearMinesCount++
                if (i + 1 < HEIGHT && j + 1 < WIDTH && mineField[i + 1][j + 1] == "X") nearMinesCount++
                mineField[i][j] = if (nearMinesCount > 0) {
                    nearMinesCount.toString()
                } else {
                    "."
                }
            }
        }
    }

    private fun makeTurn() {
        while (true) {
            print("Set/unset mines marks or claim a cell as free: ")
            val x = scanner.nextInt() - 1
            val y = scanner.nextInt() - 1
            val turnType = scanner.next()

            when {
                mineField[y][x] == "X" && turnType == "free" -> addAllMinesToDisplay()
                visibleMineField[y][x][0].isDigit() && turnType == "free" -> {
                    println("There is a number here!")
                    continue
                }
                mineField[y][x][0].isDigit() && turnType == "free" -> visibleMineField[y][x] = mineField[y][x]
                visibleMineField[y][x] == "/" && turnType == "free" -> {
                    println("There is a / here!")
                    continue
                }
                visibleMineField[y][x] == "." && turnType == "free" -> makeCellsVisible(y, x)
                visibleMineField[y][x] == "*" && turnType == "mine" -> visibleMineField[y][x] = "."
                visibleMineField[y][x] == "." && turnType == "mine" -> visibleMineField[y][x] = "*"
            }
            break
        }
    }

    private fun addAllMinesToDisplay() {
        for (i in mineField.indices) {
            for (j in mineField[i].indices) {
                if (mineField[i][j] == "X") {
                    visibleMineField[i][j] = "X"
                }
            }
        }
        isGameOver = true
    }

    private fun makeCellsVisible(x: Int, y: Int) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT || visibleMineField[x][y] !in ".*") {
            return
        }
        if (mineField[x][y][0].isDigit()) {
            visibleMineField[x][y] = mineField[x][y]
            return
        }
        visibleMineField[x][y] = "/"
        makeCellsVisible(x - 1, y - 1)
        makeCellsVisible(x - 1, y)
        makeCellsVisible(x - 1, y + 1)
        makeCellsVisible(x, y - 1)
        makeCellsVisible(x, y + 1)
        makeCellsVisible(x + 1, y - 1)
        makeCellsVisible(x + 1, y)
        makeCellsVisible(x + 1, y + 1)
    }

    private fun checkIfGameIsEnded() {
        if (isGameOver) {
            isGameEnded = true
            return
        }
        var unOpened = 0
        for (i in visibleMineField.indices) {
            for (j in visibleMineField[i].indices) {
                if (visibleMineField[i][j] == "*" || visibleMineField[i][j] == ".") {
                    unOpened++
                }
            }
        }
        isGameEnded = unOpened == minesCount
    }

    private fun displayVisibleMineField() {
        var rowNumber = 1
        println(" |123456789|")
        println("-|---------|")
        visibleMineField.forEach { println("${rowNumber++}|${it.joinToString("")}|") }
        println("-|---------|")
    }

    private fun displayResult() {
        if (isGameOver) {
            println("You stepped on a mine and failed!")
        } else {
            println("Congratulations! You found all the mines!")
        }
    }
}

fun main() {
    val minesweeper = Minesweeper()
    minesweeper.start()
}