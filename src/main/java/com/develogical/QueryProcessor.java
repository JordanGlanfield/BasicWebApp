package com.develogical;

public class QueryProcessor {

    public String process(String rawQuery) {
        String query = rawQuery.toLowerCase();

        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("banter")) {
            return "the playful and friendly exchange of teasing remarks.\n" +
                    "\"there was much good-natured banter\"";
        } else if (query.contains("what is your team name")) {
            return "Our team name is banterwagon";
        }
        return "";
    }
}
