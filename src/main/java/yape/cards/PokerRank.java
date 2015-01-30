package yape.cards;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import yape.cards.Card.Rank;
import yape.cards.Card.Suit;

public enum PokerRank {
	ROYAL_FLUSH,
	STRAIGHT_FLUSH,
	FOUR_OF_A_KIND,
	FULL_HOUSE,
	FLUSH,
	STRAIGHT,
	THREE_OF_A_KIND,
	TWO_PAIR,
	ONE_PAIR,
	HIGH_CARD;
	
	private static List<Rank> highCards;
	
	/**
	 * Like compareTo for two hands - return -1 if hand1 wins, 1 if hand2 wins,
	 * or 0 if they're equal.
	 */
	public static int compareHands(List<Card> hand1, List<Card> hand2) {
		List<Rank> highCards1, highCards2;
		PokerRank rank1 = rank(hand1);
		highCards1 = highCards;
		PokerRank rank2 = rank(hand2);
		highCards2 = highCards;
		
		int comp = rank1.compareTo(rank2);
		if(comp != 0)
			return comp;
		
		// The two hands have the same PokerRank, so we need to go by high card.
		Iterator<Rank> hc1it = highCards1.iterator();
		Iterator<Rank> hc2it = highCards2.iterator();
		
		while(hc1it.hasNext())
		{
			comp = hc1it.next().compareTo(hc2it.next());
			if(comp != 0)
				return comp;
		}
		
		return 0;
	}
	
	/**
	 * Get the rank of the given hand. For now, only works with 5 card-hands.
	 * @warning This method will sort the list!
	 * @param hand A list of cards to rank.
	 */
	public static PokerRank rank(List<Card> hand) {
		Collections.sort(hand);
		
		boolean flush = isFlush(hand);
		boolean straight = isStraight(hand);
		
		if(flush && straight)
		{
			if(hand.get(0).getRank() == Rank.ACE)
				return ROYAL_FLUSH;
			else
				return STRAIGHT_FLUSH;
		}
		
		if(isFourOfAKind(hand))
			return FOUR_OF_A_KIND;
		
		if(isFullHouse(hand))
			return FULL_HOUSE;
		
		if(flush)
			return FLUSH;
		
		if(straight)
			return STRAIGHT;
		
		if(isThreeOfAKind(hand))
			return THREE_OF_A_KIND;
		
		if(isTwoPair(hand))
			return TWO_PAIR;
		
		if(isOnePair(hand))
			return ONE_PAIR;
		
		// Setup the high cards before returning.
		highCards = new LinkedList<Card.Rank>();
		for(Card c : hand)
			highCards.add(c.getRank());
		return HIGH_CARD;
	}

	
	private static boolean isOnePair(List<Card> hand) {
		highCards = new LinkedList<Card.Rank>();
		Set<Rank> ranks = getUniqueRanks(hand);
		if(ranks.size() > 4)
			return false;
		
		for(Rank r : ranks)
			if(countRanks(hand, r) == 2)
			{
				ranks.remove(r);
				highCards.addAll(ranks);
				Collections.sort(highCards);
				highCards.add(0, r);
				return true;
			}
		
		return false;
	}


	private static boolean isTwoPair(List<Card> hand) {
		highCards = new LinkedList<Card.Rank>();
		Set<Rank> ranks = getUniqueRanks(hand);
		if(ranks.size() > 3)
			return false;
		
		boolean foundSingleCard = false;
		
		for(Rank r : ranks)
			if(countRanks(hand, r) != 2)
			{
				if(foundSingleCard)
					return false;
				else
					foundSingleCard = true;
			}
			else
			{
				highCards.add(r);
			}
		
		// Now fix the high card list.
		Collections.sort(highCards);
		ranks.removeAll(highCards);
		for(Card c : hand)
			if(ranks.contains(c) && !highCards.contains(c.getRank()))
				highCards.add(c.getRank());
		
		return true;
	}


	private static boolean isThreeOfAKind(List<Card> hand) {
		highCards = new LinkedList<Card.Rank>();
		Set<Rank> ranks = getUniqueRanks(hand);
		if(ranks.size() > 3)
			return false;
		
		for(Rank r : ranks)
			if(countRanks(hand, r) == 3)
			{
				ranks.remove(r);
				highCards.addAll(ranks);
				Collections.sort(highCards);
				highCards.add(0, r);
				return true;
			}
		
		
		return false;
	}

	
	private static boolean isFourOfAKind(List<Card> hand) {
		highCards = new LinkedList<Card.Rank>();
		Set<Rank> ranks = getUniqueRanks(hand);
		if(ranks.size() != 2)
			return false;
		
		Card.Rank r = ranks.iterator().next();
		switch(countRanks(hand, r))
		{
		case 1:
			highCards.add(r);
			ranks.remove(r);
			highCards.add(0, ranks.iterator().next());
			return true;
		case 4:
			highCards.add(r);
			ranks.remove(r);
			highCards.addAll(ranks);
			return true;
		default:
			return false;
		}
	}
	
	
	private static boolean isFullHouse(List<Card> hand) {
		highCards = new LinkedList<Card.Rank>();
		Set<Rank> ranks = getUniqueRanks(hand);
		if(ranks.size() != 2)
			return false;
		
		Card.Rank r = ranks.iterator().next();
		switch(countRanks(hand, r))
		{
		case 2:
			highCards.add(r);
			ranks.remove(r);
			highCards.add(0, ranks.iterator().next());
			return true;
		case 3:
			highCards.add(r);
			ranks.remove(r);
			highCards.addAll(ranks);
			return true;
		default:
			return false;
		}
	}
	
	
	private static boolean isStraightFlush(List<Card> hand) {
		if(!isStraight(hand))
			return false;
		
		return isFlush(hand);
	}


	private static boolean isFlush(List<Card> hand) {
		Suit s = hand.get(0).getSuit();
		for(Card card : hand)
		{
			if(card.getSuit() != s)
				return false;
		}
		
		return true;
	}


	private static boolean isStraight(List<Card> hand) {
		int topValue = hand.get(0).getRank().getOrder();
		for(Card card : hand)
		{
			if(card.getRank().getOrder() != topValue)
				return false;
			
			topValue--;
		}
		
		return true;
	}
	
	private static Set<Rank> getUniqueRanks(List<Card> hand) {
		HashSet<Rank> ranks = new HashSet<Rank>();
		
		for(Card card : hand)
			ranks.add(card.getRank());
		
		return ranks;
	}
	
	private static int countRanks(List<Card> hand, Rank rank) {
		int count = 0;
		for(Card c : hand)
			if(c.getRank() == rank)
				count++;
		return count;
	}
}
