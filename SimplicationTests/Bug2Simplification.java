import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//////////////////////////////////////////////
//These tests only pass after the bug is fixed
//////////////////////////////////////////////
public class Bug2Simplification {

	Dice d1;
	Dice d2;
	Dice d3;
	Player player;
	int bet;
	Game game;

	@Before
	public void setUp() throws Exception {
		d1 = new Dice();
        d2 = new Dice();
        d3 = new Dice();

        player = new Player("Fred", 100);
        bet = 5;
        game = new Game(d1, d2, d3);
	}

	@After
	public void tearDown() throws Exception {
		d1 = null;
		d2 = null;
		d3 = null;
		player = null;
		game = null;
	}
	
	@Test
	public void testDemonstrateBug() throws Exception {		
		Main.main(null);
	}
		
			//Bug 2 appears in the Main class, inside the while statement that controls the 100 games
	@Test
	public void testBettingLimitReachedFail() throws Exception {
		DiceValue pick = DiceValue.CROWN;
		while (player.balanceExceedsLimitBy(bet))
        {
			game.playRound(player, pick, bet);
					//If statement nullifies the player in the tearDown() method, since we want to clear the balance, and starts the test again after
					//reinitializing the objects with the setUp() method.  This is done because we want to eliminate the test resulting in the player winning at 
					//a balance of 200 since we are only testing what happens when the player losses a game
			if (player.getBalance() == 200) {
				tearDown();
				setUp();
				testBettingLimitReachedFail();
			}
		}
		assertTrue(player.getBalance() == 0);
	}
	
			//Bug 2 found inside the Player class' balanceExceedsLimitBy() method
	@Test
	public void testBalanceExceedsLimitBy() throws Exception {
		DiceValue pick = DiceValue.CROWN;
		while (player.balanceExceedsLimitBy(bet))
        {
			game.playRound(player, pick, bet);
					//If statement nullifies the player in the tearDown() method, since we want to clear the balance, and starts the test again after
					//reinitializing the objects with the setUp() method.  This is done because we want to eliminate the test resulting in the player winning at 
					//a balance of 200 since we are only testing what happens when the player losses a game
			if (player.getBalance() == 200) {
				tearDown();
				setUp();
				testBalanceExceedsLimitBy();
			}
		}
		
		System.out.println("At program end, End balance: " + player.getBalance());
		boolean testBool;
		testBool = player.balanceExceedsLimitBy(bet);
		System.out.println("At program end, balanceExceedsLimitBy() returns: " + testBool);
		assertTrue(testBool == false);
		assertTrue(player.getBalance() == 0);
		
	}

}
