package com.develogical;

import java.util.Arrays;
import java.util.List;

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
        } else if (containsAllWords(query, Arrays.asList("what", "is", "plus"))) {
            return handlePlus(query);
        }
        return "";
    }

    private String processLargestNumber(String rawQuery) {
        String csv = rawQuery.split(":")[1];
        String[] numberStrs = csv.split(",");
        int[] numbers = new int[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        
        int largest = numbers[0];
        for (int i : numbers) {
            largest = Math.max(i, largest);
        }
        return String.valueOf(largest);
    }

    private String handlePlus(String query) {
        // what is
        String substring = query.split(":")[1].trim().substring(8);
        String[] arguments = substring.split(" plus ");

        String failed = "Couldn't do plus";

        System.out.println("Handling plus!");

        if (arguments.length != 2) {
            System.err.println("Invalid number of arguments to plus: " + arguments.length);
            System.err.println("Arguments are: " + arguments.toString());
            return failed;
        }

        int x;
        int y;

        try {
            x = Integer.parseInt(arguments[0]);
            y = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            System.err.println("Either '" + arguments[0] + "' or '" + arguments[1] + "' is not a number");
            return failed;
        }


        int result = x + y;

        System.out.println("Result is " + result);

        return "" + result;
    }

    private boolean containsAllWords(String query, List<String> words) {
        for (String word : words) {
            if (!query.contains(word)) {
                return false;
            }
        }
        return true;
    }
}
