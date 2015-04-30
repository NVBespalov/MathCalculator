package test;

import com.iSoft.calculator.BaseParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Stack;
import static org.hamcrest.CoreMatchers.instanceOf;


public class MathStringParserTest extends Assert {

    private BaseParser sut;
    @Before
    public void setUp() throws Exception {
        this.sut = new BaseParser();
    }

    @After
    public void tearDown() throws Exception {
        this.sut = null;
    }

    @Test
    public void testParseResultType() {
        assertThat(this.sut.parse(""), instanceOf(Stack.class));
    }

}