package com.example.stacks_and_queues_week_9;

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


public class LinkedList<T>
{
    private Node<T> head;
    private final boolean debug_mode = false;
    // Maximum number of times a loop can repeat while
    // searching for a node.
    // Critical when bugs happen and infinite loops occur.
    // Only activated when debug_mode is set to true.
    public final int max_nodes_can_search = debug_mode ? 128 : Integer.MAX_VALUE;

    // Main constructors.
    public LinkedList()
    {
        this.head = null;
    }

    // Constructor that allows the first node.
    public LinkedList(Node<T> head)
    {
        this.head = head;
    }


    // Print the contents of the linked list without hassle.
    public void printContents()
    {
        Node<T> current_node = this.getFirstNode();
        for (int i = 0; i < this.max_nodes_can_search; i++)
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
    }


    // Methods that deal with node structure.
    // Picks out the last node from the list tree.
    public Node<T> getLastNode()
    {
        Node<T> current_node = null;

        for (int i = 0; i < max_nodes_can_search; i++)
        {
            if (current_node == null)
            {
                current_node = head;
            }
            if (current_node.getNext() == null)
            {
                return current_node;
            }
            else
            {
                current_node = current_node.getNext();
            }
        }

        return null;
    }

    // Picks out the head from the list tree.
    public Node<T> getFirstNode()
    {
        return this.head;
    }

    // Appends a node to the last part of the list tree.
    public void appendLast(Node<T> node)
    {
        if (this.head != null)
        {
            node.setPrevious(this.getLastNode());
            this.getLastNode().setNext(node);
        }
        else
        {
            this.head = node;
        }
    }

    // Appends a node to the first part of the list tree.
    public void appendFirst(Node<T> node)
    {
        Node<T> old_head = new Node<T>(this.head.get(), this.head.getKey());
        old_head.setNext(this.head.getNext());
        old_head.setPrevious(node);

        this.head = node;
        this.head.setNext(old_head);
        this.head.setPrevious(null);
    }

    // Removes the last node in the list.
    public void removeLast()
    {
        Node<T> last_node = this.getLastNode();

        // check if the last node was the head. if it was, delete it.
        if (last_node.getPrevious() == null && this.head.getNext() == null)
        {
            this.head = null;
        }
        else
        {
            last_node.getPrevious().setNext(null);
        }
    }

    // Removes the first node in the list.
    public void removeFirst()
    {
        Node<T> new_head = this.head.getNext();

        // check if the head is the only item. if it is, delete it.
        if (new_head == null)
        {
            this.head = null;
        }
        else
        {
            this.head = new_head;
        }
    }

    // TODO: add more methods to this class as the use cases increase.
}
