package view;

/**
a* Encapsulates the generic view functionality.
*/
public interface View {

  public View.Action getAction();

  /**
  * Set of actions.
  */
  public enum Action { Play, Quit, Stand, Hit 
  }

  void displayWelcomeMessage();

  void refreshView();

  void displayCard(model.Card card);

  void displayPlayerHand(Iterable<model.Card> hand, int score);

  void displayDealerHand(Iterable<model.Card> hand, int score);

  void displayGameOver(boolean dealerIsWinner);
}
