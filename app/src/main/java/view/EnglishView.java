package view;

/**
 * Implements an english console view.
 */
public class EnglishView implements View {

  private static final char play = 'p';
  private static final char quit = 'q';
  private static final char hit = 'h';
  private static final char stand = 's';

  /**
   * Shows a welcome message.
  */
  public void displayWelcomeMessage() {
    System.out.println("Hello Black Jack World");
    System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
  }

  /**
  * Refreshes view.
  */
  public void refreshView() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
  }

  /**
   * Returns pressed characters from the keyboard.
  */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  /**
  * Displays a card.
  */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Hidden Card");
    } else {
      System.out.println("" + card.getValue() + " of " + card.getColor());
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Player", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Dealer", hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Has: ");
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Score: " + score);
    System.out.println("");
  }

  /**
  * Displays the winner of the game.

  * @param dealerIsWinner True if the dealer is the winner.
  */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("GameOver: ");
    if (dealerIsWinner) {
      System.out.println("Dealer Won!");
    } else {
      System.out.println("You Won!");
    }
  }

  /**
  * Gets action.
  */
  public Action getAction() {
    int inp = this.getInput();
    switch (inp) {
      case play:
        return Action.Play;
      case quit:
        return Action.Quit;
      case stand:
        return Action.Stand;
      case hit:
        return Action.Hit;
      default:
        System.out.println("INVALID INPUT!");
        return null;
    }
  }
}
