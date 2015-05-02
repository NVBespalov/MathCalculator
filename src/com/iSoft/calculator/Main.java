package com.iSoft.calculator;

import com.iSoft.calculator.converter.IConverter;
import com.iSoft.calculator.converter.InfixToPostfixConverter;
import com.iSoft.calculator.converter.ShuntingYardAlgorithm;
import com.iSoft.calculator.evaluator.IEvaluator;
import com.iSoft.calculator.evaluator.PostfixEvaluator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        do {
            Scanner reader = new Scanner(System.in);
            System.out.println("\nВведите выражение: ");
            String infixString = reader.next();
            IConverter converter = new InfixToPostfixConverter(new ShuntingYardAlgorithm());
            IEvaluator evaluator = new PostfixEvaluator();
            System.out.print("Результат: " + evaluator.evaluate(converter.convert(infixString)) + "\n");
        } while (true);
    }
}
