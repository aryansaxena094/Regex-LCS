# LCS - Longest Common Subsequence Matcher

A project by Aryan Saxena for COMP 6651 - Algorithm Design Techniques.

## Overview

This project provides an implementation of the Longest Common Subsequence (LCS) algorithm with an added twist. It matches strings from a dictionary against a regex pattern and then computes the LCS of the matching strings.

## Features

- **Regex Matcher**: Custom implementation of a regex matcher that supports `.` and `*` patterns.
- **LCS Calculator**: Computes the LCS of two strings in a case-insensitive manner.
- **LCS3 Calculator**: Computes the LCS of three strings by first determining the LCS of the first two and then comparing it with the third.

## How to Use

1. **Input**: Place your input in `input.txt`. The first line should contain the number of strings in the dictionary, followed by the strings themselves. The last line should contain the regex pattern.
2. **Run**: Execute the `regex_matcher_40233170` class.
3. **Output**: The result will be written to `output.txt`.

## Code Structure

- **Reading the Input**: The program reads from `input.txt` and populates a priority queue to maintain the dictionary in sorted order.
- **Regex Matching**: The program filters out strings from the dictionary that match the regex pattern.
- **LCS Calculation**: Depending on the number of matching strings, the program computes the LCS. If there are three matching strings, it computes the LCS of the three; if two, then the LCS of the two; if one, it directly outputs the matching string.

## Algorithms

### 1. Regex Matcher

A custom regex matcher that supports `.` (matches any character) and `*` (matches zero or more of the preceding character). This custom implementation is faster than Java's built-in regex library for these specific patterns.

### 2. LCS Calculator

The LCS calculator uses dynamic programming to compute the LCS of two strings. It performs the comparison in a case-insensitive manner. Instead of repeatedly calling `Character.toLowerCase()`, which can be slower, this implementation converts the entire strings to lowercase once, optimizing for time over space.

### 3. LCS3 Calculator

To compute the LCS of three strings, the program first determines the LCS of the first two strings and then compares it with the third. This approach reduces the complexity from \(O(n^3)\) to \(O(2n^2)\).

## License

© Aryan Saxena - 40233170 - COMP 6651