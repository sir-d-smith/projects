package com.example.interfaces_week_10;

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


public class Cargo
{
    private String title;
    private String id;
    private String contents;
    private float mass;

    public Cargo()
    {
        this.title = "";
        this.id = "";
        this.contents = "";
        this.mass = 0.0f;
    }

    String getTitle()    { return this.title; }
    String getId()       { return this.id; }
    String getContents() { return this.contents; }
    float  getMass()     { return this.mass; }

    void setTitle(String title)       { this.title = title; }
    void setId(String id)             { this.id = id; }
    void setContents(String contents) { this.contents = contents; }
    void setMass(float mass)          { this.mass = mass; }

    @Override
    public String toString()
    {
        return "Cargo(" +
               "title: " + this.title + ", " +
               "id: " + this.id + ", " +
               "contents: " + this.contents + ", " +
               "mass: " + this.mass + ")";
    }
}
