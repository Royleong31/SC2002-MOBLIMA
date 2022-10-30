package controller;

import java.util.ArrayList;

import model.Cineplex;
import model.Cinema.Cinema;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class CineplexManager {
  private ArrayList<Cineplex> cineplexesArr = new ArrayList<Cineplex>();

  public CineplexManager() {}
  
  public boolean addCineplex(String location) {
    return false;
  }

  public boolean addCinema(Cineplex cineplex, Cinema cinema, CinemaType cinemaType) {
    return false;
  }

  public boolean updateCinema(Cineplex cineplex, Cinema cinema, CinemaType cinemaType) {

  }

  public boolean deleteCinema(Cinema cinema) {

  }

  public ArrayList<Cineplex> getCineplexes() {
    return cineplexesArr;
  }

  public ArrayList<Cinema> getCinemas(Cineplex cineplex) {

  }
}