package com.develogical;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
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
    public void knowsAboutTeamName() {
        assertThat(queryProcessor.process("which of the following numbers is the largest: 90, 94, 871, 425"), containsString("871"));
    }
}
