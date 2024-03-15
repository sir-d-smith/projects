package com.example.linked_lists_week_8;

import java.io.*;
import java.util.*;
import com.example.linked_lists_week_8.LinkedList;
import com.example.linked_lists_week_8.Node;

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
    public static void main(String args[])
    {
        // Initialize variables.
        Scanner scanner = new Scanner(System.in);
        LinkedList bucket = new LinkedList<Integer>();
        int num_items = -1;

        // Ask the user how many numbers to add to the bucket.
        System.out.println("You will be adding a number of items to a linked list.");
        while (true)
        {
            try
            {
                System.out.print("Enter how many items to add to the linked list: ");
                num_items = scanner.nextInt();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid number");
                scanner.next();
            }
        }

        // Add numbers to the bucket.
        for (int i = 0; i < num_items; i++)
        {
            while (true)
            {
                try
                {
                    System.out.print("Enter a number to add to the linked list: ");
                    int number = scanner.nextInt();
                    bucket.appendLast(new Node<Integer>(number));
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("Invalid number");
                    scanner.next();
                }
            }
        }

        // Print the bucket in normal order.
        Node<Integer> current_node = bucket.getFirstNode();
        System.out.println("Contents of linked list (in order):");
        for (int i = 0; i < bucket.max_nodes_can_search; i++)
        {
            if (current_node.getNext() != null)
            {
                System.out.println(current_node.get());
                current_node = current_node.getNext();
            }
            else
            {
                System.out.println(current_node.get());
                break;
            }
        }

        // Print the bucket in reverse order.
        current_node = bucket.getLastNode();
        System.out.println("Contents of linked list (in reverse order):");
        for (int i = 0; i < bucket.max_nodes_can_search; i++)
        {
            if (current_node.getPrevious() != null)
            {
                System.out.println(current_node.get());
                current_node = current_node.getPrevious();
            }
            else
            {
                System.out.println(current_node.get());
                break;
            }
        }
    }
}
