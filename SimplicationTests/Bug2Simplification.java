import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Bug2Simplification {

	Dice d1;
	Dice d2;
	Dice d3;
	Player player;
	int bet;
	Game game;
	List<DiceValue> cdv;

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
		cdv = null;
	}
	
	@Test
	public void testDemonstrateBug() throws Exception {		
		Main.main(null);
	}
		
			//Bug 2 appears in the Main class, inside the while statement that controls the 100 games
	@Test
	public void testBettingLimitReachedFail() throws Exception {
		DiceValue pick = DiceValue.CROWN;
		while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
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

				//However the game should stop when the balance is 0, which it does not due to the bug
		assertFalse(player.getBalance() == 0);
				//The bug results in the game stopping when the balance is 5
		assertTrue(player.getBalance() == 5);
	}
	
			//Bug 2 found inside the Player class' balanceExceedsLimitBy() method
	@Test
	public void testBalanceExceedsLimitByMethodLogic() throws Exception {
		int balance = 5;
		int limit = 0;
				//The balanceExceedsLimitBy() method returns a boolean result of the following: balance - bet > limit
				//By this logic, if the balance is 5, as currently is at the end of all loosing games, and the bet (5) is subtracted then the result is 0, when
				//compared to the limit (0) the statement determines that 0 is not greater than 0 and returns false when the balance is still 5.  However, we 
				//want the program to allow the player to bet until their balance is 0, therefore we want this method to return true when the balance is 5, since
				//the player can bet said 5 and win.
		assertFalse(balance - bet > limit);
	}

}
