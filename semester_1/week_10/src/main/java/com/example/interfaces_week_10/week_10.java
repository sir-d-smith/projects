package com.example.interfaces_week_10;

import java.util.*;
import java.util.Random;

import com.example.interfaces_week_10.Location.Hemisphere;
import com.example.interfaces_week_10.Location.LocationType;

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
    private static Random rand;

    // Generates a list of 10 random planes.
    private static ArrayList<Plane> generatePlanes()
    {
        // quite long and ugly, but it works
        List<String> list = Arrays.asList("4KZMOBT", "14QH0M1", "TEKQHUW", "THPG4F", "TM02M15", "NMTDOS", "OIM0H", "6Y34GM", "LU0LEEA", "AUS7ENB", "HRTHB7", "4146DWO",
                                          "NVUH5L", "I1J62UQ", "Q6S7GTW");

        ArrayList<Plane> planes = new ArrayList<Plane>();
        for (int i = 0; i < 10; i++)
        {
            rand = new Random();
            planes.add(new Plane(
                "",
                "",
                list.get(rand.nextInt(list.size())),
                "",
                false,
                "",
                null,
                null,
                null
            ));
        }

        return planes;
    }

    public static void main(String args[])
    {
        // unsorted array of planes
        ArrayList<Plane> planes = generatePlanes();

        // printing out array before sorting
        System.out.println("Plane callsigns before sorting:");
        for (Plane plane : planes)
        {
            System.out.println(plane.getCallsign());
        }

        // a bit of padding in the output so it looks good
        System.out.print("\n\n");

        // finally sorting the array!
        Collections.sort(planes);

        // printing out the array after sorting
        System.out.println("Plane callsigns after sorting:");
        for (Plane plane : planes)
        {
            System.out.println(plane.getCallsign());
        }
    }
}