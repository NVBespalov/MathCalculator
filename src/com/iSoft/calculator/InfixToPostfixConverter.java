package com.iSoft.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostfixConverter extends Stack implements IConverter {

    private boolean tokenIsOperator(String token) {
        char character = token.charAt(0);
        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^'
                || character == '(' || tokenIsClosingParenthesis(token);

    }

    private boolean tokenIsSpace(String token) {

        return (token.charAt(0) == ' ');

    }

    private boolean lowerPrecedence(char leftOperator, char rightOperator) {

        switch (leftOperator) {

            case '+':
            case '-':
                return !(rightOperator == '+' || rightOperator == '-');

            case '*':
            case '/':
                return rightOperator == '^' || rightOperator == '(';

            case '^':
                return rightOperator == '(';

            case '(':
                return true;

            default:
                return false;
        }

    }

    @Override
    public String convert(String infix) {

        Stack<String> operatorStack = new Stack <> ();

        StringTokenizer parser = new StringTokenizer(infix, "+-*/^() ", true);

        StringBuilder postfixStringBuilder = new StringBuilder(infix.length());

        while (parser.hasMoreTokens()) {

            String currentToken = parser.nextToken();

            if (tokenHasOneElement(currentToken) && tokenIsOperator(currentToken)) {

                appendOperatorsWithLowerPrecedence(operatorStack, currentToken, postfixStringBuilder);

                if (tokenIsClosingParenthesis(currentToken)) {
                    appendParenthesisOperators(operatorStack, postfixStringBuilder);
                } else {
                    operatorStack.push(currentToken);
                }

            } else if (tokenHasOneElement(currentToken) && tokenIsSpace(currentToken)) {

            } else {
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

    private void appendOperatorsWithLowerPrecedence(Stack<String> operatorStack, String token, StringBuilder postfix) {
        while (stackHasMoreOperators(operatorStack) &&
                tokenHasHigherPrecedence(operatorStack, token)) {

            postfix.append(" ").append(operatorStack.pop());
        }
    }

    private void appendParenthesisOperators(Stack<String> operatorStack, StringBuilder postfix) {
        String operatorToken = operatorStack.pop();
        while (operatorToken.charAt(0) != '(') {
            postfix.append(" ").append(operatorToken);
            operatorToken = operatorStack.pop();
        }
    }

    private boolean stackHasMoreOperators(Stack operatorStack) {
        return !operatorStack.empty();
    }

    private boolean tokenHasHigherPrecedence(Stack operatorStack, String token) {
        return !lowerPrecedence(lastOperator(operatorStack), token.charAt(0));
    }

    private char lastOperator(Stack operatorStack) {
        return ((String) operatorStack.peek()).charAt(0);
    }

    private boolean tokenHasOneElement(String token) {
        return token.length() == 1;
    }

}
