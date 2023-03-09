package model;

import model.rules.HitStrategy;
import model.rules.InterfaceWinStrat;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;

/**
 * Represents a dealer player that handles the deck of cards and runs the game using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private InterfaceWinStrat winStrat;

  /**
   * Initializing constructor.

   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {
    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    winStrat = rulesFactory.getWinRule();
  }

  /**
   * Starts a new game if the game is not currently under way.

   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      System.out.println();
      return newGameRule.newGame(this, player);
    }
    return false;
  }

  /**
   * Removes duplicate code, so we dont need to type this manually.
   */
  public void dealCard(Player player, boolean show) {
    Card.Mutable c;
    c = deck.getCard();
    c.show(show);
    player.dealCard(c);
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.

   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      dealCard(player, true);
      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param player The player to check agains.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    return winStrat.isDealerWinner(player, this, maxScore);
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.

   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * The player has choosen to take no more cards, it is the dealers turn.
   */
  public boolean stand() {
    if (deck != null && !isGameOver()) {
      showHand();
    }
    while (hitRule.doHit(this)) {
      dealCard(this, true);   
    }   
    return false;
  }   
}

