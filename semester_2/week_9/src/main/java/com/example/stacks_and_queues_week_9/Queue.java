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


public class Queue<T>
{
    private LinkedList<T> list;

    // Main constuctor.
    public Queue()
    {
        this.list = new LinkedList<T>();
    }

    // Pushes data to the queue.
    public void enqueue(T data)
    {
        list.appendLast(new Node<T>(data));
    }
    
    // Pops data & returns data from the queue.
    public T dequeue()
    {
        Node<T> node = list.getFirstNode();
        list.removeFirst();
        return node.get();
    }

    // Wrapper for LinkedList's printContents.
    public void printContents()
    {
        this.list.printContents();
    }
}
