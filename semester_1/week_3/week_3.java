package com.example.hello_world_week_3;

import java.util.*;

class App
{
	// global variables
	static int num_loops = 100;

	public static void main(String[] args)
	{
		int total_sum = 0;
		for (int i = 0; i < num_loops; i++)
		{
			String is_even_odd = ((i + 1) % 2) == 0 ? "even" : "odd";
			System.out.printf("%d: is %s\n", i + 1, is_even_odd);
			total_sum += i + 1;
		}
		System.out.printf("total sum: %d\n", total_sum);
	}
}
