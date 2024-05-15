package net.llamaking.falling_block_game;

import net.llamaking.falling_block_game.Logger.LoggerLevel;

import java.io.File;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

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
    // Logger from the main class and other variables.
    protected Logger logger;
    protected String assets_json_path;

    // Uploads an image to the GPU.
    public int loadImageToGPU(Image image)
    {
        int texture_id = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture_id);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.x, image.y, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image.pixel_data);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

        image.texture_id = texture_id;

        return texture_id;
    }

    // Loads an image from the disk to memory.
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

        result.texture_id = loadImageToGPU(result);
        
        return result;
    }

    // Reads assets.json and loads textures from there.
    public Map<String, Image> loadTextures()
    {
        Map<String, Image> textures = new HashMap<>();

        File assets_json_file = new File(this.assets_json_path);
        if (!assets_json_file.exists())
        { 
            logger.printf(LoggerLevel.ERROR, "assets.json (%s) does not exist", assets_json_path);
            return null;
        }
        if (assets_json_file.isDirectory())
        {
            logger.printf(LoggerLevel.ERROR, "assets.json (%s) is a directory", assets_json_path);
            return null;
        }

        JSONObject assets_json;

        try
        {
            String text = Files.readString(Paths.get(assets_json_path));
            assets_json = new JSONObject(text);
        }
        catch (Exception e)
        {
            logger.error("Error while parsing assets.json, showing stack trace");
            e.printStackTrace();
            return null;
        }

        Image temp_image;

        for (String key : assets_json.keySet())
        {
            Object value = assets_json.get(key);
            if (!(value instanceof String))
            {
                logger.warning("Found non-string in assets.json, continuing over");
                continue;
            }

            String path = (String)value;

            temp_image = loadImage(path);

            if (temp_image == null)
            {
                continue;
            }

            textures.put(key, temp_image);
        }

        return textures;
    }

    // Modifies the RGB values of an image.
    // Not tested just yet.
    public boolean modifyImageRGBA(Image image, RGBA rgba)
    {
        if (image == null)
        {
            return false;
        }
        if (image.channels < 4)
        {
            return false;
        }

        byte r, g, b, a;

        image.pixel_data.rewind();
        for (int i = 0; i < (image.x * image.y * image.channels); i++)
        {
            r = (byte) (image.pixel_data.get(i) & rgba.r);
            g = (byte) (image.pixel_data.get(i + 1) & rgba.g);
            b = (byte) (image.pixel_data.get(i + 2) & rgba.b);
            a = (byte) (image.pixel_data.get(i + 3) & rgba.a);

            image.pixel_data.put(i, r);
            image.pixel_data.put(i, g);
            image.pixel_data.put(i, b);
            image.pixel_data.put(i, a);
        }

        return true;
    }

    // Constructor.
    // Why is it all the way down here?
    public TextureLoader(Logger logger, String assets_json_path)
    {
        this.logger = logger;
        this.assets_json_path = assets_json_path;
    }
}
