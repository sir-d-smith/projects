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


//Simple horse class created by Kim Gross
//for CTE Software Development class 2022
class Horse {

    private String name;

    private int birthYear;

    public Horse(String horseName, int year){ //horse constructor needs its name and birth year.

        name=horseName; //assigns the name of the horse to the horseName which was sent in the construtor.

        birthYear=year; //assigns the year to the birthYear that was sent in the constructor;

    }
    
    public String toString(){

        return String.format("name: %s, birthYear: %d", this.name, this.birthYear);

    }

    public String getName()
    {
        return this.name;
    }

    public int getBirthYear()
    {
        return this.birthYear;
    }

    public void setName(String newName){

        name=newName;

    }

    public void setBirthYear(int new_year)
    {
        this.birthYear = new_year;
    }

    public static void main(String args[])
    {
        Horse horse0 = new Horse("Trigger", 2000);
        Horse horse1 = new Horse("Silver", 1998);
        Horse horse2 = new Horse("Scout", 2003);

        System.out.println(horse0);
        System.out.println(horse1);
        System.out.println(horse2);
    }
}