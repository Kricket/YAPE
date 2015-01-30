package yape.yape.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import yape.cards.*;
import yape.cards.Card.Rank;
import yape.cards.Card.Suit;

/**
 * The file poker.txt contains, on each line, ten cards. The first five cards
 * are for player 1, the second five for player 2. Determine how many hands
 * are won by player 1.
 */
public class Euler54 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(
				new File("src/main/resources/poker.txt")));
		String line;
		ArrayList<Card> p1 = new ArrayList<Card>();
		ArrayList<Card> p2 = new ArrayList<Card>();
		int p1wins = 0;
		
		while((line = br.readLine()) != null)
		{
			p1.clear();
			p2.clear();
			String[] cardSymbols = line.split(" ");
			
			int i;
			for(i=0; i<5; i++)
				p1.add(getCard(cardSymbols[i]));
			for(i=5; i<10; i++)
				p2.add(getCard(cardSymbols[i]));
			
			if(PokerRank.compareHands(p1, p2) < 0)
				p1wins++;
			/*
			PokerRank pr1 = PokerRank.rank(p1);
			PokerRank pr2 = PokerRank.rank(p2);
			
			if(pr1 == pr2 && pr1.compareTo(PokerRank.HIGH_CARD) < 0)
			{
				System.out.println(line);
				System.out.println("\tp1 hand: " + pr1 + " p2 hand: " + pr2 + "\n\twinner: " + PokerRank.compareHands(p1, p2));
			}
			*/
			
		}
		
		br.close();
		System.out.println("Times player 1 won: " + p1wins);
	}
	
	

	private static Card getCard(String card) throws Exception {
		Rank r = null;
		Suit s = null;
		switch(card.charAt(0))
		{
		case '2': r = Rank.TWO; break;
		case '3': r = Rank.THREE; break;
		case '4': r = Rank.FOUR; break;
		case '5': r = Rank.FIVE; break;
		case '6': r = Rank.SIX; break;
		case '7': r = Rank.SEVEN; break;
		case '8': r = Rank.EIGHT; break;
		case '9': r = Rank.NINE; break;
		case 'T': r = Rank.TEN; break;
		case 'J': r = Rank.JACK; break;
		case 'Q': r = Rank.QUEEN; break;
		case 'K': r = Rank.KING; break;
		case 'A': r = Rank.ACE; break;
		default: throw new Exception("Invalid card rank " + card);
		}
		
		switch(card.charAt(1))
		{
		case 'D': s = Suit.DIAMONDS; break;
		case 'C': s = Suit.CLUBS; break;
		case 'H': s = Suit.HEARTS; break;
		case 'S': s = Suit.SPADES; break;
		default: throw new Exception("Invalid card suit " + card);
		}
		
		return new Card(r, s);
	}



	public static void testPokerRank() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.ACE, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.QUEEN, Suit.CLUBS));
		cards.add(new Card(Rank.TEN, Suit.CLUBS));
		cards.add(new Card(Rank.JACK, Suit.CLUBS));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.QUEEN, Suit.CLUBS));
		cards.add(new Card(Rank.TEN, Suit.CLUBS));
		cards.add(new Card(Rank.JACK, Suit.CLUBS));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.NINE, Suit.HEARTS));
		cards.add(new Card(Rank.NINE, Suit.SPADES));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.NINE, Suit.HEARTS));
		cards.add(new Card(Rank.KING, Suit.SPADES));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.CLUBS));
		cards.add(new Card(Rank.SIX, Suit.CLUBS));
		cards.add(new Card(Rank.THREE, Suit.CLUBS));
		cards.add(new Card(Rank.TEN, Suit.CLUBS));
		cards.add(new Card(Rank.JACK, Suit.CLUBS));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.QUEEN, Suit.CLUBS));
		cards.add(new Card(Rank.TEN, Suit.CLUBS));
		cards.add(new Card(Rank.JACK, Suit.CLUBS));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		cards.add(new Card(Rank.NINE, Suit.SPADES));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.NINE, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		cards.add(new Card(Rank.EIGHT, Suit.SPADES));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.SEVEN, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		cards.add(new Card(Rank.EIGHT, Suit.SPADES));
		System.out.println(PokerRank.rank(cards));

		cards = new ArrayList<Card>();
		cards.add(new Card(Rank.SEVEN, Suit.CLUBS));
		cards.add(new Card(Rank.KING, Suit.CLUBS));
		cards.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		cards.add(new Card(Rank.THREE, Suit.SPADES));
		System.out.println(PokerRank.rank(cards));

		
		ArrayList<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(Rank.SEVEN, Suit.CLUBS));
		cards2.add(new Card(Rank.KING, Suit.CLUBS));
		cards2.add(new Card(Rank.NINE, Suit.DIAMONDS));
		cards2.add(new Card(Rank.EIGHT, Suit.HEARTS));
		cards2.add(new Card(Rank.TWO, Suit.SPADES));
		
		System.out.println("Second one should lose: " + PokerRank.compareHands(cards, cards2));
	}

}
