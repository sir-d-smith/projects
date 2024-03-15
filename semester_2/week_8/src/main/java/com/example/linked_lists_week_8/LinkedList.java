package com.example.linked_lists_week_8;

import com.example.linked_lists_week_8.Node;

public class LinkedList<T>
{
    private Node<T> head;
    // Maximum number of times a loop can repeat while
    // searching for a node.
    // Critical when bugs happen and infinite loops occur.
    public final int max_nodes_can_search = 128;

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

    // TODO: add more methods to this class as the use cases increase.
}
