package controller;
import java.util.ArrayList;

import model.DateTime;
import model.Screening;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class ScreeningManager {
  private ArrayList<Screening> screeningsArr;

  public void addScreening(Screening screening) {
  }

  /**
   * Update the showtime of a screening
   * Need to call the setDateTime method on screening object
   * @param screening
   * @param newDateTime
   */
  public void updateShowtime(Screening screening, DateTime newDateTime) {
  }

  /*
   * Needs to cascade and delete all tickets related to the screening
   * Do we need this? It's not listed in the brief
   */
  public void deleteScreening(Screening screening) {
  }

  // TODO: Overload this so that it works with diff optional params
  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, String date) {
  }

  /**
   * Use the cinema type and the system manager stuff to get the price
   * Use PriceUtils to calculate the price from the cinema type and date time
   * @param systemManager
   * @param screening
   * @return
   */
  public float getPrice(SystemManager systemManager, Screening screening) {

  }
}