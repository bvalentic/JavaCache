import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        List<String> cache = new ArrayList<String>();
        String output = "";
        while ((line = in.readLine()) != null) {
            String[] lineArray = readInput(line);
            output = doOperation(cache, lineArray[0], lineArray[1]);
            System.out.println(output);
        }
    }

    public static String[] readInput(String input) {
        String operation;
        String entry;
        if (input.contains("Reset")) {
            operation = "Reset";
            entry = "";
        } else {
            String[] splitStrings = input.split("\\|"); // pipe character doesn't split properly
            operation = splitStrings[0];
            entry = splitStrings[1];
        }
        return new String[] {operation, entry};
    }

    public static String doOperation(List<String> cache, String operation, String entry) {
        switch(operation) {
            case "Add":
                // add; return true if added, false if already in
                if (cache.contains(entry)) {
                    return "False";
                } else {
                    cache.add(entry);
                    return "True";
                }
            case "Get":
                // return string if exists, NULL if not
                if (cache.contains(entry)) {
                    return entry;
                } else {
                    return "NULL";
                }
            case "Has":
                // return true/false if exists
                if (cache.contains(entry)) {
                    return "True";
                } else {
                    return "False";
                }
            case "Remove":
                // delete string; return true if deleted, false if did not exist
                if (cache.contains(entry)) {
                    cache.remove(entry);
                    return "True";
                } else {
                    return "False";
                }
            case "Reset":
                // empty cache of all entries; return # of entries
                int numEntries = cache.size();
                String intString = Integer.toString(numEntries);
                List<String> newCache = new ArrayList<String>();
                cache = newCache;
                return intString;
        }
        return "";
    }
}


