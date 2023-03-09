package model.rules;

import model.Player;

class IfPlayerWinner implements InterfaceWinStrat {

  @Override
  public boolean isDealerWinner(Player player, Player dealer, int maxscore) {
    if (maxscore < player.calcScore()) {
      return true;
    } else if (maxscore < dealer.calcScore()) {
      return false;
    }
    return dealer.calcScore() >= player.calcScore();
  }

}