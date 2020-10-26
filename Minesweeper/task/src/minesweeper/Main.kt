package minesweeper

import java.util.*
import kotlin.random.Random

val scanner = Scanner(System.`in`)
var mineField: Array<CharArray> = emptyArray()
var width = 0
var height = 0
var minesCount = 0

fun main() {
    getInitData()
    createMineField()
    fillMineField()
    displayMineField()
}

fun getInitData() {
    width = 9
    height = 9
    print("How many mines do you want on the field? ")
    minesCount = scanner.nextInt()
}

fun createMineField() {
    mineField = Array(height){ CharArray(width) { '.' }}
}

fun fillMineField(){
    for (k in 0 until minesCount) {
        do {
            var isMinePlaced = false
            val x = Random.nextInt(0, width)
            val y = Random.nextInt(0, height)
            if (mineField[y][x] == '.') {
                mineField[y][x] = 'X'
                isMinePlaced = true
            }
        } while (!isMinePlaced)
    }
}

fun displayMineField() {
    mineField.forEach { println(it.joinToString("")) }
}