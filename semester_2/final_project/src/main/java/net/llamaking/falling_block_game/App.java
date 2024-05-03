package net.llamaking.falling_block_game;

import net.llamaking.falling_block_game.Logger.LoggerLevel;

import java.io.PrintStream;
import java.lang.Math;

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
    private static PrintStream printstream = System.err;
    private static Logger logger = new Logger(true, LoggerLevel.DEBUG, printstream);

    private static int window_width = 800;
    private static int window_height = 600;
    private static String window_title = "Falling Block Game";

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

    // private static void drawCube(float x, float y, float z, float r, float g, float b)
    // {
    //     GL11.glBegin(GL11.GL_QUADS);

    //     GL11.glColor3f(r, g, b);
    //     GL11.glVertex3f(x, y, z);
    //     GL11.glVertex3f(x + 1.0f, y, z);
    //     GL11.glVertex3f(x + 1.0f, y - 1.0f, z);
    //     GL11.glVertex3f(x, y - 1.0f, z);

    //     GL11.glEnd();
    // }

    private static void drawCube(int x, int y, int width, int height, RGB color)
    {
        float gl_x = xPixelToGLCoordinate(x);
        float gl_y = yPixelToGLCoordinate(y);
        float gl_width = xWidthToGLDelta(width);
        float gl_height = yWidthToGLDelta(height);

        // float gl_x_delta = gl_x - gl_width;
        // float gl_y_delta = gl_y - gl_height;

        // float gl_x_delta_result = gl_x - gl_x_delta;
        // float gl_y_delta_result = gl_y - gl_y_delta;

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor3f(color.r, color.g, color.b);
        // GL11.glColor3f(1.0f, 0.0f, 0.0f);

        GL11.glVertex2f(gl_x, gl_y);
        GL11.glVertex2f(gl_x + gl_width, gl_y);
        GL11.glVertex2f(gl_x + gl_width, gl_y + gl_height);
        GL11.glVertex2f(gl_x, gl_y + gl_height);

        // GL11.glVertex2f(gl_x, gl_y);
        // GL11.glVertex2f(gl_x - gl_x_delta, gl_y);
        // GL11.glVertex2f(gl_x - gl_x_delta, gl_y - gl_y_delta);
        // GL11.glVertex2f(gl_x, gl_y - gl_y_delta);

        // GL11.glVertex2f(gl_x, gl_y);
        // GL11.glVertex2f(gl_x + (gl_x - gl_width), gl_y);
        // GL11.glVertex2f(gl_x + (gl_x - gl_width), gl_y + (gl_y - gl_height));
        // GL11.glVertex2f(gl_x, gl_y + (gl_y - gl_height));

        GL11.glEnd();

        return;
    }

    public static void main(String[] args)
    {
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

        // int width = 0;
        // long increment_dt = 50;
        // long increment_time = 0;

        // Game loop.
        while (!GLFW.glfwWindowShouldClose(window))
        {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // drawCube(-1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f);
            // drawCube(0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            // drawCube(-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
            // drawCube(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);

            // if ((System.currentTimeMillis() - increment_time) >= increment_dt)
            // {
            //     width++;
            //     increment_time = System.currentTimeMillis();
            // }

            drawCube(0, 0, 100, 100, new RGB(1.0f, 0.0f, 0.0f));
            // drawCube(0, window_height/2, width, window_height/2, new RGB(0.0f, 1.0f, 0.0f));

            GL11.glFlush();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        // Terminate GLFW.
        logger.debug("Terminating GLFW");
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }
}
