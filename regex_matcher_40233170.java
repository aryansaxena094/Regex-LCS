import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class regex_matcher_40233170 {
    public static String LCS3(String X, String Y, String Z)
    {
        int m = X.length();
        int n = Y.length();
        int o = Z.length();

        int[][][] L = new int[m+1][n+1][o+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                for (int k=0; k<=o; k++)
                {
                    if (i == 0 || j == 0||k==0)
                        L[i][j][k] = 0;
      
                    else if (X.charAt(i - 1) == Y.charAt(j - 1)
                                && X.charAt(i - 1)==Z.charAt(k - 1))
                        L[i][j][k] = L[i-1][j-1][k-1] + 1;
      
                    else
                        L[i][j][k] = Math.max(Math.max(L[i-1][j][k],
                                             L[i][j-1][k]),
                                         L[i][j][k-1]);
                }
            }
        }
        System.out.println(L[m][n][o]);
        int i = m, j = n, k = o;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0 && k > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1) && X.charAt(i - 1) == Z.charAt(k - 1)) {
                lcs.insert(0, X.charAt(i - 1));
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

        return lcs.toString();
    }

    public static void main(String[] args) {
        //  Recording Starting Time
        long startTime = System.currentTimeMillis();

        // Input from file
        List<String> inputFile = new ArrayList<String>();
        try{
            inputFile = Files.readAllLines(Paths.get("input2.txt"));
        } catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(inputFile);
        // Logic
        int n = Integer.parseInt(inputFile.get(0));
        Pattern pattern = Pattern.compile(inputFile.get(n+1));
        Matcher matcher;
        List<String> matchingWords = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            matcher = pattern.matcher(inputFile.get(i));
            if (matcher.matches()) {
                matchingWords.add(inputFile.get(i));
            }
        }
        Collections.sort(matchingWords, String.CASE_INSENSITIVE_ORDER);
        if (matchingWords.size() > 3) {
            matchingWords = matchingWords.subList(0, 3);
        }
        String output = LCS3(matchingWords.get(0), matchingWords.get(1), matchingWords.get(2));
        System.out.println(matchingWords);
        System.out.println(output);
        //Output to File
        try {
            Files.write(Paths.get("output.txt"), output.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time in milliseconds: " + (endTime - startTime));
    }
}
