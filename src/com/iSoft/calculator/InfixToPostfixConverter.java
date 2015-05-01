package com.iSoft.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostfixConverter extends Stack implements IConverter {

    private boolean isOperator(char character) {

        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^'
                || character == '(' || character == ')';

    }

    private boolean isSpace(char c) {

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

        Stack operatorStack = new Stack();

        char firstCharacterOfToken;

        StringTokenizer parser = new StringTokenizer(infix, "+-*/^() ", true);

        StringBuffer postfix = new StringBuffer(infix.length());

        while (parser.hasMoreTokens()) {

            String token = parser.nextToken();

            firstCharacterOfToken = token.charAt(0);

            if (tokenHasOneElement(token) && isOperator(firstCharacterOfToken)) {

                while (!operatorStack.empty() &&
                        !lowerPrecedence(((String) operatorStack.peek()).charAt(0), firstCharacterOfToken))

                    postfix.append(" ").append((String) operatorStack.pop());

                if (firstCharacterOfToken == ')') {
                    String operator = (String) operatorStack.pop();
                    while (operator.charAt(0) != '(') {
                        postfix.append(" ").append(operator);
                        operator = (String) operatorStack.pop();
                    }
                } else
                    operatorStack.push(token);

            } else if (tokenHasOneElement(token) && isSpace(firstCharacterOfToken)) {

            } else {
                postfix.append(" ").append(token);
            }

        }

        while (!operatorStack.empty())
            postfix.append(" ").append((String) operatorStack.pop());

        return postfix.toString();

    }

    private boolean tokenHasOneElement(String token) {
        return token.length() == 1;
    }

}
