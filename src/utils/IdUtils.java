package utils;

/**
 * Contains static function to generate a three-letter ID for cinemas
 * @author Augustine Lee
 * @version 1.0
 * @since 2022-11-02
 */

public class IdUtils {
    /**
     * integer to String conversion function
     * Divides integer ID by 25 (we will save Z as a filler alphabet)
     * The remainder will be the offset from A
     * Assumption made for string ID's length = 3: total cinemas less than or equal to 15624 (25^3 - 1)
     * @param intID the integer ID of the cinema
     * @return the three-letter string ID of the cinema
     */
    public static String generateCinemaID(int intID) {
        int remainder = 0;
        String stringID = "";

        do {
            remainder = intID%25;
            stringID = stringID + (char) (remainder + 65); /* added 65 to bring remainder to ASCII value of corresponding character */
            intID = intID/25;
        }
        while (intID > 0);

        while (stringID.length() < 3) {
            stringID = "Z" + stringID; /* Prefix string ID with Z until its length is 3 */
        }

        return stringID;
    }
}
