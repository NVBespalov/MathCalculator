package test;

import com.iSoft.calculator.ShuntingYardAlgorithm;
import junit.framework.TestCase;

public class ShuntingYardAlgorithmTest extends TestCase {

    public ShuntingYardAlgorithm sut;

    public void setUp() throws Exception {
        super.setUp();
        sut = new ShuntingYardAlgorithm();
    }

    public void tearDown() throws Exception {
        sut = null;
    }

    public void testExecute() throws Exception {
        assertEquals(" 2.1 2 2 * 3 2 - / +", sut.execute("2.1 + (2 * 2/(3-2))"));
    }
}