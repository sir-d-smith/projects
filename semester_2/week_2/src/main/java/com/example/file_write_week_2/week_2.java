package com.example.file_write_week_2;

import java.util.*;
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
        // initialize the scanner
        Scanner scanner = new Scanner(System.in);

        // check the command-line parameters
        if (args.length > 0)
        {
            // check if the given file already exists
            File f = new File(args[0]);
            if (f.exists() && !f.isDirectory())
            {
                // give the option to the user to overwrite
                // the current file
                System.out.printf("File `%s' exists. Overwrite? (yes/no): ", args[0]);
                String input = scanner.nextLine();

                if (!input.toLowerCase().startsWith("y"))
                {
                    System.out.println("Aborting.");
                    System.exit(1);
                }
            }
        }
        else
        {
            System.out.println("No input file given.\nUsage: file_write_week_2-1.0.0.jar <path to file>");
            System.exit(1);
        }

        // initialize global variables
        String first_name = "";
        String last_name = "";
        String grade = "";
        String school = "";

        // read the first and last name
        while (true)
        {
            System.out.print("Enter your first & last name: ");
            String full_name = scanner.nextLine();
            String parts[] = full_name.split(" ");

            // if the input contained blank text
            if (parts.length == 0 || full_name.length() == 0)
            {
                System.out.println("Please enter your name");
                continue;
            }

            // if the input did not contain a last name,
            // assume the last name is 'Doe'
            if (parts.length == 1)
            {
                first_name = parts[0];
                last_name = "Doe";
                break;
            }

            // if all else, capture the first & last name
            if (parts.length > 1)
            {
                first_name = parts[0];
                last_name = parts[parts.length - 1];
                break;
            }
        }

        // read the grade level
        System.out.print("Enter your current grade level (i.e. 11th, junior): ");
        grade = scanner.nextLine();

        // read the school name
        System.out.print("Enter the name of your current school: ");
        school = scanner.nextLine();

        // initialize file writer & print writer
        FileWriter file_writer = new FileWriter(args[0]);
        PrintWriter print_writer = new PrintWriter(file_writer);

        // write data to file in configuration format
        try
        {
            print_writer.printf("first_name=%s\n", first_name);
            print_writer.printf("last_name=%s\n", last_name);
            print_writer.printf("grade=%s\n", grade);
            print_writer.printf("school=%s\n", school);
        }
        catch (Exception e)
        {
            System.out.printf("Failed to write to file `%s': %s", args[0], e);
        }

        // close file & scanner. also very important!
        print_writer.close();
        scanner.close();

        System.out.printf("Wrote student data to file `%s'.\n", args[0]);
    }
}
