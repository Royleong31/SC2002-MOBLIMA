package controller;
import java.util.ArrayList;

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

  public boolean addScreening(Screening screening) {
  }

  // TODO: Update screening?

  public boolean removeScreening(Screening screening) {
  }

  // TODO: Overload this so that it works with diff optional params
  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, String date) {
    
  }

  public float getPrice(SystemManager systemManager, Screening screening) {

  }
}