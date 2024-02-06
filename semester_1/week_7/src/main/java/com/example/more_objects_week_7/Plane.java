package com.example.more_objects_week_7;

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
    private String identifier;
    private String owner;
    private boolean is_flying;
    private String flight_number;
    private String departed_from;
    private String arriving_to;

    // The blank constructor for the class.
    public Plane()
    {
        // Since no parameters were given to
        // the constructor, we set all attributes
        // to a blank value.
        this.manufacturer = "";
        this.type = "";
        this.identifier = "";
        this.owner = "";
        this.is_flying = false;
        this.flight_number = "";
        this.departed_from = "";
        this.arriving_to = "";
    }

    // The other constructor for this class.
    public Plane(
        String manufacturer,
        String type,
        String identifier,
        String owner,
        boolean is_flying,
        String flight_number,
        String departed_from,
        String arriving_to
    )
    {
        // Since parameters are being given
        // in this constructor, we must set
        // the parameters we are given to our
        // attributes.
        this.manufacturer = manufacturer;
        this.type = type;
        this.identifier = identifier;
        this.owner = owner;
        this.is_flying = is_flying;
        this.flight_number = flight_number;
        this.departed_from = departed_from;
        this.arriving_to = arriving_to;
    }

    // The getters for this class.
    // Each returns a private attribute
    // without overwriting it or conflicting
    // with permissions.
    String getManufacturer()  { return this.manufacturer; }
    String getType()          { return this.type; }
    String getIdentifier()    { return this.identifier; }
    String getOwner()         { return this.owner; }
    boolean getIsFlying()     { return this.is_flying; }
    String getFlightNumber()  { return this.flight_number; }
    String getDepartedFrom() { return this.departed_from; }
    String getArrivingTo()    { return this.arriving_to; }

    // The setters for this class.
    // Each takes in an argument to overwrite
    // the current attribute in the class.
    // Please note -- not all attributes have
    // setters. Things like the manufacturer
    // shouldn't be overwritten by other classes.
    void setIdentifier(String identifier)      { this.identifier = identifier; }
    void setOwner(String owner)                { this.owner = owner; }
    void setIsFlying(boolean is_flying)        { this.is_flying = is_flying; }
    void setFlightNumber(String flight_number) { this.flight_number = flight_number; }
    void setDepartedfrom(String departed_from) { this.departed_from = departed_from; }
    void setArrivingTo(String arriving_to)     { this.arriving_to = arriving_to; }

    // The string method for this class.
    // When this class is called as a string, 
    // this method will be called.
    @Override
    public String toString()
    {
        return "Plane(" +
               "manufacturer: " + this.manufacturer + ", " +
               "type: " + this.type + ", " +
               "identifier: " + this.identifier + ", " +
               "owner: " + this.owner + ", " +
               "is_flying: " + this.is_flying + ", " +
               "flight_number: " + this.flight_number + ", " +
               "departed_from: " + this.departed_from + ", " +
               "arriving_to: " + this.arriving_to + ")"
        ;
    }
}