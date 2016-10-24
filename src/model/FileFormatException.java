package model;

/**
 * Created by robertwaters on 10/11/16.
 */
public class FileFormatException extends Exception {
    private String line;

    /**
     * Throw an exception.
     * @param str error message.
     */
    public FileFormatException(String str) {
        line = str;
    }

    /**
     * Getter of error message.
     * @return error message.
     */
    public String getOriginalLine() { return line; }
}
