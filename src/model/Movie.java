package model;
import enums.*;
import java.util.ArrayList;

/**
 * Movie class
 * Contains details of movie and get/update functions
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class Movie {
  private String title;
  private ShowStatus showingStatus;
  private String synopsis;
  private String director;
  private ArrayList<String> cast;
  private ArrayList<Review> reviews = new ArrayList<Review>();
  private Advisory advisoryRating;
  private Genre genre;
  private MovieType type;

  public Movie(String title, ShowStatus showingStatus, String synopsis, String director, ArrayList<String> cast, Advisory advisoryRating, Genre genre, MovieType type) {
    this.title = title;
    this.showingStatus = showingStatus;
    this.synopsis = synopsis;
    this.director = director;
    this.cast = cast;
    this.advisoryRating = advisoryRating;
    this.genre = genre;
    this.type = type;
  }

  public void addReview(Review review) {
    reviews.add(review);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public void setDirector(String director) {
    this.director = director;
  }
  
  public void setCast(ArrayList<String> cast) {
    this.cast = cast;
  }

  public void setMovieType(MovieType type) {
    this.type = type;
  }

  /**
   * Allows the admins to change the status of the movie
   * @param showStatus
   */
  public void setShowingStatus(ShowStatus showStatus) throws Exception {
    if (showStatus != ShowStatus.NULL) {
      this.showingStatus = showStatus;
    }
    else {
      throw new Exception("Showing status cannot be NULL");
    }
  }

  public void setAdvisoryRating(Advisory advisoryRating) throws Exception {
    if (advisoryRating != Advisory.NULL) {
      this.advisoryRating = advisoryRating;
    }
    else {
      throw new Exception("Advisory rating cannot be NULL");
    }
  }

  public void setGenre(Genre genre) throws Exception {
    if (genre != Genre.NULL) {
      this.genre = genre;
    }
    else {
      throw new Exception("Genre cannot be NULL");
    }
  }

  public String getTitle() {
    return this.title;
  }

  public ShowStatus getShowingStatus() {
    return this.showingStatus;
  }

  public String getSynopsis() {
    return this.synopsis;
  }

  public String getDirector() {
    return this.director;
  }
  
  public ArrayList<String> getCast() {
    return this.cast;
  }

  public Advisory getAdvisoryRating() {
    return this.advisoryRating;
  }

  public Genre getGenre() {
    return this.genre;
  }

  public MovieType getMovieType() {
    return this.type;
  }

  public ArrayList<Review> getReviews() {
    return this.reviews;
  }

  /*
   * Gets the overall rating from all the reviews of this movie
   */
  public float getOverallRating() {
    // loop through the list of reviews and get the average rating
    float total = 0;
    for (Review review : reviews) {
      total += review.getRating();
    }
    total /= reviews.size();
    return total;
  }
}
