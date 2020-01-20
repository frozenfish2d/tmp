package com.tsystems.javaschool.tasks.calculator;

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

    public String evaluate(String statement) {
        if (statement == null || statement.isEmpty() || statement.contains(",") || statement.contains("..")) {
            return null;
        }
        CalcImplementation calcImp = new CalcImplementation();
        return calcImp.RPNToValue(calcImp.expressionToRPN(statement.replace(" ", "")));
    }

}
