package utils;

/**
 * Use this to calculate the price of each ticket at a particular date and time
 */
public class PriceUtils {
  // As the SystemsManager stores the list of holidays and DateTime can calculate the time and whether the date is a weekend, we can use these to calculate the price
  //  so each method requires SystemsManager, DateTime, and screening as parameters
  // Regarding the different types of Cinemas, we can use an enum instead of multiple classes so we can store different prices for each type of cinema. This gives more flexibility than having a price multiplier
  // we can also have different prices for different genres of movies, for example 3D, blockbuster
}
