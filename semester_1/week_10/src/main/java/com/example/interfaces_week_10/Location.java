package com.example.interfaces_week_10;

// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development I
// Instructor Mr. Gross


// Copyright (C) 2023  Daniel Smith

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


// A basic container to hold location data
// for planes and airports.
public class Location implements Comparable<Location>
{
    // Enumeration classes to seperate whether
    // a container is a plane or an airport and
    // to seperate whether or not a coordinate is
    // in the north/east or in the south/west hemisphere.
    enum LocationType
    {
        AIRPLANE,
        AIRPORT
    }
    enum Hemisphere
    {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    // Private attributes such as location
    // and ICAO codes.
    private double latitude;
    private double longitude;
    private double altitude;
    private String icao_code;
    private LocationType type;

    // The blank constructor.
    public Location()
    {
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.altitude = 0.0;
        this.icao_code = null;
        this.type = null;
    }

    // The default constructor.
    public Location(
        double latitude,
        double longitude,
        double altitude,
        String icao_code,
        LocationType type
    )
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.icao_code = icao_code;
        this.type = type;
    }

    // An alternate default constructor.
    public Location(
        double lat_degrees,
        double lat_minutes,
        double lat_seconds,
        double lon_degrees,
        double lon_minutes,
        double lon_seconds,
        Hemisphere lat_hemisphere,
        Hemisphere lon_hemisphere,
        double altitude,
        String icao_code,
        LocationType type
    )
    {
        this.latitude = dmsToDecimal(lat_degrees, lat_minutes, lat_seconds, lat_hemisphere);
        this.longitude = dmsToDecimal(lon_degrees, lon_minutes, lon_seconds, lon_hemisphere);
        this.altitude = altitude;
        this.icao_code = icao_code;
        this.type = type;
    }

    // The getters for this object.
    public double       getLatitude()     { return this.latitude; }
    public double       getLongitude()    { return this.longitude; }
    public double       getAltitude()     { return this.altitude; }
    public String       getICAOCode()     { return this.icao_code; }
    public LocationType getLocationType() { return this.type; }

    // The default setters for this object.
    // Please note -- we exclude setting the
    // ICAO code and location type, as either
    // should not change.
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }

    // The other setters for this object.
    // These setters set the location of the
    // object via degrees, minutes, and seconds.
    public void setLatitude(
        double degrees, double minutes, double seconds,
        Hemisphere hemisphere
    )
    {
        this.latitude = dmsToDecimal(degrees, minutes, seconds, hemisphere);
    }
    public void setLongitude(
        double degrees, double minutes, double seconds,
        Hemisphere hemisphere
    )
    {
        this.longitude = dmsToDecimal(degrees, minutes, seconds, hemisphere);
    }

    // Other useful functions.
    private double dmsToDecimal(double degrees, double minutes, double seconds, Hemisphere hemisphere)
    {
        // Check if the parameters are invalid.
        if (degrees < 0 || minutes < 0 || seconds < 0 || minutes >= 60 || seconds >= 60)
            throw new IllegalArgumentException("Incorrect measurement of parameters.");

        double output = degrees + (minutes / 60.0) + (seconds / 3600.0);

        // Reverse the longitude if the hemisphere is in the south or western
        // hemisphere.
        if (hemisphere.equals(Hemisphere.SOUTH) || hemisphere.equals(Hemisphere.WEST))
            output = -output;

        return output;
    }

    // The string interface method.
    @Override
    public String toString()
    {
        return "Location(" +
               "latitude: " + this.latitude + ", " +
               "longitude: " + this.longitude + ", " +
               "altitude: " + this.altitude + ", " +
               "icao_code: " + this.icao_code + ", " +
               "type: " + this.type + ")"
        ;
    }

    // The comparison interface method.
    public int compareTo(Location location)
    {
        int result = Double.compare(this.latitude, location.latitude);
        if (result == 0)
        {
            result = Double.compare(this.longitude, location.longitude);
        }
        if (result == 0)
        {
            result = Double.compare(this.altitude, location.altitude);
        }
        if (result == 0)
        {
            result = this.icao_code.compareTo(location.icao_code);
        }
        if (result == 0)
        {
            result = this.type.compareTo(location.type);
        }
        return result;
    }
}