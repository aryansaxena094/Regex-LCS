import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class regex_matcher_40233170 {

    public static List<String> input(String path){
        // Input from file
        List<String> inputFile = new ArrayList<String>();
        try{
            inputFile = Files.readAllLines(Paths.get(path));
        } catch(IOException e){
            e.printStackTrace();
        }
        return inputFile;
    }

    public static void writeOutput(String output){
                try {
            Files.write(Paths.get("output.txt"), output.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String LCS3(String A, String B, String C) {
        int m = A.length();
        int n = B.length();
        int o = C.length();
    
        // Convert strings to lowercase once to avoid multiple conversions
        char[] aChars = A.toLowerCase().toCharArray();
        char[] bChars = B.toLowerCase().toCharArray();
        char[] cChars = C.toLowerCase().toCharArray();
    
        int[][][] L = new int[m + 1][n + 1][o + 1];
    
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= o; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        L[i][j][k] = 0;
                    } else if (aChars[i - 1] == bChars[j - 1] && aChars[i - 1] == cChars[k - 1]) {
                        L[i][j][k] = L[i - 1][j - 1][k - 1] + 1;
                    } else {
                        L[i][j][k] = Math.max(Math.max(L[i - 1][j][k], L[i][j - 1][k]), L[i][j][k - 1]);
                    }
                }
            }
        }
    
        int i = m, j = n, k = o;
        StringBuilder lcs = new StringBuilder();
    
        while (i > 0 && j > 0 && k > 0) {
            if (aChars[i - 1] == bChars[j - 1] && aChars[i - 1] == cChars[k - 1]) {
                lcs.append(A.charAt(i - 1));  // Use original string to preserve case
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
    
        return lcs.reverse().toString();
    }
        
    public static String LCS(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] L = new int[m+1][n+1];
    
        char[] aChars = A.toLowerCase().toCharArray();
        char[] bChars = B.toLowerCase().toCharArray();
    
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
    
                else if (aChars[i - 1] == bChars[j - 1])
                    L[i][j] = L[i-1][j-1] + 1;
    
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
    
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();
    
        while (i > 0 && j > 0) {
            if (aChars[i - 1] == bChars[j - 1]) {
                lcs.append(A.charAt(i - 1));  // Use the original character from A
                i--;
                j--;
            } else if (L[i - 1][j] >= L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }
    
    public static void main(String[] args) {
        //  Recording Starting Time
        long startTime = System.currentTimeMillis();

        // Reading data from the input file using the function input
        List<String> inputFile =  input("input.txt");
        
        // Logic
        // Taking the size of the dictionary i.e n
        int n = Integer.parseInt(inputFile.get(0));
        System.out.println(inputFile);
        // Making a case insensitive
        Pattern pattern = Pattern.compile(inputFile.get(n+1),Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<String> matchingWords = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            matcher = pattern.matcher(inputFile.get(i));
            if (matcher.matches()) {
                matchingWords.add(inputFile.get(i));
            }
        }
        Collections.sort(matchingWords, String.CASE_INSENSITIVE_ORDER);
        System.out.println(matchingWords);
        String output;
        switch(matchingWords.size()){
            case 3: output = LCS3(matchingWords.get(0), matchingWords.get(1), matchingWords.get(2));
            break;
            case 2: output = LCS(matchingWords.get(0), matchingWords.get(1));
            break;
            case 1: output = matchingWords.get(0);
            break;
            default: output = "";
        }

        System.out.println(output);
        // Writing the output in the output file
        writeOutput(output);

        // Logging time for debugging the running time
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time in milliseconds: " + (endTime - startTime));
    }
}
