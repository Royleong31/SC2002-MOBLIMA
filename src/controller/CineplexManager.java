package controller;

import java.util.ArrayList;

import enums.CinemaType;
import model.Cinema;
import model.Cineplex;
import model.SeatingPlan;

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
  private ArrayList<Cinema> cinemasArr = new ArrayList<Cinema>();

  public void addCineplex(String location) throws Exception {
    for (Cineplex cur: this.cineplexesArr) {
      if (cur.getLocation().equals(location)) {
        // throw exception
        throw new Exception("Cineplex already exists.");
      }
    }

    this.cineplexesArr.add(new Cineplex(location));
  }

  public void addCinema(Cineplex cineplex, int rows, int columns, int aisle, CinemaType cinemaType) throws Exception {
    SeatingPlan seatingPlan = new SeatingPlan(rows, columns, aisle);
    Cinema cinema = new Cinema(seatingPlan, cineplex, cinemaType);
    this.cinemasArr.add(cinema);
    cineplex.addCinema(cinema);
  }

  public Cinema getCinemaById(String cinemaId) throws Exception {
    for (Cinema cur: this.cinemasArr) {
      if (cur.getId().equals(cinemaId)) {
        return cur;
      }
    }
    
    throw new Exception("A cinema with the specified ID does not exist.");
  }

  public Cineplex getCineplexByLocation(String cineplexLocation) throws Exception {
    for (Cineplex cur: this.cineplexesArr) {
      if (cur.getLocation().equals(cineplexLocation)) {
        return cur;
      }
    }
    
    throw new Exception("A cineplex with the specified location does not exist.");
  }

  // add delete method for cineplex?
  public void deleteCinema(Cineplex cineplex, Cinema cinema) throws Exception {
    cineplex.deleteCinema(cinema);
  }

  public ArrayList<Cineplex> getCineplexes() {
    return this.cineplexesArr;
  }

  public ArrayList<Cinema> getCinemas(Cineplex cineplex) throws Exception {
    /* Check if cineplex exists */
    for (Cineplex cur: this.cineplexesArr) {
      if (cineplex.equals(cur)) {
        /* Cineplex found in cineplex array */
        return cineplex.getCinemas();
      }
    }
    throw new Exception("Cineplex does not exists");
  }
}