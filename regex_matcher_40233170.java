import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
        // Getting lengths of all three for the constructing of the matrices
        int m = A.length();
        int n = B.length();
        int o = C.length();

        // Getting the characters in lower case to perform the comparison case
        // insensitive
        char[] aChars = A.toLowerCase().toCharArray();
        char[] bChars = B.toLowerCase().toCharArray();
        char[] cChars = C.toLowerCase().toCharArray();

        // Initializing a matrix for DP
        int[][][] L = new int[m + 1][n + 1][o + 1];

        // Filling values in the DP (Going from top left to bottom right)
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= o; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        // For 0th column and 0th row
                        L[i][j][k] = 0;
                    } else if (aChars[i - 1] == bChars[j - 1] && aChars[i - 1] == cChars[k - 1]) {
                        // If characters match for all three strings
                        L[i][j][k] = L[i - 1][j - 1][k - 1] + 1;
                    } else {
                        // Getting the last longest LCS among strings (If current doesn't match)
                        L[i][j][k] = Math.max(Math.max(L[i - 1][j][k], L[i][j - 1][k]), L[i][j][k - 1]);
                    }
                }
            }
        }

        int i = m, j = n, k = o;
        StringBuilder lcs = new StringBuilder();

        // Reconstructing
        while (i > 0 && j > 0 && k > 0) {
            if (aChars[i - 1] == bChars[j - 1] && aChars[i - 1] == cChars[k - 1]) {
                // If it matches the add to the string
                lcs.append(A.charAt(i - 1)); // Use original string to preserve case
                i--;
                j--;
                k--;
            } else if (L[i - 1][j][k] >= L[i][j - 1][k] && L[i - 1][j][k] >= L[i][j][k - 1]) {
                i--;
            } else if (L[i][j - 1][k] >= L[i - 1][j][k] && L[i][j - 1][k] >= L[i][j][k - 1]) {
                j--;
            } else {
                k--;
            }
        }
        // Reverse the string to get lcs because characters are appended
        return lcs.reverse().toString();
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
        List<String> inputFile = input("input.txt");

        // Logic
        // Taking the size of the dictionary i.e n
        int n = Integer.parseInt(inputFile.get(0));
        System.out.println(inputFile);

        // Making a pattern of the last word (i.e the Pattern)
        Pattern pattern = Pattern.compile(inputFile.get(n + 1), Pattern.CASE_INSENSITIVE);

        // Initializing a matcher
        Matcher matcher;

        // Making an ArrayList to store the matching words
        List<String> matchingWords = new ArrayList<>();

        // Filtering the matching words from the arraylist
        for (int i = 1; i <= n; i++) {
            matcher = pattern.matcher(inputFile.get(i));
            if (matcher.matches()) {
                matchingWords.add(inputFile.get(i));
            }
        }

        // Case-insensitively soting the matching words
        Collections.sort(matchingWords, String.CASE_INSENSITIVE_ORDER);
        System.out.println(matchingWords);

        // Initializing output String
        String output;

        // Switch cases to deal with the number of matching words we get
        switch (matchingWords.size()) {
            // If 3 matching words then LCS of those three
            case 3:
                output = LCS3(matchingWords.get(0), matchingWords.get(1), matchingWords.get(2));
                break;
            // If 2 matching words then normal LCS of those two
            case 2:
                output = LCS(matchingWords.get(0), matchingWords.get(1));
                break;
            // Else output the only matching string straight out
            case 1:
                output = matchingWords.get(0);
                break;
            // If there are no matching words with the last string then there would be no
            // output
            default:
                output = "";
        }

        System.out.println(output);
        // Writing the output in the output file using the output string
        writeOutput(output);

        // Logging time for debugging the running time
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time in milliseconds: " + (endTime - startTime));
    }
}