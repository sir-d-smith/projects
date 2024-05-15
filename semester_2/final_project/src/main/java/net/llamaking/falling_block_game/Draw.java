package net.llamaking.falling_block_game;

import org.lwjgl.opengl.GL11;

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


public class Draw 
{
    // Private variables we get from the main class.
    private int window_width;
    private int window_height;

    // Constructor class.
    public Draw(int window_width, int window_height)
    {
        this.window_width = window_width;
        this.window_height = window_height;
    }

    // Update the window width and height so the drawer knows where to draw.
    public void updateWindow(int window_width, int window_height)
    {
        this.window_width = window_width;
        this.window_height = window_height;
    }

    // Convert pixel coordinates to -1.0f through 1.0f, as that's what the GPU wants.  
    private float xPixelToGLCoordinate(int x)
    {
        return (2.0f * x) / window_width - 1.0f;
    }

    private float yPixelToGLCoordinate(int y)
    {
        return 1.0f - (2.0f * y) / window_height;
    }

    private float xWidthToGLDelta(int x)
    {
        return (2.0f * (x + (window_width/2))) / window_width - 1.0f;
    }

    private float yWidthToGLDelta(int y)
    {
        return 1.0f - (2.0f * (y + (window_height/2))) / window_height;
    }

    // Draws a square at the desired location on the window.
    public void drawSquare(int x, int y, int width, int height, RGB color)
    {
        float gl_x = xPixelToGLCoordinate(x);
        float gl_y = yPixelToGLCoordinate(y);
        float gl_width = xWidthToGLDelta(width);
        float gl_height = yWidthToGLDelta(height);

        GL11.glBegin(GL11.GL_QUADS);

        float red = (color.r > 255) ? 1.0f : (color.r * 255);
        float green = (color.g > 255) ? 1.0f : (color.g * 255);
        float blue = (color.b > 255) ? 1.0f : (color.b * 255);

        GL11.glColor3f(red, green, blue);

        GL11.glVertex2f(gl_x, gl_y);
        GL11.glVertex2f(gl_x + gl_width, gl_y);
        GL11.glVertex2f(gl_x + gl_width, gl_y + gl_height);
        GL11.glVertex2f(gl_x, gl_y + gl_height);

        GL11.glEnd();

        return;
    }

    // Draws a textured object at the desired location on the window.
    public void drawTexture(int x, int y, int width, int height, int texture_id, RGB color)
    {
        float gl_x = xPixelToGLCoordinate(x);
        float gl_y = yPixelToGLCoordinate(y);
        float gl_width = xWidthToGLDelta(width);
        float gl_height = yWidthToGLDelta(height);

        float red, green, blue;

        if (color == null)
        {
            red = 1.0f;
            green = 1.0f;
            blue = 1.0f;
        }
        else
        {
            red = (color.r > 255) ? 1.0f : (color.r * 255);
            green = (color.g > 255) ? 1.0f : (color.g * 255);
            blue = (color.b > 255) ? 1.0f : (color.b * 255);
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture_id);

        GL11.glBegin(GL11.GL_QUADS);

        // GL11.glColor3f(red, green, blue);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(gl_x, gl_y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(gl_x + gl_width, gl_y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(gl_x + gl_width, gl_y + gl_height);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(gl_x, gl_y + gl_height);

        GL11.glEnd();

        return;
    }
}
