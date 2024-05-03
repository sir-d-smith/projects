package com.example.more_debugging_week_12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import com.example.more_debugging_week_12.Logger.LoggerLevel;

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
    // new! creating a global logger function to use
    // minimum logger level is INFO, so it will skip DEBUG
    // it's also possible to turn off logging in general by setting
    // the 'true' variable to 'false'
    static Logger logger = new Logger(true, LoggerLevel.DEBUG, System.err);

    // creating regular expressions
    private static final String regex_string = "\"([^\\\"]*)\"";
    private static final String regex_integer = "(-?\\d+)";
    private static final String regex_float = "(-?\\d+\\.\\d+)";

    private static ArrayList<Collection> parseRow(String input, int line_number)
    {
        // set up initial variables and regular expressions
        ArrayList<Collection> output = new ArrayList<Collection>();
        String split[] = input.split(",");
        Pattern string_pattern = Pattern.compile(regex_string);
        Pattern integer_pattern = Pattern.compile(regex_integer);
        Pattern float_pattern = Pattern.compile(regex_float);

        // match each element in the row
        // determine if an element is a string, int, or float
        for (int i = 0; i < split.length; i++)
        {
            Matcher string_matcher = string_pattern.matcher(split[i]);
            Matcher integer_matcher = integer_pattern.matcher(split[i]);
            Matcher float_matcher = float_pattern.matcher(split[i]);

            // added debug statements
            // if the regex matchers fail to find elements in a csv row,
            // then i'll need a way to check what data types are being found and where.
            if (string_matcher.find())
            {
                output.add(new Collection(string_matcher.group(1), DataType.STRING));
                logger.printf(LoggerLevel.DEBUG, "Line %d: Found a string type", line_number);
            }
            else if (float_matcher.find())
            {
                output.add(new Collection(float_matcher.group(1), DataType.FLOAT));
                logger.printf(LoggerLevel.DEBUG, "Line %d: Found a float type", line_number);
            }
            else if (integer_matcher.find())
            {
                output.add(new Collection(integer_matcher.group(1), DataType.INTEGER));
                logger.printf(LoggerLevel.DEBUG, "Line %d: Found an integer type", line_number);
            }
            else
            {
                logger.printf(LoggerLevel.WARNING, "Line %d: Found something strange while parsing row. Ignoring.", line_number);
            }
        }

        return output;
    }

    private static void partOne(String args[]) throws Exception
    {
        File file = null;

        // determine if the file given exists
        if (args.length > 0)
        {
            file = new File(args[0]);
            if (!file.exists())
            {
                System.out.printf("File `%s': No such file or directory.\n", args[0]);
                System.exit(1);
            }
        }
        else
        {
            System.out.println("Missing filename from parameters.\nusage: target.java <filename.csv>");
            System.exit(1);
        }

        // a lot of initalization
        Scanner scanner = new Scanner(file);
        ArrayList<ArrayList<Collection>> entire_file = new ArrayList<ArrayList<Collection>>();
        ArrayList<Collection> current_row = new ArrayList<Collection>();
        long total_frequency = 0;
        float total_percentage = 0;
        int average_frequency = 0;
        int total_numbers = 0;

        // go through each line in the file, parse it, and add it to memory
        int line_number = 0;
        while (scanner.hasNextLine())
        {
            current_row = parseRow(scanner.nextLine(), line_number);

            if (current_row.get(1).getType() == DataType.INTEGER)
            {
                total_frequency += Integer.parseInt(current_row.get(1).getData());
            }

            entire_file.add(current_row);

            line_number++;
        }

        // go through each row and perform intense calculations on them
        for (int i = 0; i < entire_file.size(); i++)
        {
            ArrayList<Collection> row = entire_file.get(i);

            // we want the best!
            if (row.get(1).getType() != DataType.INTEGER)
                continue;

            // this is very very bad converting a string to a float but oh well it works
            float float_data = Float.parseFloat(row.get(1).getData());
            float percentage = (float_data / total_frequency) * 100;
            System.out.printf("%s - %.1f%%\n", row.get(0).getData(), percentage);
            average_frequency += float_data;
            total_percentage += percentage;
            total_numbers++;
        }

        System.out.printf("Average Frequency: %d    Total Percentage: %.1f%%\n", average_frequency / total_numbers, total_percentage);

        // use this as a warning to the user in case the percentage doesn't make sense
        if (total_percentage > 100.1)
        {
            logger.println(LoggerLevel.ERROR, "total_percentage is over 100%, did something horrible happen?");
        }

        scanner.close();
    }

    private static void partTwo(String args[]) throws Exception
    {
        // initialize things
        Scanner scanner = new Scanner(System.in);
        String output_file = "users.cfg";
        File file = new File(output_file);

        // check if the file given exists
        if (file.exists() && !file.isDirectory())
        {
            // give the option to the user to overwrite
            // the current file
            System.out.printf("File `%s' exists. Overwrite? (yes/no): ", output_file);
            String input = scanner.nextLine();

            if (!input.toLowerCase().startsWith("y"))
            {
                System.out.println("Aborting.");
                System.exit(1);
            }
        }

        // initialize file writer & print writer
        FileWriter file_writer = new FileWriter(output_file);
        PrintWriter print_writer = new PrintWriter(file_writer);

        // initialize other stuff
        String current_name = "";
        String current_address = "";
        String current_city = "";
        String current_state = "";
        String current_zip = "";
        int num_users = 0;

        // get the number of users to configure
        while (true)
        {
            try
            {
                System.out.print("Enter how many users to add to the configuration: ");
                num_users = scanner.nextInt();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid number");
                scanner.next();
            }
        }

        // in the case that the scanner picks up a number that was
        // not what the user intended
        logger.printf(LoggerLevel.DEBUG, "Adding %d users to the configuration", num_users);

        // get data from user and write to file
        for (int i = 0; i < num_users; i++)
        {
            System.out.printf("User %d\n", i + 1);

            // questionable ethics happened here
            // we're throwing science at the wall to see what sticks
            if (i == 0)
                scanner.nextLine();

            // ask data from the user
            System.out.print("Enter your name: ");
            current_name = scanner.nextLine();

            System.out.print("Enter your address: ");
            current_address = scanner.nextLine();

            System.out.print("Enter your city: ");
            current_city = scanner.nextLine();

            System.out.print("Enter your state: ");
            current_state = scanner.nextLine();

            System.out.print("Enter your ZIP: ");
            current_zip = scanner.nextLine();

            // write data to the file
            try
            {
                print_writer.printf("user_%d_name=%s\n", i + 1, current_name);
                print_writer.printf("user_%d_address=%s\n", i + 1, current_address);
                print_writer.printf("user_%d_city=%s\n", i + 1, current_city);
                print_writer.printf("user_%d_state=%s\n", i + 1, current_state);
                print_writer.printf("user_%d_zip=%s\n", i + 1, current_zip);
            }
            catch (Exception e)
            {
                System.out.printf("Failed to write to file `%s': %s\n", output_file, e);
            }
        }

        // close file & scanner. also very important!
        print_writer.close();
        scanner.close();

        System.out.printf("Wrote user data to file `%s'.\n", output_file);
    }

    public static void main(String args[]) throws Exception
    {
        // do part one
        System.out.println("Part One");
        partOne(args);

        // do part two
        System.out.println("Part Two");
        partTwo(args);
    }
}
