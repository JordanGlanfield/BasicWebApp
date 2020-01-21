package com.develogical;

public class QueryProcessor {

    public String process(String rawQuery) {
        String query = rawQuery.toLowerCase();

        System.out.println("Query received: '" + rawQuery + "'");

        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("banter")) {
            return "the playful and friendly exchange of teasing remarks.\n" +
                    "\"there was much good-natured banter\"";
        } else if (query.contains("what") && query.contains("is") && query.contains("your") && query.contains("team")) {
            return "Our team name is banterwagon";
        } else if (query.contains("which of the following numbers is the largest")) {
            return processLargestNumber(rawQuery);
        }
        return "";
    }

    private String processLargestNumber(String rawQuery) {
        String csv = rawQuery.split(":")[1];
        String[] numberStrs = csv.split(",");
        int[] numbers = new int[numberStrs.length];
        int largest = numbers[0];
        for (int i : numbers) {
            largest = Math.max(i, largest);
        }
        return String.valueOf(largest);
    }
}
