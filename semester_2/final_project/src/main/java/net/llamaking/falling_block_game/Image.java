package net.llamaking.falling_block_game;

import java.nio.ByteBuffer;

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


// Basic class to contain image data.
// Would be helpful to use proper getters/setters but oh well.
public class Image
{
    public ByteBuffer pixel_data;
    public int x;
    public int y;
    public int channels;
    public int texture_id;
    public String path;
    public String name;
}
