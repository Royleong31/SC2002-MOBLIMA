package constants;

import java.util.ArrayList;
import java.util.HashMap;

import enums.CinemaType;
import enums.SeatType;
import enums.SortCriteria;
import utils.Utils;

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

    // All the possible staff ids that can be used to create an account
    // Each id can only be used once
    public static final ArrayList<String> APPROVED_STAFF_IDS = Utils.asArrayList("abcde","1234", "zxcv");

    // This is to ensure that only staff members who have the correct code can create an account
    // You need to have both the correct code and an approved staff id to create a staff account
    public static final String STAFF_ID_CODE = "super_secret_code";
}