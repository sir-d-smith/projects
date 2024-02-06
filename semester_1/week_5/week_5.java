package com.example.sorting_week_5;

import java.util.*;

// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development I
// Instructor Mr. Gross


// Copyright (C) 2023  Daniel Smith

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
	public static int total_array_length = 100;

	// Method to shuffle a given array.
	// Uses the Fisher-Yates shuffle algorithm.
	public static void shuffleArray(int arr[]) {
		int n = arr.length;
		Random rand = new Random();

		for (int i = 0; i < arr.length; i++) {
			// Generate a random index, but not one
			// that we've already used.
			int j = i + rand.nextInt(n - i);

			// Swap the present element with a
			// random element.
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	//Swap function by kimg@techtrepacademy.com
	//this function will take a array of ints and the lower index as an int and then return the array with the two items swapped.
	static int[] swapTwoArrayElements(int[] arrayToSwap, int lowerIndex){
		int temp;
		temp=arrayToSwap[lowerIndex];
		arrayToSwap[lowerIndex]=arrayToSwap[lowerIndex+1];
		arrayToSwap[lowerIndex+1]=temp;
		return arrayToSwap;
	}

	// Method to sort a given array.
	// Uses the bubble sort algorithm.
	public static void bubbleSort(int arr[], boolean ascending)
	{
		boolean did_swap;

		// Loop through the length of the array.
		for (int i = 0; i < arr.length - 1; i++)
		{
			did_swap = false;

			// Loop through the unsorted parts of the array.
			for (int j = 0; j < (arr.length - 1 - i); j++)
			{
				// If the current element is bigger than the
				// next element and if we are sorting in an
				// ascending order, swap the current and next
				// elements.
				if ((arr[j] > arr[j + 1]) && ascending)
				{
					arr = swapTwoArrayElements(arr, j);
					did_swap = true;
				}

				// The same logic as above, but checking if the
				// current element is smaller than the next
				// element if we are sorting in a decending
				// order.
				else if ((arr[j] < arr[j + 1]) && !ascending)
				{
					arr = swapTwoArrayElements(arr, j);
					did_swap = true;
				}
			}

			// If we didn't swap any elements, we can break
			// out of the loop, since the array has been
			// sorted.
			if (!did_swap) break;
		}
	}

	// Method to check if a given array is sorted.
	// Doesn't use a particular algorithm.
	public static boolean array_is_sorted(int arr[], boolean ascending)
	{
		int previous_i = -1;
		for (int i : arr)
		{
			// If the current element is less than
			// the last element and the array is
			// ascending, or if the current element
			// is greater than the last element and
			// the array is descending, then return
			// false.
			if (
				((i < previous_i) && ascending) ||
				(i > previous_i) && !ascending
				)
			{
				return false;
			}

			previous_i = i;
		}
		// Otherwise, return true.
		return true;
	}

	// Method to print out a given array to the standard output.
	// Something important is supposed to be here.
	public static void print_array(int arr[])
	{
		// This assumes that the array length is
		// greater than one. This will break otherwise.
		// Too bad!
		for (int i = 0; i < arr.length - 1; i++)
		{
			System.out.print(arr[i]);
			System.out.print(", ");
		}
		System.out.println(arr[arr.length - 1]);
	}

	public static void main(String args[])
	{
		int[] arrayToSort={1,3,4,5,1,23,57,126,4,543,345,23,12,45,67,97};// this creates the array.

		// Print the array.
		System.out.println("Unsorted array:");
		print_array(arrayToSort);

		// Sort the array.
		bubbleSort(arrayToSort, true);

		// Print the array again.
		System.out.println("Sorted array:");
		print_array(arrayToSort);

		// Check if the array has been properly sorted.
		// If it isn't, the assertion will fail.
		assert array_is_sorted(arrayToSort, true) : "Array not sorted";

		// If it is, we give the user some dopamine
		// by printing it worked.
		System.out.println("The array was sorted correctly!");
	}
}