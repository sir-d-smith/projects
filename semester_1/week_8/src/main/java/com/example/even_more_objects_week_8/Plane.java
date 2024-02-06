package com.example.even_more_objects_week_8;

import java.util.regex.Pattern;

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


public class Plane
{
    // The class' private attributes.
    private String manufacturer;
    private String type;
    private String callsign;
    private String owner;
    private boolean is_flying;
    private String flight_number;
    private Location location;
    private Location departing_from;
    private Location arriving_to;

    // The blank constructor for the class.
    public Plane()
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
    }

    // The other constructor for this class.
    public Plane(
        String manufacturer,
        String type,
        String callsign,
        String owner,
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
        this.manufacturer = manufacturer;
        this.type = type;
        setCallsign(callsign);
        this.owner = owner;
        this.is_flying = is_flying;
        setFlightNumber(flight_number);
        this.location = location;
        this.departing_from = departing_from;
        this.arriving_to = arriving_to;
    }

    // The getters for this class.
    // Each returns a private attribute
    // without overwriting it or conflicting
    // with permissions.
    String getManufacturer()  { return this.manufacturer; }
    String getType()          { return this.type; }
    String getCallsign()      { return this.callsign; }
    String getOwner()         { return this.owner; }
    boolean getIsFlying()     { return this.is_flying; }
    String getFlightNumber()  { return this.flight_number; }
    Location getLocation()      { return this.location; }
    Location getDepartingFrom() { return this.departing_from; }
    Location getArrivingTo()    { return this.arriving_to; }

    // The setters for this class.
    // Each takes in an argument to overwrite
    // the current attribute in the class.
    // Please note -- not all attributes have
    // setters. Things like the manufacturer
    // shouldn't be overwritten by other classes.
    void setOwner(String owner)                { this.owner = owner; }
    void setIsFlying(boolean is_flying)        { this.is_flying = is_flying; }
    void setLocation(Location location)            { this.location = location; }
    void setDepartingfrom(Location departing_from) { this.departing_from = departing_from; }
    void setArrivingTo(Location arriving_to)       { this.arriving_to = arriving_to; }

    // Special setters for callsigns and
    // flight numbers, as each require a specific
    // regular expression to match against.
    boolean setCallsign(String callsign)
    {
        if (callsign == null) return false;

        if (Pattern.compile("^[A-Z0-9]{1,10}$").matcher(callsign).matches())
        {
            this.callsign = callsign;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    boolean setFlightNumber(String flight_number)
    {
        if (flight_number == null) return false;

        if (Pattern.compile("^[A-Z]{2,4}[A-Z0-9]{1,5}$").matcher(flight_number).matches())
        {
            this.flight_number = flight_number;
            return true;
        }
        else
        {
            return false;
        }
    }

    // The string method for this class.
    // When this class is called as a string, 
    // this method will be called.
    @Override
    public String toString()
    {
        return "Plane(" +
               "manufacturer: " + this.manufacturer + ", " +
               "type: " + this.type + ", " +
               "callsign: " + this.callsign + ", " +
               "owner: " + this.owner + ", " +
               "is_flying: " + this.is_flying + ", " +
               "flight_number: " + this.flight_number + ", " +
               "location: " + this.location + ", " +
               "departing_from: " + this.departing_from + ", " +
               "arriving_to: " + this.arriving_to + ")"
        ;
    }
}