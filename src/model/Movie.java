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
  private boolean isBlockbuster;
  private boolean is3D;
  private MovieType type;

  public Movie(String title, ShowStatus showingStatus, String synopsis, String director, ArrayList<String> cast, Advisory advisoryRating, Genre genre, boolean isBlockbuster, boolean is3D) {
    this.title = title;
    this.showingStatus = showingStatus;
    this.synopsis = synopsis;
    this.director = director;
    this.cast = cast;
    this.advisoryRating = advisoryRating;
    this.genre = genre;
    this.isBlockbuster = isBlockbuster;
    this.is3D = is3D;
  }
  
  public void addReview(Review review) {
    reviews.add(review);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Allows the admins to change the status of the movie
   * @param showStatus
   */
  public void setShowingStatus(ShowStatus showStatus) {
    this.showingStatus = showStatus;
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

  public void setAdvisoryRating(Advisory advisoryRating) {
    this.advisoryRating = advisoryRating;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public void setBlockbuster(boolean isBlockbuster) {
    this.isBlockbuster = isBlockbuster;
  }
  
   public void set3D(boolean is3D) {
    this.is3D = is3D;
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

  /*
   * Gets isBlockbuster value of this movie
   */
  public boolean isBlockbuster() {
    return this.isBlockbuster;
  }

  /*
   * Gets is3D value of this movie
   */
  public boolean is3D() {
    return this.is3D;
  }

  public String getTitle() {
    return this.title;
  }
}
