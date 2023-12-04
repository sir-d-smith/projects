package com.example.even_more_objects_week_8;

import com.example.even_more_objects_week_8.Location.Hemisphere;
import com.example.even_more_objects_week_8.Location.LocationType;

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
        plane2.setCallsign("KELS");
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
            null,
            null  
        );

        // print the current plane3 variable
        System.out.println(plane3);

        // edit some parameters of plane3 to make it flying
        plane3.setIsFlying(true);
        plane3.setDepartingfrom(new Location(
            43.0, 33.0, 52.0,
            116.0, 13.0, 22.0,
            Hemisphere.NORTH,
            Hemisphere.WEST,
            875.0,
            "KBOI",
            LocationType.AIRPORT
        ));
        plane3.setArrivingTo(new Location(
            43.0, 34.0, 53.0,
            116.0, 31.0, 23.0,
            Hemisphere.NORTH,
            Hemisphere.WEST,
            773.0,
            "KMAN",
            LocationType.AIRPORT
        ));
        plane3.setLocation(new Location(
            43.574800076641175, -116.41699743659879,
            3500.0,
            null,
            LocationType.AIRPLANE
        ));
        plane3.setFlightNumber("FGB212");

        // print the current plane3 variable
        System.out.println(plane3);
    }
}