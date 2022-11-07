package utils;

import enums.CinemaType;

public class CinemaPremiumUtils {
    public static Number getPremium(CinemaType ct) {
        if (ct.equals(CinemaType.GOLD_CLASS)) {
            return 2.0;
        }
        else if (ct.equals(CinemaType.DELUXE)) {
            return 1.75;
        }
        else if (ct.equals(CinemaType.PREMIUM)) {
            return 1.5;
        }
        else {
            return 1.0;
        }
    }
}
