package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.observer.InterfaceObserver;

/**
 * Player class.
*/
public class Player {

  private List<Card> hand;
  protected final int maxScore = 21;
  private ArrayList<InterfaceObserver> observers;

  public Player() {
    hand = new LinkedList<Card>();
    observers = new ArrayList<InterfaceObserver>();
  }

  /**
   * Deals a card.
  */
  public void dealCard(Card addToHand) {
    hand.add(addToHand);
    pingObservers();
  }

  /**
   * Gets hand.
  */
  public Iterable<Card> getHand() {
    return hand;
  }

  /**
   * Clears hand.
  */
  public void clearHand() {
    hand.clear();
  }

  /**
   * Shows hand.
  */
  public void showHand() {
    for (Card c : getHand()) {
      c.show(true);
      pingObservers();
    }
  }

  /**
  * Checks for ace.
  */
  public boolean aceChecker() {
    for (Card card : hand) {
      if (card.getValue() == Card.Value.Ace) {
        return true;
      }
    }
    return false;
  }

  /**
   * Calculates the score.
  */
  public int calcScore() {
    int[] cardScores = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
    assert (cardScores.length == Card.Value.Count.ordinal());

    int score = 0;

    for (Card c : getHand()) {
      if (c.getValue() != Card.Value.Hidden) {
        score += cardScores[c.getValue().ordinal()];
      }
    }
    if (score > maxScore) {
      for (Card c : getHand()) {
        if (c.getValue() == Card.Value.Ace && score > maxScore) {
          score -= 10;
        }
      }
    }
    return score;
  }

  /**
   * Adds observer.
  */
  public void addObservers(InterfaceObserver observer) {
    observers.add(observer);
  }

  /**
   * Notify observer.
  */
  private void pingObservers() {
    for (InterfaceObserver observer : observers) {
      observer.dataToObserver();
    }
  }
}
