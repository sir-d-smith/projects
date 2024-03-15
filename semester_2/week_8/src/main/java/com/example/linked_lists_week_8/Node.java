package com.example.linked_lists_week_8;

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
