package com.example.exceptions_week_11;

// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development I
// Instructor Mr. Gross


// Copyright (C) 2023  Daniel Smith

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
        // out of bounds exception handling
        System.out.println("Performing out of bounds");
        try
        {
            // set up an array and print each item
            int silly_array[] = { 1, 3, 5, 7, 9 };
            for (int i = 0; i < silly_array.length + 1; i++)
            {
                System.out.println(silly_array[i]);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Array index was out of bounds!");
        }

        // divide by zero exception handling
        System.out.println("Performing dividing by zero");
        try
        {
            // two arrays: numerators and denominators
            // we go through each and calculate the output
            int numerators[] = { 2, 62, 194, 9, 3 };
            int denominators[] = { 29, 104, 5, 0, 1 };

            for (int i = 0; i < numerators.length; i++)
            {
                System.out.println(numerators[i] / denominators[i]);
            }
        }
        catch (ArithmeticException e)
        {
            System.out.println("Divided by zero! Uh oh");
        }
    }
}
