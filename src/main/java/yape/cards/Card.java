package yape.cards;

/**
 * A card from the standard 52-card deck.
 */
public class Card implements Comparable<Card> {
	public static enum Rank {
		ACE(14),
		KING(13),
		QUEEN(12),
		JACK(11),
		TEN(10),
		NINE(9),
		EIGHT(8),
		SEVEN(7),
		SIX(6),
		FIVE(5),
		FOUR(4),
		THREE(3),
		TWO(2);
		
		private int order;
		/**
		 * Get an integer that can be used to sort the cards, but satisfies the
		 * additional constraint that two adjacent cards are separated by 1.
		 * For example, JACK - TEN = 1.
		 */
		public int getOrder() {
			return order;
		}
		private Rank(int order) {
			this.order = order;
		}
	}
	
	public static enum Suit implements Comparable<Suit> {
		HEARTS,
		DIAMONDS,
		CLUBS,
		SPADES;
	}
	
	private Rank rank;
	private Suit suit;
	
	public Card(Rank r, Suit s) {
		rank = r;
		suit = s;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	/**
	 * Imposes a total ordering on all the cards. Aces are the highest-ordered card.
	 * If two cards' ranks are the same, the decision is based on an (arbitrary)
	 * ordering of the suits.
	 */
	public int compareTo(Card o) {
		if(rank == o.rank)
			return suit.compareTo(o.suit);
		else
			return rank.compareTo(o.rank);
	}
	
	public String toString() {
		return rank.toString() + " of " + suit.toString();
	}
}
