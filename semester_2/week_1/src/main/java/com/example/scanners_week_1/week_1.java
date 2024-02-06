package com.example.scanners_week_1;

import java.util.*;

// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development I
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
	public static void main(String args[])
	{
		// initialize the scanner
		Scanner scanner = new Scanner(System.in);
		
		// initialize the variables
		int read_integer;
		float read_float;
		String read_line;
		
		// read the integer
		while (true)
		{
			try
			{
				System.out.print("Enter an integer: ");
				read_integer = scanner.nextInt();
				break;
			}
			// in the case we don't get an optimal input,
			// we ask the user again for input.
			catch (Exception e)
			{
				System.out.println("Invalid integer");
				scanner.next();
			}
		}

		// read the float
		while (true)
		{
			try
			{
				System.out.print("Enter a float: ");
				read_float = scanner.nextFloat();
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid float");
				scanner.next();
			}
		}

		// read the line
		while (true)
		{
			try
			{
				// it took me SO LONG to realize what the problem was
				// with not getting correct text from the user.
				// it would skip over the scanner on line 67 and proceed
				// onwards. very annoying.
				// turns out i needed to read the line previous to the
				// text given by the user for some reason so it doesn't
				// spontaneously combust.
				// AAAGGHH.
				scanner.nextLine();

				System.out.print("Enter some text: ");
				read_line = scanner.nextLine();
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
				// scanner.next(); // ?
				// at this point, if something has gone wrong, it
				// DEFINITLEY wasn't an invalid input. but whatever.
			}
		}
		
		// print out the integer, float, & line
		System.out.print("The integer I read: "); System.out.println(read_integer);
		System.out.print("The float I read: "); System.out.println(read_float);
		System.out.print("The text I read: "); System.out.println(read_line);

		// read the first and last name
		String first_name = "";
		String last_name = "";
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

		// print out the greeting
		System.out.printf("Hello Mr. or Ms. %s. May I call you by %s?\n", last_name, first_name);

		// close the scanner. very important!
		scanner.close();
	}
}
