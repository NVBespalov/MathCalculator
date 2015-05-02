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
            if (tokenIsNumber(currentToken)) addTokenToOutput(currentToken);

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

    /**
     * Indicates whatever token is number
     *
     * @param token Token to check
     * @return true if token is numeric false if not
     */
    public static boolean tokenIsNumber(String token) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Add given token to postfix string builder chain
     * @param token Token to add
     */
    private void addTokenToOutput(String token) {
        postfixStringBuilder.append(" ").append(token);
    }
}