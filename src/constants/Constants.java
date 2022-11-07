package constants;

import java.util.HashMap;

import enums.CinemaType;
import enums.SeatType;
import enums.SortCriteria;

public final class Constants {
    private Constants() {
      throw new Error("Cannot instantiate Constants class");
    }

    // TODO: Place all constants here
    public static final float DEFAULT_PRICE = 10.0f;

    // Default cinema price multipliers
    public static final HashMap<CinemaType, Float> DEFAULT_CINEMA_PRICE_MAP = new HashMap<CinemaType, Float>() {{
      put(CinemaType.NORMAL, 1f);
      put(CinemaType.GOLD_CLASS, 1.5f);
      put(CinemaType.DELUXE, 2.0f);
      put(CinemaType.PREMIUM, 2.5f);
    }};

    // Default seat price multipliers
    public static final HashMap<SeatType, Float> DEFAULT_SEAT_PRICE_MAP = new HashMap<SeatType, Float>() {{
      put(SeatType.NORMAL, 1f);
      put(SeatType.GOLD, 1.5f);
      put(SeatType.JUBILEE, 2.0f);
      put(SeatType.PLATINUM, 2.5f);
    }};

    // Default sorting criteria that the movie goers use
    public static final SortCriteria DEFAULT_SORT_CRITERIA = SortCriteria.SALES;
}