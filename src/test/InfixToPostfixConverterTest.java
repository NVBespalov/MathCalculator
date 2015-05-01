package test;

import com.iSoft.calculator.IConverter;
import com.iSoft.calculator.InfixToPostfixConverter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;


public class InfixToPostfixConverterTest extends Assert {

    private IConverter sut;

    @Before
    public void setUp() throws Exception {
        this.sut = new InfixToPostfixConverter();
    }

    @After
    public void tearDown() throws Exception {
        this.sut = null;
    }

    @Test
    public void testParseResultType() {
        assertThat(this.sut.parse(""), instanceOf(String.class));
    }

    @Test
    public void testParsePlusResult() {
        String a = this.sut.parse("2+2");
        assertEquals(sut.parse("2+2"), " 2 2 +");
    }

}