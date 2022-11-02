package utils;

/**
 * Contains function to generate a three-letter ID for cinemas
 * @author Augustine Lee
 * @version 1.0
 * @since 2022-11-02
 */

public class IdUtils {
    private static int cinemaCounter;

    public IdUtils(int numMovies) {
        cinemaCounter = numMovies;
    }

    public String generateCinemaID() {
        /* Int to String conversion function */
        /* Divides integer ID by 25 (we will save Z as a filler alphabet) */
        /* The remainder will be the offset from A */
        /* Assumption made for string ID's length = 3: total cinemas <= 15624 (25^3 - 1) */
        int remainder = 0, intID = cinemaCounter;
        String stringID = "";

        do {
            remainder = intID%25;
            stringID = stringID + (char) (remainder + 65);
            intID = intID/25;
        }
        while (intID > 0);

        while (stringID.length() < 3) {
            stringID = "Z" + stringID; /* Prefix string ID with Z until its length is 3 */
        }

        cinemaCounter++; /* increment cinemaCounter by 1 to denote an increase in cinemas */

        return stringID;
    }
}
