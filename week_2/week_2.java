package com.example.hello_world_week_2;

import java.util.*;

class App
{
	// global variables
	static String name = "Daniel Smith";
	static int    age  = 17;

	static int    num_siblings = 3;

	public static void main(String[] args)
	{
		// first modification
		System.out.println("Hello, world!");
		System.out.printf("My name is %s. I am %d years old.\n", name, age);
		System.out.printf("I have %d other siblings, one brother and two sisters, and I am the oldest of all.\n", num_siblings);

		// second modification
		System.out.println("Mary is 14 and is the older middle child.");
		System.out.println("Sean is 10 and is the younger middle child.");
		System.out.println("Clara is 8 and is the youngest.");
	}
}
