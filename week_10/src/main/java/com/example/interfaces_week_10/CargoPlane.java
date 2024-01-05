package com.example.interfaces_week_10;

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


public class CargoPlane extends Plane
{
    // Private attributes for this class.
    private ArrayList<Cargo> cargo;
    private int max_cargo;

    // We have to steal the constructors from the original
    // Plane class due to the fact that constructors are not
    // inherited by child classes.
    public CargoPlane()
    {
        // Since no parameters were given to
        // the constructor, we set all attributes
        // to a blank value.
        this.manufacturer = "";
        this.type = "";
        this.callsign = "";
        this.owner = "";
        this.is_flying = false;
        this.flight_number = "";
        this.location = null;
        this.departing_from = null;
        this.arriving_to = null;
        this.cargo = new ArrayList<Cargo>();
        this.max_cargo = 0;
    }

    public CargoPlane(
        String manufacturer,
        String type,
        String callsign,
        String owner,
        int max_cargo,
        boolean is_flying,
        String flight_number,
        Location location,
        Location departing_from,
        Location arriving_to
    )
    {
        // Since parameters are being given
        // in this constructor, we must set
        // the parameters we are given to our
        // attributes.
        super(manufacturer, type, callsign, owner, is_flying, flight_number, location, departing_from, arriving_to);
        this.cargo = new ArrayList<Cargo>();
        this.max_cargo = max_cargo;
    }

    // Some extra getters for the cargo attribute.
    public ArrayList<Cargo> getAllCargo() { return this.cargo; }

    public Cargo getCargo(String id)
    {
        for (Cargo i : this.cargo)
        {
            if (i.getId().equals(id))
                return i;
        }
        return null;
    }

    // What makes this different from getCargo is that
    // this both returns the cargo and deletes or "unloads"
    // it from the Cargo Plane.
    public Cargo unloadCargo(String id)
    {
        for (int i = 0; i < this.cargo.size(); i++)
        {
            if (this.cargo.get(i).getId().equals(id))
            {
                Cargo temp = this.cargo.get(i);
                this.cargo.remove(i);
                return temp;
            }
        }
        return null;
    }

    // Loading cargo onto the Cargo Plane.
    public boolean loadCargo(Cargo cargo)
    {
        if (this.cargo.size() > this.max_cargo)
            return false;

        this.cargo.add(cargo);
        return true;
    }

    // The string method for this class.
    // This cannot be inherited, it must contain
    // the cargo we are transporting.
    // When this class is called as a string, 
    // this method will be called.
    @Override
    public String toString()
    {
        return "CargoPlane(\n" +
               "\tmanufacturer: " + this.manufacturer + ",\n" +
               "\ttype: " + this.type + ",\n" +
               "\tcallsign: " + this.callsign + ",\n" +
               "\towner: " + this.owner + ",\n" +
               "\tamount of cargo: " + this.cargo.size() + ",\n" +
               "\tcargo: " + this.cargo + ",\n" +
               "\tis_flying: " + this.is_flying + ",\n" +
               "\tflight_number: " + this.flight_number + ",\n" +
               "\tlocation: " + this.location + ",\n" +
               "\tdeparting_from: " + this.departing_from + ",\n" +
               "\tarriving_to: " + this.arriving_to + "\n)"
        ;
    }
}
