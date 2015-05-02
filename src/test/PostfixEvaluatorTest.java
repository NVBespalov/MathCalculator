package test;

import com.iSoft.calculator.converter.IConverter;
import com.iSoft.calculator.converter.InfixToPostfixConverter;
import com.iSoft.calculator.converter.ShuntingYardAlgorithm;
import com.iSoft.calculator.evaluator.IEvaluator;
import com.iSoft.calculator.evaluator.PostfixEvaluator;
import junit.framework.TestCase;


public class PostfixEvaluatorTest extends TestCase {

    public IEvaluator sut;

    public void setUp() throws Exception {
        super.setUp();
        sut = new PostfixEvaluator();
    }

    public void tearDown() throws Exception {
        sut = null;
    }

    public void testEvaluate() throws Exception {
        IConverter converter = new InfixToPostfixConverter(new ShuntingYardAlgorithm());
        String infixString = "2 + (2 * 2/(3-2))";
        String postfixString = converter.convert(infixString);
        assertEquals(6.0, sut.evaluate(postfixString));
    }
}