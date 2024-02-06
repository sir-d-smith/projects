package com.example.extending_objects_week_9;

import com.example.extending_objects_week_9.Location.Hemisphere;
import com.example.extending_objects_week_9.Location.LocationType;

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
        // first plane -- is an original plane class
        Plane plane1 = new Plane();
        plane1.setOwner("Daniel Smith");
        plane1.setCallsign("KELS");
        plane1.setIsFlying(false);

        // second plane -- now is a cargo plane
        CargoPlane plane2 = new CargoPlane(
            "Boeing",
            "B763",
            "FDX2945",
            "FedEx",
            10,
            false,
            null,
            null,
            null,
            null  
        );

        // add some cargo to the first cargo plane
        for (int i = 0; i < 5; i++)
        {
            Cargo j = new Cargo();
            j.setTitle("Some very important cargo");
            j.setId("SOME_ID:" + i);
            j.setContents("SOME_CARGO-" + i);
            j.setMass(23.1f);
            plane2.loadCargo(j);
        }

        // edit some parameters of plane2 to make it flying
        plane2.setIsFlying(true);
        plane2.setDepartingfrom(new Location(
            39.0, 51.0, 42.0,
            104.0, 40.0, 23.0,
            Hemisphere.NORTH,
            Hemisphere.WEST,
            1656.0,
            "KDEN",
            LocationType.AIRPORT
        ));
        plane2.setArrivingTo(new Location(
            35.0, 2.0, 33.0,
            89.0, 58.0, 36.0,
            Hemisphere.NORTH,
            Hemisphere.WEST,
            104.0,
            "KMEM",
            LocationType.AIRPORT
        ));
        plane2.setLocation(new Location(
            37.63704434285557, -104.2348761155192,
            9100.0,
            null,
            LocationType.AIRPLANE
        ));
        plane2.setFlightNumber("FX5622");

        // print the current plane2 variable
        System.out.println(plane2);


        // third plane -- again a cargo plane, but idle
        CargoPlane plane3 = new CargoPlane(
            "Boeing",
            "B763",
            "FDX618",
            "FedEx",
            10,
            false,
            null,
            null,
            null,
            null  
        );

        // we don't need to add too many parameters
        // as the plane is idle
        plane3.setLocation(new Location(
            43.5671711354671, -116.22077795583724,
            875.0,
            null,
            LocationType.AIRPLANE
        ));

        // print the current plane3 variable
        System.out.println(plane3);
    }
}