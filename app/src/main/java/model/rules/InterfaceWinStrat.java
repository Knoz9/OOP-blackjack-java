package model.rules;

import model.Player;

/**
* Interface for win strategy.
*/
public interface InterfaceWinStrat {
  boolean isDealerWinner(Player player, Player dealer, int maxscore);
}