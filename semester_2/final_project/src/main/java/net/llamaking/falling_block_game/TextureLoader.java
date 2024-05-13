package net.llamaking.falling_block_game;

import net.llamaking.falling_block_game.Logger.LoggerLevel;

import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

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


public class TextureLoader
{
    Logger logger;

    public Image loadImage(String filepath)
    {
        Image result = new Image();

        logger.printf(LoggerLevel.DEBUG, "Loading image %s", filepath);

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            result.pixel_data = STBImage.stbi_load(filepath, width, height, channels, 4);

            if (result.pixel_data == null)
            {
                this.logger.printf(LoggerLevel.ERROR, "Unable to load image at %s", filepath);
                return null;
            }

            result.x = width.get();
            result.y = height.get();
            result.channels = channels.get();
            result.path = filepath;
        }

        int texture_id = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture_id);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, result.x, result.y, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, result.pixel_data);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

        result.texture_id = texture_id;
        
        return result;
    }

    public TextureLoader(Logger logger)
    {
        this.logger = logger;
    }
}
