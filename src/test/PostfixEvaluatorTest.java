package test;

import com.iSoft.calculator.IEvaluator;
import com.iSoft.calculator.IConverter;
import com.iSoft.calculator.InfixToPostfixConverter;
import com.iSoft.calculator.PostfixEvaluator;
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
        IConverter converter = new InfixToPostfixConverter();
        String infixString = "2 + (2 * 2/(3-2))";
        String postfixString = converter.parse(infixString);
        assertEquals(6.0, sut.evaluate(postfixString));
    }
}