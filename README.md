# Regex Matcher and Longest Common Subsequence Finder

This Java program, `regex_matcher_40233170`, is designed to read a list of strings from an input file, match them against a given regex pattern, and then find the Longest Common Subsequence (LCS) among the matching strings.

## Features

- **File Input**: Reads a list of strings from a specified file.
- **Regex Matching**: Matches each string against a given regex pattern.
- **LCS Finder**: Determines the LCS among the matching strings. Supports finding LCS for two or three strings.
- **File Output**: Writes the LCS result to an `output.txt` file.

## Usage

1. Ensure you have the Java Development Kit (JDK) installed on your system.
2. Compile the program:
   ```bash
   javac regex_matcher_40233170.java
   ```
3. Run the program:
   ```bash
   java regex_matcher_40233170
   ```

## Input Format

The input file (`input.txt`) should be structured as follows:

- The first line contains an integer `n`, the number of strings in the dictionary.
- The next `n` lines contain the strings.
- The last line contains the regex pattern against which the strings will be matched.

## Output

The program will generate an `output.txt` file containing the LCS of the matching strings. If no strings match the regex pattern, the output file will be empty.

## Performance

The program uses dynamic programming to determine the LCS, ensuring efficient computation even for longer strings.

## Author

[aryansaxena094](https://github.com/aryansaxena094)