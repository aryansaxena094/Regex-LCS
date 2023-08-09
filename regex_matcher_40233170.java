// © Aryan Saxena - 40233170
// COMP 6651 - Algoithm Design Techniques Project

// Imports
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class regex_matcher_40233170 {

    public static List<String> input(String path) {
        // Function for Inputing from file
        List<String> inputFile = new ArrayList<String>();
        try {
            inputFile = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFile;
    }

    public static void writeOutput(String output) {
        // Function for writing the output in a file('output.txt')
        try {
            Files.write(Paths.get("output.txt"), output.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String LCS3(String A, String B, String C) {
        String lcsOfFirstTwo = LCS(A, B);
        return LCS(lcsOfFirstTwo, C);
    }

    public static boolean isRegexMatch(String inputString, String regexPattern) {
        int m = inputString.length();
        int n = regexPattern.length();
    
        // dp[i][j] will be true if the first i characters in inputString match the first j characters in regexPattern
        boolean[][] dp = new boolean[m + 1][n + 1];
    
        // An empty pattern matches an empty string
        dp[0][0] = true;
    
        // Handle patterns like a* or a*b* or a*b*c*
        for (int j = 1; j <= n; j++) {
            if (regexPattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
    
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (regexPattern.charAt(j - 1) == '.' || regexPattern.charAt(j - 1) == inputString.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (regexPattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2]; // Consider the pattern without the preceding character and '*'
                    if (regexPattern.charAt(j - 2) == '.' || regexPattern.charAt(j - 2) == inputString.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }
    
    public static String LCS(String A, String B) {
        // Getting lengths in order to construct the matrices
        int m = A.length();
        int n = B.length();
        // Initializing a DP matrix
        int[][] L = new int[m + 1][n + 1];
        // Getting the characters in lower case to perform the comparison case
        // insensitive
        char[] aChars = A.toLowerCase().toCharArray();
        char[] bChars = B.toLowerCase().toCharArray();
        // Adding values to the DP matrix
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    // If the column and row are at the 0th index
                    L[i][j] = 0;
                } else if (aChars[i - 1] == bChars[j - 1]) {
                    // If the current characters are equal
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    // If the characters are different
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();
        // Reconstructing the LCS usingthe DP matrix table
        while (i > 0 && j > 0) {
            if (aChars[i - 1] == bChars[j - 1]) {
                // If the characters are equal then add it to the LCS
                lcs.append(A.charAt(i - 1)); // Use the original character from A
                i--;
                j--;
            }
            // Otherwise move to the maximum of the two values(either up or left)
            else if (L[i - 1][j] >= L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        // Reverse the string to get lcs because characters are appended
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        // Recording Starting Time
        long startTime = System.currentTimeMillis();

        // Reading data from the input file using the function input
        List<String> data = input("input.txt");

        System.out.println(data);

        // Logic
        // Taking the size of the dictionary i.e n
        int n = Integer.parseInt(data.get(0));

        PriorityQueue<String> sortedStrings = new PriorityQueue<>(String::compareToIgnoreCase);
        sortedStrings.addAll(data.subList(1, data.size() - 1));
        
        System.out.println(sortedStrings);

        // Getting the pattern
        String regexString = data.get(n+1);

        // Making an ArrayList to store the matching words
        data = new ArrayList<>();

        // Filtering the matching words from the arraylist
        while(!sortedStrings.isEmpty() && data.size() < 3){
            String current = sortedStrings.poll();
            if (isRegexMatch(current, regexString)) {
                data.add(current);
            }
        }
        
        System.out.println(data);

        // Switch cases to deal with the number of matching words we get
        switch (data.size()) {
            // If 3 matching words then LCS of those three
            case 3:
                writeOutput(LCS3(data.get(0), data.get(1), data.get(2)));
                break;
            // If 2 matching words then normal LCS of those two
            case 2:
                writeOutput(LCS(data.get(0), data.get(1)));
                break;
            // Else output the only matching string straight out
            case 1:
                writeOutput(data.get(0));
                break;
            // If there are no matching words with the last string then there would be no
            // output
            default:
                writeOutput("");
        }

        // Logging time for debugging the running time
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time in milliseconds: " + (endTime - startTime));
    }
}