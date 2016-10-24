package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Just a class to hold location info we might want to display on the map
 */
public class Location implements Serializable {

    private static Logger LOGGER = Logger.getLogger("Location");
    private static FileHandler logFileHandler;
    static {
        LOGGER.setLevel(Level.FINER);
        try {
            logFileHandler = new FileHandler("LogFile");
            logFileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(logFileHandler);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to create log file", ex);
        }
    }

    private double longitude;
    private double latitude;
    private final String description;
    private final String title;

    /**
     * Create new location
     * @param lg the longitude
     * @param lat the longitude
     */
    public Location(double lg, double lat, String ti, String desc) {
        LOGGER.entering("Location", "Constructor");
        longitude = lg;
        latitude = lat;
        title = ti;
        description = desc;
        LOGGER.exiting("Location", "Constructor");
    }

    /**
     * Getter of longitude.
     * @return longitude
     */
    public double getLongitude() { return longitude; }

    /**
     * Setter of longitude.
     */
    public void setLongitude(double lg) {
         longitude= lg;
    }

    /**
     * Getter of latitude.
     * @return latitude of location
     */
    public double getLatitude() {return latitude; }

    /**
     * Setter of longitude.
     */
    public void setLatitude(double lat) {
        latitude = lat;
    }

    /**
     * Getter of description
     * @return description of location
     */
    public String getDescription() {return description;}
    public String getTitle() { return title; }

    /**
     * Save logger to text.
     * @param pw
     */
    public void saveToText(PrintWriter pw) {
        LOGGER.setLevel(Level.FINEST);
        LOGGER.entering("Location", "saveToText");
        pw.println(longitude + "\t" + latitude + "\t" +  description + "\t" + title);
        LOGGER.exiting("Location", "saveToText");
    }

    /**
     * Return location from text file.
     * @param str
     * @return the location.
     * @throws FileFormatException
     */
    public static Location makeFromFileString(String str) throws FileFormatException {
        String[] tokens = str.split("\t");


        if (tokens.length < 3) {
            throw(new FileFormatException(str));
        }

        double longit;
        double lat;
        try {
            longit = Double.parseDouble(tokens[0]);
            lat = Double.parseDouble(tokens[1]);
        } catch (NumberFormatException e) {
            throw(new FileFormatException(str));
        }

       return new Location(longit, lat, tokens[3], tokens[2]);

    }
}
