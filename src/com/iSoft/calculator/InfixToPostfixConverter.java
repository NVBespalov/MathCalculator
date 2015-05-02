package com.iSoft.calculator;

import java.util.Stack;

public class InfixToPostfixConverter extends Stack implements IConverter {
    /**
     * Algorithm to use for conversion
     */
    private IAlgorithm algorithm;

    public InfixToPostfixConverter(IAlgorithm convertingAlgorithm) {
        algorithm = convertingAlgorithm;
    }

    /**
     * Convert infix string to postfix
     */
    @Override
    public String convert(String infix) {
        return algorithm.execute(infix);
    }


}
