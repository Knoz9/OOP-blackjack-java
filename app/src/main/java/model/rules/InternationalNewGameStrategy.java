package model.rules;

import model.Dealer;
import model.Player;


class InternationalNewGameStrategy implements NewGameStrategy {
  private Dealer ndealer;

  public boolean newGame(Dealer dealer, Player player) {
    ndealer = dealer;
    ndealer.dealCard(player, true);
    ndealer.dealCard(dealer, true);
    ndealer.dealCard(player, true);


    return true;
  }
}