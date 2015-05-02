package com.iSoft.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostfixConverter extends Stack implements IConverter {

    private boolean tokenIsOperator(String token) {
        char character = token.charAt(0);
        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^'
                || character == '(' || tokenIsClosingParenthesis(token);

    }

    private boolean tokenIsNotSpace(String token) {

        return !(token.charAt(0) == ' ');

    }

    private boolean lowerPrecedence(String leftOperator, String rightOperator) {

        switch (leftOperator.charAt(0)) {

            case '+':
            case '-':
                return !(rightOperator.charAt(0) == '+' || rightOperator.charAt(0) == '-');

            case '*':
            case '/':
                return rightOperator.charAt(0) == '^' || rightOperator.charAt(0) == '(';

            case '^':
                return rightOperator.charAt(0) == '(';

            case '(':
                return true;

            default:
                return false;
        }

    }

    @Override
    public String convert(String infix) {

        Stack <String> operatorStack = new Stack <> ();

        StringTokenizer parser = new StringTokenizer(infix, "+-*/^() ", true);

        StringBuilder postfixStringBuilder = new StringBuilder(infix.length());

        while (parser.hasMoreTokens()) {

            String currentToken = parser.nextToken();

            if (tokenHasOneElement(currentToken) && tokenIsOperator(currentToken)) {

                appendOperatorsWithHigherOrSamePrecedence(operatorStack, currentToken, postfixStringBuilder);

                if (tokenIsClosingParenthesis(currentToken)) {
                    appendParenthesisOperators(operatorStack, postfixStringBuilder);
                } else {
                    operatorStack.push(currentToken);
                }

            } else if (tokenHasOneElement(currentToken) && tokenIsNotSpace(currentToken)) {
                postfixStringBuilder.append(" ").append(currentToken);
            }

        }

        while (stackHasMoreOperators(operatorStack))
            postfixStringBuilder.append(" ").append(operatorStack.pop());

        return postfixStringBuilder.toString();

    }

    private boolean tokenIsClosingParenthesis(String token) {
        return token.charAt(0) == ')';
    }

    private void appendOperatorsWithHigherOrSamePrecedence(Stack<String> operatorStack, String token, StringBuilder postfix) {
        while (stackHasMoreOperators(operatorStack) && tokenHasLowerOrSamePrecedence(lastOperator(operatorStack), token)) {
            postfix.append(" ").append(operatorStack.pop());
        }
    }

    private void appendParenthesisOperators(Stack <String> operatorStack, StringBuilder postfix) {
        String operatorToken = operatorStack.pop();
        while (operatorToken.charAt(0) != '(') {
            postfix.append(" ").append(operatorToken);
            operatorToken = operatorStack.pop();
        }
    }

    private boolean stackHasMoreOperators(Stack <String> operatorStack) {
        return !operatorStack.empty();
    }

    private boolean tokenHasLowerOrSamePrecedence(String leftSideToken, String rightSideToken) {
        return !lowerPrecedence(leftSideToken, rightSideToken);
    }

    private String lastOperator(Stack <String> operatorStack) {
        return operatorStack.peek();
    }

    private boolean tokenHasOneElement(String token) {
        return token.length() == 1;
    }

}
