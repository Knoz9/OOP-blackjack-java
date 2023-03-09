package controller;

import model.Game;
import model.observer.InterfaceObserver;
import view.View;
import view.View.Action;

/**
* Start game class. Checks input, initiates observer.
*/
public class StartGame implements InterfaceObserver {
  private int sleep = 1000;
  private View obsview;
  private Game obsgame;

  /**
  * Class constructor.
  */
  public StartGame(Game game, View view) {
    this.obsview = view;
    this.obsgame = game;
    this.obsgame.newGameObserv(this);
  }
  
  /**
  * Starts the game.
  */
  public boolean play() {
    obsview.displayWelcomeMessage();

    Action userChoice = obsview.getAction();
    if (userChoice == Action.Play) {
      obsgame.newGame();

    } else if (userChoice == Action.Hit) {
      obsgame.hit();

    } else if (userChoice == Action.Stand) {
      obsgame.stand();
      obsgame.isGameOver();
    }
    return userChoice != Action.Quit;
  }

  @Override
  public void dataToObserver() {
    try {
      obsview.refreshView();
      obsview.displayDealerHand(obsgame.getDealerHand(), obsgame.getDealerScore());
      obsview.displayDealerHand(obsgame.getPlayerHand(), obsgame.getPlayerScore());

      if (obsgame.isGameOver()) {
        obsview.displayGameOver(obsgame.isDealerWinner());
      }
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}