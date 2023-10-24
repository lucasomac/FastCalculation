package br.edu.scl.ifsp.sdm.fastcalculation.game

import kotlin.math.abs
import kotlin.random.Random

enum class Operator { ADD, SUB, MUL, DIV }

class CalculationGame(private val rounds: Int) {
    companion object {
        private const val INITIAL_VALUE = 1
        private const val FINAL_VALUE = 9
    }

    private var currentRound: Int = 0
    private val random = Random(System.currentTimeMillis())

    fun nextRound(): Round? {
        return if (currentRound < rounds) {
            var firstValue = random.nextInt(INITIAL_VALUE, FINAL_VALUE + 5)
            var secondValue = random.nextInt(INITIAL_VALUE, FINAL_VALUE + 5)

            while (abs(firstValue - secondValue) < 2) {
                secondValue = random.nextInt(INITIAL_VALUE, FINAL_VALUE + 5)
            }

            // Garantindo a distância mínima para ter alternativas
            val x = secondValue
            secondValue = secondValue.coerceAtMost(firstValue)
            firstValue = x.coerceAtLeast(firstValue)

            val operator = Operator.entries[random.nextInt(4)]

            val answer: Int
            val question: String
            when (operator) {
                Operator.ADD -> {
                    answer = firstValue + secondValue
                    question = "$firstValue + $secondValue"
                }

                Operator.SUB -> {
                    answer = firstValue - secondValue
                    question = "$firstValue - $secondValue"
                }

                Operator.MUL -> {
                    answer = firstValue * secondValue
                    question = "$firstValue * $secondValue"
                }

                Operator.DIV -> {
                    answer = firstValue / secondValue
                    question = "$firstValue / $secondValue"
                }
            }
            currentRound++
            val altList = genAlternatives(firstValue, secondValue, answer)
            Round(question, answer, altList[0], altList[1], altList[2], currentRound)
        } else null
    }

    private fun genAlternatives(op1: Int, op2: Int, answer: Int): List<Int> {
        val operationSet = mutableSetOf(op1 - op2, op1 + op2, op1 * op2, op1 / op2)
        val lowest = operationSet.min()
        val biggest = operationSet.max()

        val alternativeSet = mutableSetOf(answer)
        while (alternativeSet.size < 3) {
            alternativeSet.add(random.nextInt(lowest, biggest + 1))
        }
        return alternativeSet.toList().shuffled()
    }

    data class Round(
        val question: String,
        val answer: Int,
        val alt1: Int,
        val alt2: Int,
        val alt3: Int,
        val round: Int
    )
}