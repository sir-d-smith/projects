package com.example.stacks_and_queues_week_9;

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


// This class serves no more than to be an example.
public class PrinterJob
{
    private String id;
    private String text;
    private boolean completed;

    public PrinterJob(String id, String text)
    {
        this.id = id;
        this.text = text;
        this.completed = false;
    }

    public String getId()
    {
        return this.id;
    }

    public String getText()
    {
        return this.text;
    }

    public boolean getCompleted()
    {
        return this.completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public String toString()
    {
        return "PrinterJob(" +
               "id: " + this.id + ", " +
               "text: '" + this.text + "', " +
               "completed: " + Boolean.toString(this.completed) + ")";
    }
}
