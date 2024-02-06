package com.example.wordswordswords_week_4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development II
// Instructor Mr. Gross


// Copyright (C) 2024  Daniel Smith

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.


class App
{
    // original testing string
    static String test_string = "the quick Brown Fox Jumped over the lazy dog";

    private static ArrayList<String> countWords(String input)
    {
        // initialize the output and regular expression
        ArrayList<String> output = new ArrayList<String>();
        Pattern pattern = Pattern.compile("([\\w\\d']+)");

        // split the string by spaces and then add ever word
        // to the output if it matches the regular expression
        for (String i : input.split(" "))
        {
            Matcher matcher = pattern.matcher(i);
            if (!matcher.find())
                continue;

            output.add(matcher.group(0));
        }

        return output;
    }

    public static void main(String args[]) throws Exception
    {
        // split the test string and print out the words
        ArrayList<String> words = countWords(test_string);
        for (String i : words)
        {
            System.out.println(i);
        }
        System.out.printf("Total number of words: %d\n", words.size());

        // initialize the scanner
        Scanner scanner = new Scanner(System.in);

        // ask the user for a sentence
        System.out.print("Enter a sentence: ");
        String line = scanner.nextLine();

        // split the words and print them out
        words = countWords(line);
        for (String i : words)
        {
            System.out.println(i);
        }
        System.out.printf("Total number of words: %d\n", words.size());

        // close the scanner. very important!
        scanner.close();
    }
}
