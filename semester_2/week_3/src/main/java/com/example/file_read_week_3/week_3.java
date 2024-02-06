package com.example.file_read_week_3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public static void main(String args[]) throws Exception
    {
        // check the command-line parameters
        if (args.length > 0)
        {
            // check if the given file exists
            File f = new File(args[0]);
            if (!f.exists())
            {
                System.out.printf("File `%s': No such file or directory.\n", args[0]);
                System.exit(1);
            }
        }
        
        // initialize the file and regular expressions
        File file = new File(args[0]);
        Scanner file_scanner = new Scanner(file);
        Pattern pattern = Pattern.compile("(.+)=(.+)");

        // initialize other variables
        String first_name = "";
        String last_name = "";
        String grade = "";
        String school = "";

        // parse the file
        int file_line = 0;
        while (file_scanner.hasNextLine())
        {
            String data = file_scanner.nextLine();
            String key = "";
            String value = "";

            // match the current line against the regular expression
            Matcher matcher = pattern.matcher(data);
            if (!matcher.find())
            {
                System.out.printf("Parsing error in file `%s' on line %d. Aborting.\n", args[0], file_line);
                System.exit(1);
            }

            key = matcher.group(1);
            value = matcher.group(2);

            // match the current key with a variable
            switch (key)
            {
                case "first_name":
                    first_name = value;
                    break;
                
                case "last_name":
                    last_name = value;
                    break;

                case "grade":
                    grade = value;
                    break;
                
                case "school":
                    school = value;
                    break;
                
                default:
                    break;
            }

            file_line++;
        }

        // closing the file scanner. very important!
        file_scanner.close();

        // printing out the contents of the parsed information
        System.out.printf("First name: %s\n", first_name);
        System.out.printf("Last name: %s\n", last_name);
        System.out.printf("Grade: %s\n", grade);
        System.out.printf("School: %s\n", school);
    }
}
