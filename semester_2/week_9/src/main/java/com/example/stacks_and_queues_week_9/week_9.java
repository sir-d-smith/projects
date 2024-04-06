package com.example.stacks_and_queues_week_9;

import java.util.*;

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
    // Using the same scanner across the class is better than
    // opening and closing the same scanner in each method.
    private static Scanner scanner;

    private static void userTestStack()
    {
        // Initialize variables.
        Stack<Integer> stack = new Stack<Integer>();
        int num_items = -1;

        // Ask the user how many numbers to add to the stack.
        System.out.println("You will be adding a number of items to a stack.");
        while (true)
        {
            try
            {
                System.out.print("Enter how many items to add to the stack: ");
                num_items = scanner.nextInt();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid number");
                scanner.next();
            }
        }

        // Add numbers to the stack.
        for (int i = 0; i < num_items; i++)
        {
            while (true)
            {
                try
                {
                    System.out.print("Enter a number to add to the stack: ");
                    int number = scanner.nextInt();
                    stack.push(number);
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("Invalid number");
                    scanner.next();
                }
            }
        }

        // Print contents of the stack.
        System.out.println("Contents of the stack in order:");
        stack.printContents();

        // Pop data from the stack & give it to the user.
        int popped_data = stack.pop();
        System.out.printf("Popped data from the stack: %d\n", popped_data);
    }

    private static void userTestQueue()
    {
        // Initialize variables.
        Queue<Integer> queue = new Queue<Integer>();
        int num_items = -1;

        // Ask the user how many numbers to add to the queue.
        System.out.println("You will be adding a number of items to a queue.");
        while (true)
        {
            try
            {
                System.out.print("Enter how many items to add to the queue: ");
                num_items = scanner.nextInt();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid number");
                scanner.next();
            }
        }

        // Add numbers to the queue.
        for (int i = 0; i < num_items; i++)
        {
            while (true)
            {
                try
                {
                    System.out.print("Enter a number to add to the queue: ");
                    int number = scanner.nextInt();
                    queue.enqueue(number);
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("Invalid number");
                    scanner.next();
                }
            }
        }

        // Print contents of the queue.
        System.out.println("Contents of the queue in order:");
        queue.printContents();

        // Dequeue data from the queue & give it to the user.
        int dequeued_data = queue.dequeue();
        System.out.printf("Dequeued data from the queue: %d\n", dequeued_data);
    }

    // Testing the stack class.
    private static void testStack()
    {
        Stack<Integer> stack = new Stack<Integer>();

        // add content to the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("In stack:");
        stack.printContents();

        // pop the last number on the stack for funzies
        // casting nonsense
        int popped_data = (int)stack.pop();

        System.out.printf("Popped data: %d\n", popped_data);

        System.out.println("Remaining in stack:");
        stack.printContents();
    }

    // Testing the queue class.
    private static void testQueue()
    {
        Queue<PrinterJob> queue = new Queue<PrinterJob>();

        // add content to the queue
        queue.enqueue(new PrinterJob("1", "This is some text."));
        queue.enqueue(new PrinterJob("2", "More important text !!"));
        queue.enqueue(new PrinterJob("3", "Dingus"));

        System.out.println("Jobs in queue:");
        queue.printContents();

        // dequeue the first job in the list to do stuff with
        // more casting nonsense
        PrinterJob popped_job = (PrinterJob)queue.dequeue();

        System.out.printf("Dequeued data:\n%s\n", popped_job);

        System.out.println("Remaining jobs in queue:");
        queue.printContents();
    }

    public static void main(String args[])
    {
        // Create the scanner.
        scanner = new Scanner(System.in);

        // Conducting part one.
        System.out.println("Part one");
        userTestStack();
        userTestQueue();

        // Conducting part two.
        System.out.println("Part two");

        // Stack testing.
        System.out.println("Testing the stack");
        testStack();

        // Queue testing.
        System.out.println("Testing the queue");
        testQueue();

        // Close the scanner.
        scanner.close();
    }
}
