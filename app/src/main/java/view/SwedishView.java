package view;

/**
 * Implements an english console view.
 */
public class SwedishView implements View {

  private static final char play = 'p';
  private static final char quit = 'q';
  private static final char hit = 'h';
  private static final char stand = 's';

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    System.out.println("Hej Black Jack Världen");
    System.out.println("Skriv " + "\'" + play + "\' för att Spela, " + "\'" + hit + "\' för nytt Kort, " + "\'"
        + stand + "\' för att Stanna, " + "\'" + quit + "\' för att avsluta\n");
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
      System.out.println("Dolt Kort");
    } else {
      String[] colors = { "Hjärter", "Spader", "Ruter", "Klöver" };
      String[] values = { "två", "tre", "fyra", "fem", "sex", "sju",
        "åtta", "nio", "tio", "knekt", "dam", "kung", "ess" };
      System.out.println("" + colors[card.getColor().ordinal()] + " " + values[card.getValue().ordinal()]);
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Spelare", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Croupier", hand, score);
  }

  /**
  * Displays a hand and score.
  */
  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    if (!hand.iterator().hasNext()) {
      System.out.println(name + " Har inget kort ännu.\n");
    } else {
      System.out.println(name + " Har: ");
      for (model.Card c : hand) {
        displayCard(c);
      }
      System.out.println("Poäng: " + score);
      System.out.println("");
    }
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("Spelet slut: ");
    if (dealerIsWinner) {
      System.out.println("Croupier vann!");
    } else {
      System.out.println("Du vann!");
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
        System.out.println("FELAKTIG INMATNING!");
        return null;
    }
  }
}
