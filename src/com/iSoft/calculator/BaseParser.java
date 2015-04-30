package com.iSoft.calculator;
import java.util.Stack;

public class BaseParser implements IMathExpressionParser {


    @Override
    public Stack parse(String string) {
        return new Stack();
    }
}
