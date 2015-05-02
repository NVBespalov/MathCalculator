package com.iSoft.calculator;

import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Algorithm to convert string from infix to postfix notation
 */
public class ShuntingYardAlgorithm {

    /**
     * Stack to hold operators
     */
    protected Stack<String> operatorStack;

    /**
     * Result postfix string builder chain
     */
    private StringBuilder postfixStringBuilder;

    /**
     * Parse string from infix to postfix notation
     *
     * @param stringToConvert String in infixNotation
     * @return String in postfixNotation
     */
    public String execute(String stringToConvert) {

        String currentToken;
        operatorStack = new Stack<>();
        StringTokenizer parser = new StringTokenizer(stringToConvert, "+-*/^() ", true);
        postfixStringBuilder = new StringBuilder(stringToConvert.length());
        while (parser.hasMoreTokens()) {
            currentToken = parser.nextToken();
            if (tokenIsSpace(currentToken)) continue;


        }
        return postfixStringBuilder.toString();
    }
    /**
     * Indicates whatever token is space
     *
     * @param token Infix string token
     * @return true if token is space false if not
     */
    private boolean tokenIsSpace(String token) {
        return token.charAt(0) == ' ';
    }

}