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
    protected Logger logger;
    protected String assets_json_path;

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

    public TextureLoader(Logger logger, String assets_json_path)
    {
        this.logger = logger;
        this.assets_json_path = assets_json_path;
    }
}
