package net.llamaking.falling_block_game;

import java.util.Map;

import org.lwjgl.stb.STBImage;

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


public class Game 
{
    // Logger from the main class.
    protected Logger logger;

    // Private variables for safekeeping.
    private Map<String, Image> textures;
    private Grid<Block> grid;
    private Draw drawer;

    private int window_width;
    private int window_height;

    private final int grid_x_offset = 100;
    private final int grid_y_offset = 25;
    private final int BLOCK_SIZE = 25;

    // Constructor class.
    public Game(Logger logger, int window_width, int window_height)
    {
        this.logger = logger;
        this.window_width = window_width;
        this.window_height = window_height;
    }

    // Getters and setters for different constants in this class.
    public int getXOffset()
    {
        return this.grid_x_offset;
    }

    public int getYOffset()
    {
        return this.grid_y_offset;
    }

    public int getBlockSize()
    {
        return this.BLOCK_SIZE;
    }

    // Random number functions.
    private int genRandomNumber(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private long generateKey()
    {
        return (long)(Math.random() * Integer.MAX_VALUE + 1);
    }

    // Initialize function.
    public boolean init()
    {
        grid = new Grid<Block>(10, 22);
        drawer = new Draw(window_width, window_height);

        // Make every square in the grid null to make sure nothing is present.
        for (int i = 0; i < grid.getWidth(); i++)
        {
            for (int j = 0; j < grid.getHeight(); j++)
            {
                grid.set(null, i, j);
            }
        }

        return true;
    }

    // Loads textures into memory and to the GPU.
    public boolean loadTextures()
    {
        TextureLoader textureloader = new TextureLoader(logger, "./assets/assets.json");
        textures = textureloader.loadTextures();

        if (textures == null || textures.isEmpty())
        {
            return false;
        }

        return true;
    }

    // Free textures set in heap memory.
    public boolean freeTextures()
    {
        logger.debug("Freeing textures");

        if (textures == null || textures.isEmpty())
        {
            logger.debug("Nothing to free!");
            return false;
        }

        for (Map.Entry<String, Image> i : textures.entrySet())
        {
            if (i.getValue().pixel_data == null)
            {
                continue;
            }
            STBImage.stbi_image_free(i.getValue().pixel_data);
        }

        return true;
    }

    // Draw the play field and the grid.
    public void drawPlayField()
    {
        // draw borders
        // left border
        for (int i = 0; i < grid.getHeight(); i++)
        {
            drawer.drawTexture(grid_x_offset - BLOCK_SIZE, (i * BLOCK_SIZE) + grid_y_offset,
                               BLOCK_SIZE, BLOCK_SIZE, textures.get("block").texture_id, new RGB(255, 255, 255));
        }

        // right border
        for (int i = 0; i < grid.getHeight(); i++)
        {
            drawer.drawTexture(grid_x_offset + (grid.getWidth() * BLOCK_SIZE), (i * BLOCK_SIZE) + grid_y_offset,
                               BLOCK_SIZE, BLOCK_SIZE, textures.get("block").texture_id, new RGB(255, 255, 255));
        }

        // draw grid
        for (int i = 0; i < grid.getWidth(); i++)
        {
            for (int j = 0; j < grid.getHeight(); j++)
            {
                drawer.drawTexture((i * BLOCK_SIZE) + grid_x_offset, (j * BLOCK_SIZE) + grid_y_offset,
                                   BLOCK_SIZE, BLOCK_SIZE, textures.get("block").texture_id, new RGB(255, 255, 255));
            }
        }
    }

    // Draw the blocks inside of the grid.
    public void drawBlocks()
    {
        Block temp_block;

        for (int i = 0; i < grid.getWidth(); i++)
        {
            for (int j = 0; j < grid.getHeight(); j++)
            {
                temp_block = grid.get(i, j);
                if (temp_block == null) continue;

                drawer.drawTexture((i * BLOCK_SIZE) + grid_x_offset, (j * BLOCK_SIZE) + grid_y_offset,
                            BLOCK_SIZE, BLOCK_SIZE, textures.get("block").texture_id, Block.blockColorToRGB(temp_block.getColor()));
            }
        }
    }
}
