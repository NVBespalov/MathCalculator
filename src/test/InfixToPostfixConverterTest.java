package test;

import com.iSoft.calculator.converter.IConverter;
import com.iSoft.calculator.converter.InfixToPostfixConverter;
import com.iSoft.calculator.converter.ShuntingYardAlgorithm;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;


public class InfixToPostfixConverterTest extends Assert {

    private IConverter sut;

    @Before
    public void setUp() throws Exception {
        this.sut = new InfixToPostfixConverter(new ShuntingYardAlgorithm());
    }

    @After
    public void tearDown() throws Exception {
        this.sut = null;
    }

    @Test
    public void testParseResultType() {
        assertThat(this.sut.convert(""), instanceOf(String.class));
    }

    @Test
    public void testParsePlusResult() {
        assertEquals(" 2 2 +", sut.convert("2+2"));
    }

}