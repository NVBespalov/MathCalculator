package com.iSoft.calculator;

import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Algorithm to convert string from infix to postfix notation
 */
public class ShuntingYardAlgorithm implements IAlgorithm{

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
            if (tokenIsOperator(currentToken)) handleTokenIsOperatorCase(currentToken);
            if (tokenIsLeftParenthesis(currentToken)) pushTokenToOperatorStack(currentToken);
            if (tokenIsRightParenthesis(currentToken)) handleOperatorIsRightParenthesisCase();
        }
        // @TODO If the operator token on the top of the stack is a parenthesis, then there are mismatched parentheses.
        popOperatorStackToOutput();
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

    /**
     * Indicates whatever operator token is function
     *
     * @param token Token to check
     * @return true if operator token is function false if not
     */
    private boolean tokenIsOperator(String token) {
        char character = token.charAt(0);
        switch (character) {
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case '/':
                return true;
            case '^':
                return true;
            default:
                return false;
        }

    }

    /**
     * Handler for case when token is operator
     * @param firstOperatorToken The O1 operator
     */
    private void handleTokenIsOperatorCase(String firstOperatorToken) {

        if (!operatorStack.empty()) {
            String secondOperator = operatorStack.peek();
            while (!operatorStack.empty()
                    &&
                    (operatorTokenIsLeftAssociative(firstOperatorToken) && operatorTokenPrecedence(firstOperatorToken) <= operatorTokenPrecedence(secondOperator))
                    || (!operatorTokenIsLeftAssociative(firstOperatorToken) && operatorTokenPrecedence(firstOperatorToken) < operatorTokenPrecedence(secondOperator))) {
                addTokenToOutput(operatorStack.pop());

                if (operatorStack.empty()) break;
                secondOperator = operatorStack.peek();
            }
        }

        pushTokenToOperatorStack(firstOperatorToken);
    }

    /**
     * Indicates whatever operator token is left associative
     * @param operatorToken Operator token
     * @return true if operator token is left associative false if not
     */
    private boolean operatorTokenIsLeftAssociative(String operatorToken) {
        switch (operatorToken) {
            case "^":
                return false;
            case "*":
                return true;
            case "/":
                return true;
            case "+":
                return true;
            case "-":
                return true;
            default:
                return false;
        }
    }

    /**
     * Returns operator token precedence value
     *
     * @param operatorToken Operator token Precedence
     * @return numeric value of operator precedence
     */
    private int operatorTokenPrecedence(String operatorToken) {
        switch (operatorToken) {
            case "^":
                return 4;
            case "*":
                return 3;
            case "/":
                return 3;
            case "+":
                return 2;
            case "-":
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Push token to operator stack
     * @param token Token to push
     */
    private void pushTokenToOperatorStack(String token) {
        operatorStack.push(token);
    }

    /**
     * Indicates whatever operator token is right parenthesis
     *
     * @param operatorToken Operator token
     * @return true if operator token is right parenthesis false if not
     */
    private boolean tokenIsRightParenthesis(String operatorToken) {
        return Objects.equals(operatorToken, ")");
    }

    /**
     * Indicates whatever operator token is left parenthesis
     *
     * @param operatorToken Operator token
     * @return true if operator token is left parenthesis false if not
     */
    private boolean tokenIsLeftParenthesis(String operatorToken) {
        return Objects.equals(operatorToken, "(");
    }

    /**
     * Handler for case when operator token is right Parenthesis
     */
    private void handleOperatorIsRightParenthesisCase() {
        String lastTokenInOperatorStack = operatorStack.peek();
        while (!operatorStack.empty() && !tokenIsLeftParenthesis(lastTokenInOperatorStack)) {
            addTokenToOutput(operatorStack.pop());
            lastTokenInOperatorStack = operatorStack.peek();
        }
        operatorStack.pop();
        lastTokenInOperatorStack = operatorStack.peek();
        if (tokenIsFunction(lastTokenInOperatorStack)) addTokenToOutput(operatorStack.pop());
        // @TODO If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.
    }

    /**
     * Indicates whatever operator token is function
     *
     * @param operatorToken Operator token
     * @return true if operator token is function false if not
     */
    private boolean tokenIsFunction(String operatorToken) {
        return Objects.equals(operatorToken, "sin") || Objects.equals(operatorToken, "max");
    }

    /**
     * Pop out all operators to postfix string builder chain
     */
    private void popOperatorStackToOutput() {
        while (!operatorStack.isEmpty()) {
            addTokenToOutput(operatorStack.pop());
        }
    }
}