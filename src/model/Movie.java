package model;
import enums.*;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Movie class
 * Contains details of movie and get/update functions
 *
 @author Roy Leong, Augustine Lee, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class Movie implements Serializable {
   /**
   * Title of the movie
   */
  private String title;

   /**
   * Showing status of the movie (enum)
   */
  private ShowStatus showingStatus;

   /**
   * Synopsis of the movie
   */
  private String synopsis;

  /**
   * Director in charge of the movie
   */
  private String director;

  /**
   * Arraylist of cast performing in the movie
   */
  private ArrayList<String> cast;

  /**
   * Arraylist of reviews given to the movie
   */
  private ArrayList<Review> reviews = new ArrayList<Review>();

  /**
   * Advisory rating of the movie (enum)
   */
  private Advisory advisoryRating;

  /**
   * Movie genre (enum)
   */
  private Genre genre;

  /**
   * Type of movie (enum)
   */
  private MovieType type;

  /**
   * Constructor for Movie
   * @param title title of the movie
   * @param showingStatus showing status of the movie (enum)
   * @param synopsis synopsis of the movie
   * @param director director in charge of the movie
   * @param cast arraylist of cast performing in the movie
   * @param advisoryRating advisory rating of the movie (enum)
   * @param genre movie genre (enum)
   * @param type type of movie (enum)
   */
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
  
  /**
   * Adds a new review to the arraylist of reviews for the movie
   * 
   * @param review review object to add
   */
  public void addReview(Review review) {
    reviews.add(review);
  }

  /**
   * Setter method to set movie title
   * 
   * @param title title of movie
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Setter method to change the status of the movie
   * 
   * @param showStatus status to update movie to
   */
  public void setShowingStatus(ShowStatus showStatus) {
    this.showingStatus = showStatus;
  }

  /**
   * Setter method to set the synopsis of the movie
   * 
   * @param synopsis synopsis of movie
   */
  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  /**
   * Setter method to change the director in charge of the movie
   * 
   * @param director director in charge of the movie
   */
  public void setDirector(String director) {
    this.director = director;
  }
  
  /**
   * Setter method to change the cast of the movie
   * 
   * @param cast arraylist of cast members in the movie
   */
  public void setCast(ArrayList<String> cast) {
    this.cast = cast;
  }

  /**
   * Setter method to change the advisory rating of the movie
   * 
   * @param advisoryRating advisory rating (enum) of the movie
   */
  public void setAdvisoryRating(Advisory advisoryRating) {
    this.advisoryRating = advisoryRating;
  }

  /**
   * Setter method to change the genre of the movie
   * 
   * @param genre genre (enum) of the movie
   */
  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  /**
   * Setter method to change the type of the movie
   * 
   * @param type type (enum) of the movie
   */
  public void setMovieType(MovieType type) {
    this.type = type;
  }
  
  /**
   * Getter method to get the title of the movie
   * 
   * @return title of the movie
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Getter method to get the showing status of the movie
   * 
   * @return showing status of the movie
   */
  public ShowStatus getShowingStatus() {
    return this.showingStatus;
  }

  /**
   * Getter method to get the synopsis of the movie
   * 
   * @return synopsis of the movie
   */
  public String getSynopsis() {
    return this.synopsis;
  }

  /**
   * Getter method to get the director of the movie
   * 
   * @return director of the movie
   */
  public String getDirector() {
    return this.director;
  }
  
  /**
   * Getter method to get an arraylist of cast performing in the movie
   * 
   * @return arraylist of cast in the movie
   */
  public ArrayList<String> getCast() {
    return this.cast;
  }

  /**
   * Getter method to get the advisory rating of the movie
   * 
   * @return advisory rating (enum) of the movie
   */
  public Advisory getAdvisoryRating() {
    return this.advisoryRating;
  }

  /**
   * Getter method to get the genre of the movie
   * 
   * @return genre (enum) of the movie
   */
  public Genre getGenre() {
    return this.genre;
  }

  /**
   * Getter method to get the type of the movie
   * 
   * @return type (enum) of the movie
   */
  public MovieType getMovieType() {
    return this.type;
  }

  /**
   * Getter method to get an arraylist of reviews for the movie
   * 
   * @return arraylist of reviews for the movie
   */
  public ArrayList<Review> getReviews() {
    return this.reviews;
  }

  /**
   * Gets the overall rating from all the reviews of this movie
   * 
   * @return the average rating of the movie
   */
  public Float getOverallRating() {
    // loop through the list of reviews and get the average rating
    float total = 0;
    for (Review review : reviews) {
      total += review.getRating();
    }
    total /= reviews.size();
    return total;
  }
}
