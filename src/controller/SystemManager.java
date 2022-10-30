package controller;

import java.util.ArrayList;

import model.Holiday;

enum SpecialDay {
  WEEKEND,
  HOLIDAY
}

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class SystemManager {
  private ArrayList<Holiday> holidaysArr;
  private float basePrice;

  public SystemManager(float basePrice) {
    this.basePrice = basePrice;
  }

  public ArrayList<Holiday> getHoliday() {
    return holidaysArr;
  }


  public SpecialDay isSpecialDay(Date date) {
    
  }
  
  public void setBasePrice(float basePrice) {
    this.basePrice = basePrice;
  }

  public boolean deleteHoliday(Date date) {
    return false;
  }

  public boolean addHoliday(Date date) {
    return false;
  }

  public float getBasePrice() {
    return basePrice;
  }




}