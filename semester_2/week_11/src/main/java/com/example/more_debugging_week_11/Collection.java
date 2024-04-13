package com.example.more_debugging_week_11;

// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development II
// Instructor Mr. Gross


// Copyright (C) 2024  Daniel Smith

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


public class Collection
{
    private String data;
    private DataType type;

    public Collection()
    {
        this.data = null;
        this.type = null;
    }

    public Collection(String data, DataType type)
    {
        this.data = data;
        this.type = type;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return this.data;
    }

    public void setType(DataType type)
    {
        this.type = type;
    }

    public DataType getType()
    {
        return this.type;
    }
}