package com.example.hello_world_week_3;

import java.util.*;

class App
{
	// global variables
	static int size_array = 100;

	public static void main(String[] args)
	{
		int int_array[] = new int[size_array];
		for (int i = 0; i < size_array; i++)
		{
			int_array[i] = i + 1;
		}
		
		int total_sum = 0;
		for (int i = 0; i < size_array; i++)
		{
			int value = int_array[i];
			String is_even_odd = ((value + 1) % 2) == 0 ? "even" : "odd";
			System.out.printf("%d: is %s\n", value, is_even_odd);
			total_sum += value;
		}
		System.out.printf("total sum: %d\n", total_sum);
	}
}