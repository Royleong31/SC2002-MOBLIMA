package utils;

import java.util.ArrayList;

import controller.SystemManager;
import model.Screening;
import utils.DateTimeUtils;

/**
 * Use this to calculate the price of each ticket at a particular date and time
 */
public class PriceUtils {
  // As the SystemsManager stores the list of holidays and DateTime can calculate the time and whether the date is a weekend, we can use these to calculate the price
  //  so each method requires SystemsManager, DateTime, and screening as parameters
  // Regarding the different types of Cinemas, we can use an enum instead of multiple classes so we can store different prices for each type of cinema. This gives more flexibility than having a price multiplier
  // we can also have different prices for different genres of movies, for example 3D, blockbuster
  public static float getPrice(SystemManager sm, Screening screening, String dateString) throws Exception {
    /* check if it is a PH */
    boolean isHoliday = sm.isHoliday(dateString);
    /* check movie genre */
    /* check cinema type */
    float cinemaBasePrice = sm.getCinemaBasePrice(screening.getCinema());
    /* check seat type */
    return 1;
  }
}
