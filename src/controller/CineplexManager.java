package controller;

import java.util.ArrayList;

import enums.CinemaType;
import model.Cineplex;
import model.Cinema.Cinema;

/**
 * Cineplex Manager
 * Responsible for handling all addition/deletion/information retrieval of cineplexes and its constituent cinemas
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class CineplexManager {
  private ArrayList<Cineplex> cineplexesArr = new ArrayList<Cineplex>();

  public CineplexManager() {}

  public void addCineplex(String location) throws Exception {
    for (int i = 0; i < cineplexesArr.size(); i++) {
      if (cineplexesArr.get(i).getLocation() == location) {
        // throw exception
        throw new Exception("Cineplex already exists.");
      }
    }
    cineplexesArr.add(new Cineplex(location));
  }

  public void addCinema(Cineplex cineplex, Cinema cinema, CinemaType cinemaType) throws Exception {
    cineplex.addCinema(cinema);
  }

  public void deleteCinema(Cineplex cineplex, Cinema cinema) throws Exception {
    cineplex.deleteCinema(cinema);
  }

  public ArrayList<Cineplex> getCineplexes() {
    return cineplexesArr;
  }

  public ArrayList<Cinema> getCinemas(Cineplex cineplex) throws Exception {
    /* Check if cineplex exists */
    for (int i = 0; i < cineplexesArr.size(); i++) {
      if (cineplex.equals(cineplexesArr.get(i))) {
        /* Cineplex found in cineplex array */
        return cineplex.getCinemas();
      }
    }
    throw new Exception("Cineplex does not exists");
  }
}