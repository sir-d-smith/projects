package net.llamaking.falling_block_game;

import net.llamaking.falling_block_game.Logger.LoggerLevel;

import java.io.PrintStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
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


public class App
{
    // make sure to create a grid of 10x22 size

    private static PrintStream printstream = System.err;
    private static Logger logger = new Logger(true, LoggerLevel.DEBUG, printstream);

    private static int window_width = 800;
    private static int window_height = 600;
    private static String window_title = "Falling Block Game";

    private static Image block_texture;

    private static ArrayList<Image> textures;
    private static Hashtable<String, Image> key_textures;

    private static final int grid_x_offset = 100;
    private static final int grid_y_offset = 25;

    private static int genRandomNumber(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private static RGB genRandomRGB()
    {
        int r = genRandomNumber(0, 255);
        int g = genRandomNumber(0, 255);
        int b = genRandomNumber(0, 255);

        return new RGB(r, g, b);
    }

    private static float xPixelToGLCoordinate(int x)
    {
        return (2.0f * x) / window_width - 1.0f;
    }

    private static float yPixelToGLCoordinate(int y)
    {
        return 1.0f - (2.0f * y) / window_height;
    }

    private static float xWidthToGLDelta(int x)
    {
        return (2.0f * (x + (window_width/2))) / window_width - 1.0f;
    }

    private static float yWidthToGLDelta(int y)
    {
        return 1.0f - (2.0f * (y + (window_height/2))) / window_height;
    }

    // this is not how OpenGL was intended to be used.
    // oh well, it is now!
    private static void drawSquare(int x, int y, int width, int height, RGB color)
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

    private static void drawTexture(int x, int y, int width, int height, int texture_id, RGB color)
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

        GL11.glColor3f(red, green, blue);

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

    private static boolean loadTextures(TextureLoader textureloader)
    {
        block_texture = textureloader.loadImage("./assets/block.png");
        block_texture.name = "block";
        textures.add(block_texture);

        File letters_dir = new File("./assets/letters/");
        for (File i : letters_dir.listFiles())
        {
            if (!i.isFile() || !i.getName().endsWith("png"))
            {
                continue;
            }

            Image j = textureloader.loadImage(i.getPath());
            j.name = i.getName().replace(".png", "");
            textures.add(j);

            key_textures.put(j.name, j);
        }

        // being reduntant, checking if any loaded images are null.
        // hopefully an error was sent to the user if an image was not loaded.
        for (Image e : textures)
        {
            if (e == null)
            {
                return false;
            }
        }

        return true;
    }

    private static void freeAndGo()
    {
        // Free textures used. Important!!
        logger.debug("Freeing textures");
        for (Image i : textures)
        {
            if (i.pixel_data == null)
            {
                continue;
            }
            STBImage.stbi_image_free(i.pixel_data);
        }

        // Terminate GLFW.
        logger.debug("Terminating GLFW");
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args)
    {
        // Initialize logger & textures.
        logger.usePrettyColors(true);
        textures = new ArrayList<Image>();
        key_textures = new Hashtable<String, Image>();

        // Setup error callback for GLFW.
        GLFWErrorCallback.createPrint(printstream).set();

        // Initialize GLFW.
        logger.debug("Initializing GLFW");
        boolean glfw_return_status = GLFW.glfwInit();
        if (!glfw_return_status)
        {
            logger.critical("Unable to initialize GLFW");
            System.exit(1);
        }

        // Set window configurations.
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        // Create a window.
        logger.printf(LoggerLevel.DEBUG, "Creating a window (%dw, %dh)", window_width, window_height);
        long window = GLFW.glfwCreateWindow(window_width, window_height, window_title, 0, 0);
        if (window == 0)
        {
            GLFW.glfwTerminate();
            logger.critical("Unable to create window");
            System.exit(1);
        }

        // Make the window visible.
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);

        // Tell OpenGL configuration stuff.
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // Load textures.
        logger.debug("Loading textures");
        TextureLoader textureloader = new TextureLoader(logger);
        if (!loadTextures(textureloader))
        {
            logger.critical("Unable to load all textures, bailing");
            freeAndGo();
            System.exit(1);
        }
        logger.info("Successfully loaded textures!");

        // Game loop.
        while (!GLFW.glfwWindowShouldClose(window))
        {
            GLFW.glfwPollEvents();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // drawSquare(0, 0, 100, 100, new RGB(1.0f, 0.0f, 0.0f));
            // drawTexture(0, 0, 100, 100, block_texture.texture_id);
            // drawTexture(0, 0, window_width, window_height, block_texture.texture_id);

            // drawTexture(0, 0, 50, 50, key_textures.get("h").texture_id);
            // drawTexture(50, 0, 50, 50, key_textures.get("e").texture_id);
            // drawTexture(100, 0, 50, 50, key_textures.get("l").texture_id);
            // drawTexture(150, 0, 50, 50, key_textures.get("l").texture_id);
            // drawTexture(200, 0, 50, 50, key_textures.get("o").texture_id);
            // drawTexture(300, 0, 50, 50, key_textures.get("w").texture_id);
            // drawTexture(350, 0, 50, 50, key_textures.get("o").texture_id);
            // drawTexture(400, 0, 50, 50, key_textures.get("r").texture_id);
            // drawTexture(450, 0, 50, 50, key_textures.get("l").texture_id);
            // drawTexture(500, 0, 50, 50, key_textures.get("d").texture_id);

            // drawTexture(0, 0, (Block.BLOCK_SIZE * 10), (Block.BLOCK_SIZE * 22), block_texture.texture_id);

            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 22; j++)
                {
                    drawTexture((i * Block.BLOCK_SIZE) + grid_x_offset, (j * Block.BLOCK_SIZE) + grid_y_offset,
                                Block.BLOCK_SIZE, Block.BLOCK_SIZE, block_texture.texture_id, genRandomRGB());
                }
            }

            GL11.glFlush();

            GLFW.glfwSwapBuffers(window);
        }

        freeAndGo();
    }
}
