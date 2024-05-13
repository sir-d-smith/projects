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


public class Block
{
    private long id;
    private BlockColor color;
    private int x;
    private int y;

    public static final int BLOCK_SIZE = 25;

    public Block(long id, BlockColor color, int x, int y)
    {
        this.id = id;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public long getId()
    {
        return this.id;
    }

    public BlockColor getColor()
    {
        return this.color;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
}
