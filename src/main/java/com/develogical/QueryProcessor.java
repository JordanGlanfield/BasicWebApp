package com.develogical;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class QueryProcessor {

    public String process(String rawQuery) {
        String query = rawQuery.toLowerCase();

        System.out.println("Query received: '" + rawQuery + "'");

        String response = "";

        if (query.contains("shakespeare")) {
            response = "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("banter")) {
            response =  "the playful and friendly exchange of teasing remarks.\n" +
                    "\"there was much good-natured banter\"";
        } else if (query.contains("what") && query.contains("is") && query.contains("your") && query.contains("team")) {
            response =  "Our team name is banterwagon";
        } else if (query.contains("which of the following numbers is the largest")) {
            response =  processLargestNumber(rawQuery);
        } else if (containsAllWords(query, Arrays.asList("what", "is", "plus"))) {
            response = handlePlus(query);
        } else if (containsAllWords(query, Arrays.asList("square", "cube"))) {
            response =  handleSquareAndCube(query);
        } else if (containsAllWords(query, Arrays.asList("what", "is", "multiply"))) {
            response =  handleMultiply(query);
        } else if (containsAllWords(query, Arrays.asList("what", "is", "minus"))) {
            response = handleMinus(query);
        } else if (containsAllWords(query, Arrays.asList("what", "is", "power"))) {
            response = handleBinop(query, "tothepowerof", (x, y) -> (int) Math.pow(x, y));
        }

        System.out.println("Response is...");
        System.out.println(response);

        return response;
    }

    private String processLargestNumber(String rawQuery) {
        String csv = rawQuery.split(":")[2].trim();
        csv = stripWhitespace(csv);
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

    private String handleSquareAndCube(String rawQuery) {
        String csv = rawQuery.split(":")[2].trim();
        csv = csv.replaceAll("\\s+","");
        String[] numberStrs = csv.split(",");
        int[] numbers = new int[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }

        for (int i : numbers) {
            double sq = Math.pow(i, 1.0/2);
            double cu = Math.pow(i, 1.0/3);
            if (sq - Math.floor(sq) == 0 && cu - Math.floor(cu) == 0) {
                return String.valueOf(i);
            }
        }
        return "";
    }

    private String handlePlus(String query) {
        return handleBinop(query, "plus", Integer::sum);
    }

    private boolean containsAllWords(String query, List<String> words) {
        for (String word : words) {
            if (!query.contains(word)) {
                return false;
            }
        }
        return true;
    }

    private String handleMultiply(String query) {
        return handleBinop(query, "multiply", (x, y) -> x * y);
    }

    private String handleMinus(String query) {
        return handleBinop(query, "minus", (x, y) -> x - y);
    }

    private String stripWhitespace(String string) {
        return string.replaceAll("\\s+","");
    }

    private String handleBinop(String query, String keyword, BiFunction<Integer, Integer, Integer> op) {
        String substring = stripWhitespace(query.split(":")[1].trim().substring(8));
        String[] arguments = substring.split(keyword);

        String failed = "Couldn't do " + keyword;

        System.out.println("Handling " + keyword + "!");

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

        int result = op.apply(x, y);

        System.out.println("Result is " + result);

        return String.valueOf(result);
    }
}
