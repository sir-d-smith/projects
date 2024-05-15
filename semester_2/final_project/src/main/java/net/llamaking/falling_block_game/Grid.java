package net.llamaking.falling_block_game;

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


public class Grid<T>
{
    // Private variables.
    private int width;
    private int height;
    private T[][] grid;

    // Constructor. Initializes the grid array.
    @SuppressWarnings("unchecked")
    public Grid(int width, int height)
    {
        this.width = width;
        this.height = height;
        // scary!
        this.grid = (T[][]) new Object[this.width][this.height];
    }

    // Getters and setter.
    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public T get(int x, int y)
    {
        if ((x >= this.width ||
                x <= -1) ||
                (y >= this.height ||
                y <= -1))
        {
            return null;
        }

        return this.grid[x][y];
    }

    public boolean set(T value, int x, int y)
    {
        if ((x >= this.width ||
                x <= -1) ||
                (y >= this.height ||
                y <= -1))
        {
            return false;
        }

        this.grid[x][y] = value;
        return true;
    }
}
