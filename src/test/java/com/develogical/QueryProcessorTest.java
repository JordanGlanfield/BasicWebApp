package com.develogical;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

    QueryProcessor queryProcessor = new QueryProcessor();

    @Test
    public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
        assertThat(queryProcessor.process("test"), is(""));
    }

    @Test
    public void knowsAboutShakespeare() throws Exception {
        assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
    }

    @Test
    public void isNotCaseSensitive() throws Exception {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }

    @Test
    public void knowsAboutBanter() {
        assertThat(queryProcessor.process("banter"), containsString("teasing"));
    }

    @Test
    public void knowsAboutTeamName() {
        assertThat(queryProcessor.process("what is your team name"), containsString("banterwagon"));
    }

    @Test
    public void canCheckLargestNumbers() {
        assertThat(queryProcessor.process("abfddfg: which of the following numbers is the largest: 90, 94, 871, 425"), containsString("871"));
    }

    @Test
    public void canCheckSquareAndCube() {
        assertThat(queryProcessor.process("abfddfg: which of the following numbers is both a square and a cube: 319, 1, 2500, 26"), containsString("1"));
    }
    
    @Test
    public void canAddNumbers() {
        assertThat(queryProcessor.process("abdgrsgd: what is 5 plus 10"), containsString("15"));
    }

    @Test
    public void canMultiplyNumbers() {
        assertThat(queryProcessor.process("sdafdasfd: what is 9 multiply 5"), containsString("45"));
    }

    @Test
    public void canMinusNumbers() {
        assertThat(queryProcessor.process("dsfagads: what is 100 minus 13"), equalTo("87"));
    }

    @Test
    public void canPlusManyNumbers() {
        assertThat(queryProcessor.process("afdggfd: what is 5 plus 10 plus 9"), containsString("24"));
    }
}
