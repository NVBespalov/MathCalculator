package com.iSoft.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostfixConverter extends Stack implements IConverter {

    private boolean isOperator(char character) {

        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^'
                || character == '(' || character == ')';

    }

    private boolean tokenCharacterIsSpace(char c) {

        return (c == ' ');

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

        char firstCharacterOfToken;

        StringTokenizer parser = new StringTokenizer(infix, "+-*/^() ", true);

        StringBuilder postfix = new StringBuilder(infix.length());

        while (parser.hasMoreTokens()) {

            String token = parser.nextToken();

            firstCharacterOfToken = token.charAt(0);

            if (tokenHasOneElement(token) && isOperator(firstCharacterOfToken)) {

                appendOperatorsWithLowerPrecedence(operatorStack, firstCharacterOfToken, postfix);

                if (firstCharacterOfToken == ')') {
                    appendParenthesisOperators(operatorStack, postfix);
                } else {
                    operatorStack.push(token);
                }

            } else if (tokenHasOneElement(token) && tokenCharacterIsSpace(firstCharacterOfToken)) {

            } else {
                postfix.append(" ").append(token);
            }

        }

        while (stackHasMoreOperators(operatorStack))
            postfix.append(" ").append(operatorStack.pop());

        return postfix.toString();

    }

    private void appendOperatorsWithLowerPrecedence(Stack<String> operatorStack, char firstCharacterOfToken, StringBuilder postfix) {
        while (stackHasMoreOperators(operatorStack) &&
                characterOfTokenHasHigherPrecedence(operatorStack, firstCharacterOfToken)) {


            postfix.append(" ").append(operatorStack.pop());
        }
    }

    private void appendParenthesisOperators(Stack<String> operatorStack, StringBuilder postfix) {
        String operator = operatorStack.pop();
        while (operator.charAt(0) != '(') {
            postfix.append(" ").append(operator);
            operator = operatorStack.pop();
        }
    }

    private boolean stackHasMoreOperators(Stack operatorStack) {
        return !operatorStack.empty();
    }

    private boolean characterOfTokenHasHigherPrecedence(Stack operatorStack, char firstCharacterOfToken) {
        return !lowerPrecedence(lastOperator(operatorStack), firstCharacterOfToken);
    }

    private char lastOperator(Stack operatorStack) {
        return ((String) operatorStack.peek()).charAt(0);
    }

    private boolean tokenHasOneElement(String token) {
        return token.length() == 1;
    }

}
