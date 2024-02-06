package com.example.more_objects_week_7;

import com.example.more_objects_week_7.Plane;

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
    public static void main(String args[])
    {
        // first plane -- is empty
        Plane plane1 = new Plane();

        // second plane -- is empty, but we supply
        // information later
        Plane plane2 = new Plane();
        plane2.setOwner("Daniel Smith");
        plane2.setIdentifier("KELS");
        plane2.setIsFlying(false);

        // third plane -- we supply information to the
        // constructor this time
        Plane plane3 = new Plane(
            "Cessna",
            "Cessna 172",
            "KELS",
            "Daniel Smith",
            false,
            null,
            null,
            null  
        );

        // print the current plane3 variable
        System.out.println(plane3);

        // edit some parameters of plane3 to make it flying
        plane3.setIsFlying(true);
        plane3.setDepartedfrom("KBOI");
        plane3.setArrivingTo("KMAN");
        plane3.setFlightNumber("FGB212");

        // print the current plane3 variable
        System.out.println(plane3);
    }
}