package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    public static String expressionToRPN(String expr) {

        StringBuilder rpnString = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        int openBracketsCount = 0;
        for (int i = 0; i < expr.length(); i++) {
            priority = getPriority(expr.charAt(i));
            if (priority == 0) {
                rpnString.append(expr.charAt(i));
            }
            if (priority == 1) {
                stack.push(expr.charAt(i));
                openBracketsCount++;
            }
            if (priority > 1) {
                rpnString.append(' ');
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        rpnString.append(stack.pop());
                    } else break;
                }
                stack.push(expr.charAt(i));
            }

            if (priority == -1) {
                if (openBracketsCount > 1) {
                    return null;
                }
                rpnString.append(' ');
                try {
                    while (getPriority(stack.peek()) != 1) {
                        rpnString.append(stack.pop());
                    }
                    stack.pop();
                    openBracketsCount = 0;
                } catch (EmptyStackException e) {
                    return null;
                }
            }
        }
        while (!stack.empty()) {
            rpnString.append(stack.pop());
        }

        return rpnString.toString();
    }

    public static String RPNToValue(String rpnString) {
        if (rpnString == null || rpnString.equals("")) {
            return null;
        }
        StringBuilder operand = new StringBuilder("");
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpnString.length(); i++) {

            if (rpnString.charAt(i) == ' ') continue;

            if (getPriority(rpnString.charAt(i)) == 0) {
                while (rpnString.charAt(i) != ' ' && getPriority(rpnString.charAt(i)) == 0) {
                    operand.append(rpnString.charAt(i++));
                    if (i == rpnString.length())
                        break;
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder("");
            }

            if (getPriority(rpnString.charAt(i)) > 1) {


                double second = stack.pop();
                if (stack.empty() || second == stack.peek())
                    return null;
                if (second == 0 && rpnString.charAt(i) == '/') return null;
                double first = stack.pop();
                if (rpnString.charAt(i) == '+') stack.push(first + second);
                if (rpnString.charAt(i) == '-') stack.push(first - second);
                if (rpnString.charAt(i) == '*') stack.push(first * second);
                if (rpnString.charAt(i) == '/') stack.push(first / second);

            }

        }
        double answer = stack.pop();
        if (answer % 1 == 0) {
            return Integer.toString((int) answer);
        } else {
            return Double.toString(answer);
        }

    }


    private static int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 3;
        } else if (token == '+' || token == '-') {
            return 2;
        } else if (token == '(') {
            return 1;
        } else if (token == ')') {
            return -1;
        } else return 0;

    }

    public String evaluate(String statement) {
        if (statement == null || statement.isEmpty() || statement.contains(",") || statement.contains("..")) {
            return null;
        }
        statement = statement.replace(" ", "");


        return RPNToValue(expressionToRPN(statement));
    }

}
