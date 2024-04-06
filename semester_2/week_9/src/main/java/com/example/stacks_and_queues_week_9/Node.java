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


public class Node<T>
{
    // Initialize private variables.
    private T data;
    private int key;
    private Node<T> previous;
    private Node<T> next;

    // Generates a random number between 0 and 2147483647.
    private int generateKey()
    {
        return (int)(Math.random() * Integer.MAX_VALUE + 1);
    }

    // Main constructors.
    public Node()
    {
        this.data = null;
        this.next = null;
        this.previous = null;
        this.key = this.generateKey();
    }

    // Constructor that allows data.
    public Node(T data)
    {
        this.data = data;
        this.next = null;
        this.previous = null;
        this.key = this.generateKey();
    }

    // Constructor that allows data and a custom key.
    public Node(T data, int key)
    {
        this.data = data;
        this.next = null;
        this.previous = null;
        this.key = key;
    }

    
    // Setters and getters for private data in the node.
    public void set(T data)
    {
        this.data = data;
    }

    public T get()
    {
        return this.data;
    }

    public void setNext(Node<T> next)
    {
        this.next = next;
    }

    public Node<T> getNext()
    {
        return this.next;
    }

    public void setPrevious(Node<T> previous)
    {
        this.previous = previous;
    }

    public Node<T> getPrevious()
    {
        return this.previous;
    }

    public int getKey()
    {
        return this.key;
    }
}
