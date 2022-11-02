package utils;

import enums.CinemaType;

public class CinemaPremium {
    public Number getPremium(CinemaType ct) {
        if (ct == CinemaType.GOLD_CLASS) {
            return 2.0;
        }
        else if (ct == CinemaType.DELUXE) {
            return 1.75;
        }
        else if (ct == CinemaType.PREMIUM) {
            return 1.5;
        }
        else {
            return 1.0;
        }
    }
}
