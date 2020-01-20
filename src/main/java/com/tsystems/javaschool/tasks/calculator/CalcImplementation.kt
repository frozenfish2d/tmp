package com.tsystems.javaschool.tasks.calculator


class CalcImplementation {

    private fun getPriority(char: Char): Int {
        return when (char) {
            '*', '/' -> 3
            '+', '-' -> 2
            '(' -> 1
            ')' -> -1
            else -> 0
        }
    }

    fun expressionToRPN(expr: String): String? {
        val stack = StackImplementation()
        val rpnString = StringBuilder()

        var priority: Int
        var openBracketsCount = 0
        for (i in expr.indices) {
            priority = getPriority(expr[i])
            when (priority) {
                0 -> {
                    rpnString.append(expr[i])
                }
                1 -> {
                    stack.push(expr[i])
                    openBracketsCount++
                }
                2, 3 -> {
                    rpnString.append(' ')
                    while (!stack.isEmpty()) {
                        if (getPriority(stack.peek() as Char) >= priority) {
                            rpnString.append(stack.pop() as Char)
                        } else break
                    }
                    stack.push(expr[i])
                }
                -1 -> {
                    if (openBracketsCount > 1) {
                        return null
                    }
                    rpnString.append(' ')
                    while (!stack.isEmpty() && getPriority(stack.peek() as Char) != 1) {
                        rpnString.append(stack.pop() as Char)
                    }
                    stack.pop()
                    openBracketsCount = 0
                }
                else -> return null
            }
        }
        while (!stack.isEmpty()) {
            rpnString.append(stack.pop())
        }
        return rpnString.toString()
    }

    fun RPNToValue(rpnString: String?): String? {

        if (rpnString == null || rpnString == "") {
            return null
        }
        var operand = StringBuilder()
        val stack = StackImplementation()

        var i = 0
        while (i < rpnString.length) {
            if (rpnString[i] == ' ') {
                i++
                continue
            }
            if (getPriority(rpnString[i]) == 0) {
                while (rpnString[i] != ' ' && getPriority(rpnString[i]) == 0) {
                    operand.append(rpnString[i++])
                    if (i == rpnString.length) break
                }
                stack.push(operand.toString().toDouble())
                operand = StringBuilder()
            }
            if (getPriority(rpnString[i]) > 1) {
                val second = stack.pop() as Double
                if (stack.isEmpty() || second == stack.peek()) return null
                if (second == 0.0 && rpnString[i] == '/') return null
                val first = stack.pop() as Double

                when (rpnString[i]) {
                    '+' -> stack.push(first + second)
                    '-' -> stack.push(first - second)
                    '*' -> stack.push(first * second)
                    '/' -> stack.push(first / second)

                }
            }
            i++
        }

        val answer = stack.pop() as Double
        return if (answer % 1 == 0.0) {
            answer.toInt().toString()
        } else {
            answer.toString()
        }
    }

}
