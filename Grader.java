import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Grader {
	// HW-specific constants;
	public static final int ROYAL_FLUSH = 9;
	public static final int STRAIGHT_FLUSH = 8;
	public static final int FOUR_OF_A_KIND = 7;
	public static final int FULL_HOUSE = 6;
	public static final int FLUSH = 5;
	public static final int STRAIGHT = 4;
	public static final int THREE_OF_A_KIND = 3;
	public static final int TWO_PAIR = 2;
	public static final int ONE_PAIR = 1;
	public static final int LOSER = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintWriter performanceReport = null;
		try {
			performanceReport = new PrintWriter(new File(args[0]
					+ "Test reports.txt"));
			performanceReport.println("Test Results for " + args[0]);
			verifyClassAndMethodExistence(performanceReport);
			performTests(performanceReport);
			performanceReport.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			performanceReport.print("Missing a method...");
			performanceReport.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Recommended Strategy: check for method presence and return
	 * type, then perform tests on actual method.
	 * 
	 * @param reporter
	 */
	public static void verifyClassAndMethodExistence(
			PrintWriter reporter) {
		try {
			Class<?> s = Class.forName("PokerStats");

			// Checking for the required constants, but not checking
			// their values...This will be checked indirectly by the
			// performance of their program, since this Grader has the
			// correct values.
			String[] constants = { "ROYAL_FLUSH", "STRAIGHT_FLUSH",
					"FOUR_OF_A_KIND", "FULL_HOUSE", "FLUSH",
					"STRAIGHT", "THREE_OF_A_KIND", "TWO_PAIR",
					"ONE_PAIR", "LOSER" };
			Field[] studentConstants = s.getDeclaredFields();

			for (String c : constants) {
				boolean inTheirCode = false;
				for (Field f : studentConstants)
					if (c.equals(f.getName()))
						inTheirCode = true;
				if (!inTheirCode)
					reporter.println("The constant " + c
							+ " was not found in the student's code");
			}

			for (Field f : studentConstants) {
				boolean inMyCode = false;
				for (String c : constants)
					if (f.getName().equals(c))
						inMyCode = true;
				if (!inMyCode)
					reporter.println("The constant "
							+ f.getName()
							+ " was found in the student's code but should not have been there.");
			}

			// Checking for method presence and return type...........
			try {
				Method m = s
						.getMethod("evaluateOneHandOfPoker", null);
				if (!m.getReturnType().equals(int.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType() + "instead of int.");

				m = s.getMethod("hasFlush", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasFourOfAKind", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasFullHouse", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasOnePair", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasRoyalFlush", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasStraight", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasStraightFlush", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasThreeOfAKind", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("hasTwoPair", Card[].class);
				if (!m.getReturnType().equals(boolean.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of boolean.");

				m = s.getMethod("printHand", Card[].class);
				m = s.getMethod("showStats", int[].class);
				m = s.getMethod("sort", Card[].class);

			}
			catch (NoSuchMethodException e) {
				reporter.println(e.toString());
			}

		}
		catch (SecurityException e) {
			e.printStackTrace();
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calls specific methods that are supposed to be in the student's
	 * homework and runs tests to verify output and other expected
	 * behavior.
	 * 
	 * @param reporter
	 *            Writes the results of the tests to a text file.
	 */
	public static void performTests(PrintWriter reporter) {
		Deck tempDeck = new Deck();
		Card[] deck = new Card[52];
		for (int i = 0; i < 52; i++)
			deck[i] = tempDeck.deal();
		tempDeck = null;

		Card[] handToDeal = new Card[5];

		// A,K,Q,J,10 HEARTS
		handToDeal[0] = deck[13];// A
		handToDeal[1] = deck[23];// J
		handToDeal[2] = deck[25];// K
		handToDeal[3] = deck[24];// Q
		handToDeal[4] = deck[22];// 10

		testHand(reporter, handToDeal, "bfffttfftt");

		// 2,3,4,5,6 Clubs
		handToDeal[0] = deck[3];// 4
		handToDeal[1] = deck[5];// 6
		handToDeal[2] = deck[1];// 2
		handToDeal[3] = deck[2];// 3
		handToDeal[4] = deck[4];// 5

		testHand(reporter, handToDeal, "bfffttfftf");

		// 2,3,4,5 Clubs, 6 diamonds
		handToDeal[0] = deck[3];// 4
		handToDeal[1] = deck[44];// 6D
		handToDeal[2] = deck[1];// 2
		handToDeal[3] = deck[2];// 3
		handToDeal[4] = deck[4];// 5

		testHand(reporter, handToDeal, "bffftfffff");

		// 7,7,7,7,6 HEARTS.... 4 of a kind
		handToDeal[0] = deck[6];// 7
		handToDeal[1] = deck[19];// 7
		handToDeal[2] = deck[18];// 6
		handToDeal[3] = deck[32];// 7
		handToDeal[4] = deck[45];// 7

		testHand(reporter, handToDeal, "btttffftff");

		// 5S, 5D, 8C, 8D, 8H
		handToDeal[0] = deck[7];// 8C
		handToDeal[1] = deck[43];// 5D
		handToDeal[2] = deck[20];// 8H
		handToDeal[3] = deck[30];// 5S
		handToDeal[4] = deck[46];// 8D

		testHand(reporter, handToDeal, "btttfftfff");

		// 5S, 6D, 8C, 8D, 8H
		handToDeal[0] = deck[7];// 8C
		handToDeal[1] = deck[44];// 6D
		handToDeal[2] = deck[20];// 8H
		handToDeal[3] = deck[30];// 5S
		handToDeal[4] = deck[46];// 8D

		testHand(reporter, handToDeal, "btftffffff");

		// 10,J,K,Q CLUBS, A Spades
		handToDeal[0] = deck[9];// 10C
		handToDeal[1] = deck[11];// JC
		handToDeal[2] = deck[13];// KC
		handToDeal[3] = deck[26];// AS
		handToDeal[4] = deck[12];// QS

		testHand(reporter, handToDeal, "bffftfffff");

		// 10,J,K,Q CLUBS, 5 Spades... loser hand
		handToDeal[0] = deck[9];// 10C
		handToDeal[1] = deck[11];// JC
		handToDeal[2] = deck[13];// KC
		handToDeal[3] = deck[31];// 5S
		handToDeal[4] = deck[12];// QS

		testHand(reporter, handToDeal, "bfffffffff");

		// 6S, 6D, 2C, 4D, QH
		handToDeal[0] = deck[1];// 2C
		handToDeal[1] = deck[44];// 6D
		handToDeal[2] = deck[42];// 4D
		handToDeal[3] = deck[24];// QH
		handToDeal[4] = deck[31];// 6S

		testHand(reporter, handToDeal, "btffffffff");

		// 6S, 6D, 4C, 4D, QH
		handToDeal[0] = deck[3];// 4C
		handToDeal[1] = deck[44];// 6D
		handToDeal[2] = deck[42];// 4D
		handToDeal[3] = deck[24];// QH
		handToDeal[4] = deck[31];// 6S

		testHand(reporter, handToDeal, "bttfffffff");

		// 9,10,J,Q,K SPADES
		handToDeal[0] = deck[35];// 10
		handToDeal[1] = deck[34];// 9
		handToDeal[2] = deck[38];// K
		handToDeal[3] = deck[36];// J
		handToDeal[4] = deck[37];// Q

		testHand(reporter, handToDeal, "bfffttfftf");

		reporter.println();
		reporter.println();
		reporter.close();
	}

	/**
	 * 
	 * @param reporter
	 *            Records any errors in the test reports text file
	 * @param handToDeal
	 *            The hand that will be subjected to tests
	 * @param handAttributes
	 *            A String of 9 letters (t or f) to represent what
	 *            kind of hand it is, ignoring the Loser attribute.
	 */
	public static void testHand(PrintWriter reporter,
			Card[] handToDeal, String handAttributes) {
		int testPosition = 9;// Represents the constants of hand
								// attributes - 1

		if ((PokerStats.hasRoyalFlush(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasRoyalFlush(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Royal Flush.");
			reporter.println();

		}
		testPosition--;
		if ((PokerStats.hasStraightFlush(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasStraightFlush(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Straight Flush.");
			reporter.println();
		}
		testPosition--;
		if ((PokerStats.hasFourOfAKind(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasFourOfAKind(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Four of a Kind.");
			reporter.println();
		}
		testPosition--;

		if ((PokerStats.hasFullHouse(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasFullHouse(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Full House.");
			reporter.println();
		}
		testPosition--;

		if ((PokerStats.hasFlush(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasFlush(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Flush.");
			reporter.println();
		}
		testPosition--;

		if ((PokerStats.hasStraight(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasStraight(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Straight.");
			reporter.println();
		}
		testPosition--;

		if ((PokerStats.hasThreeOfAKind(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasThreeOfAKind(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Three of a Kind.");
			reporter.println();
		}
		testPosition--;

		if ((PokerStats.hasTwoPair(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasTwoPair(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a Two Pair.");
			reporter.println();
		}
		testPosition--;

		if ((PokerStats.hasOnePair(handToDeal) && handAttributes
				.charAt(testPosition) == 'f')
				|| (!PokerStats.hasOnePair(handToDeal) && handAttributes
						.charAt(testPosition) == 't')) {
			if (handAttributes.charAt(testPosition) == 't')
				reporter.print("Failed to recognize ");
			else reporter.print("Incorrectly reported ");
			for (Card c : handToDeal)
				reporter.print(c.toString() + " ");
			reporter.println(" as a One Pair.");
			reporter.println();
		}
		testPosition--;

		// Testing sorting--------------------------------------------
		PokerStats.sort(handToDeal);
		for (int i = 0; i < 4; i++)
			if (handToDeal[i].getValue() > handToDeal[i + 1]
					.getValue()) {
				reporter.print("Incorrectly sorted this hand: ");
				for (Card c : handToDeal)
					reporter.print(c.toString() + " ");
				reporter.println();
				break;
			}

	}
}
