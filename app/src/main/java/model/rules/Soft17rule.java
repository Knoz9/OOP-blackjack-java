package model.rules;

import model.Player;

/**
* Rule for soft 17.
*/
public class Soft17rule implements HitStrategy {
  
  private static final int limit = 17;

  @Override
  public boolean doHit(Player dealer) {
    int dealerScore = dealer.calcScore();
    if (limit > dealerScore) {
      return true;
    }
    if (limit == dealerScore && dealer.aceChecker()) {
      return true;
    }
    return false; 
  }  
}