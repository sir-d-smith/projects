package net.llamaking.falling_block_game;

import java.io.PrintStream;
import java.time.*;
import java.time.format.DateTimeFormatter;

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


// Please don't let me overcomplicate this.
// It just needs to be simple. Dont. overcomplicate. it.
// you've got this daniel
public class Logger
{
    // Creating the levels for the logger.
    enum LoggerLevel
    {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        CRITICAL
    }

    // Important base variables for the class.
    private boolean is_active;
    private LoggerLevel min_level;
    private PrintStream stream;
    private boolean use_pretty_colors;

    // Blank constructor. Uses default variables.
    public Logger()
    {
        this.is_active = true;
        this.min_level = LoggerLevel.DEBUG;
        this.stream = System.err;
        this.use_pretty_colors = false;
    }

    // Other constructor to let user decide what data to use.
    public Logger(boolean is_active, LoggerLevel min_level, PrintStream stream)
    {
        this.is_active = is_active;
        this.min_level = min_level;
        this.stream = stream;
        this.use_pretty_colors = false;
    }

    // Changes whether or not the logger uses very pretty colors.
    public void usePrettyColors(boolean use_pretty_colors)
    {
        this.use_pretty_colors = use_pretty_colors;
    }


    private String logLevelToColorCode(LoggerLevel level)
    {
        switch (level)
        {
            case LoggerLevel.DEBUG:
                return "\033[1;34m";
                
            case LoggerLevel.INFO:
                return "\033[1;37m";

            case LoggerLevel.WARNING:
                return "\033[1;35m";

            case LoggerLevel.ERROR:
                return "\033[1;31m";

            case LoggerLevel.CRITICAL:
                return "\033[0;41;37m";
            
            default:
                return "";
        }
    }

    // Prints the beginning of the logging message.
    // Warning: Don't use in nested methods! Only use directly once.
    protected boolean printStringFormat(LoggerLevel level, int threadStackTraceElement)
    {
        // check if the level we're using is below minimum spec or
        // the loger is deacivated
        if (level.compareTo(this.min_level) < 0 || this.is_active == false)
        {
            return false;
        }

        // this turned out to be extrememly complicated and overengineered.
        // oopsies!

        // getting the date & time into a single string
        String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        // getting the method that called the logger
        StackTraceElement method_called = Thread.currentThread().getStackTrace()[threadStackTraceElement];

        // tracing that method's information
        String method_name = method_called.getMethodName();
        String method_class_name = method_called.getClassName();
        // String method_file_name = method_called.getFileName();
        int method_line_number = method_called.getLineNumber();

        // print to the console (or wherever the user selected)
        if (!this.use_pretty_colors)
        {
            this.stream.printf("%s [%s] %s@%s:%d : ", 
                               date_time,
                               level.name(),
                               method_class_name,
                               method_name,
                               method_line_number);
        }
        else
        {
            // made this for fun.
            // not much practical use other than it looks cool.
            this.stream.printf("\033[0;32m%s\033[0m ", date_time);
            this.stream.printf("[%s%s\033[0m] ", logLevelToColorCode(level), level.name());
            this.stream.printf("\033[0;36m%s\033[0m@\033[36m%s\033[0m:\033[36m%d\033[0m : ",
                               method_class_name, method_name, method_line_number);
        }

        return true;
    }
    
    // Other standard printing functions.
    public void printf(LoggerLevel level, String str, Object... formats)
    {
        if (!this.printStringFormat(level, 3))
            return;

        this.stream.printf(str, formats);
        this.stream.println();
    }

    public void print(LoggerLevel level, Object str)
    {
        if (!this.printStringFormat(level, 3))
            return;

        this.stream.print(str);
    }

    public void println(LoggerLevel level, Object str)
    {
        if (!this.printStringFormat(level, 3))
            return;

        this.stream.println(str);
    }

    // Wrapper methods to shorten logger calls.
    public void debug(Object str)
    {
        if (!this.printStringFormat(LoggerLevel.DEBUG, 3))
            return;

        this.stream.println(str);
    }

    public void info(Object str)
    {
        if (!this.printStringFormat(LoggerLevel.INFO, 3))
            return;

        this.stream.println(str);
    }

    public void warning(Object str)
    {
        if (!this.printStringFormat(LoggerLevel.WARNING, 3))
            return;

        this.stream.println(str);
    }

    public void error(Object str)
    {
        if (!this.printStringFormat(LoggerLevel.ERROR, 3))
            return;

        this.stream.println(str);
    }

    public void critical(Object str)
    {
        if (!this.printStringFormat(LoggerLevel.CRITICAL, 3))
            return;

        this.stream.println(str);
    }
}
