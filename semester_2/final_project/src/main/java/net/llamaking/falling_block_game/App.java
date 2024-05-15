package net.llamaking.falling_block_game;

import net.llamaking.falling_block_game.Logger.LoggerLevel;

import java.io.PrintStream;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
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


public class App
{
    // Private, global variables for this main class.
    private static PrintStream printstream = System.err;
    private static Logger logger = new Logger(true, LoggerLevel.DEBUG, printstream);

    private static int window_width = 800;
    private static int window_height = 600;
    private static String window_title = "Falling Block Game";
    private static long window;

    private static Game game;

    // Frees & closes different variables before exiting the program.
    private static void freeAndGo()
    {
        // Free textures used. Important!!
        game.freeTextures();

        // Terminate GLFW.
        logger.debug("Terminating GLFW");
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args)
    {
        // Initialize logger & the grid.
        logger.usePrettyColors(true);
        game = new Game(logger, window_width, window_height);

        logger.info("Falling block game");

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
        window = GLFW.glfwCreateWindow(window_width, window_height, window_title, 0, 0);
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
        if (!game.loadTextures())
        {
            logger.critical("Unable to load all textures, bailing");
            freeAndGo();
            System.exit(1);
        }
        logger.info("Successfully loaded textures!");

        // Initialize the game class.
        game.init();

        // Game loop.
        while (!GLFW.glfwWindowShouldClose(window))
        {
            GLFW.glfwPollEvents();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            game.drawPlayField();
            game.drawBlocks();

            GL11.glFlush();

            GLFW.glfwSwapBuffers(window);
        }

        // Call the free & close function before exiting.
        freeAndGo();
    }
}
