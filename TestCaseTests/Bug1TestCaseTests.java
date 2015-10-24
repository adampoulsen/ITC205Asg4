import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//////////////////////////////////////////////
//These tests only pass after the bug is fixed
//////////////////////////////////////////////
public class Bug1TestCaseTests {

	Dice d1;
	Dice d2;
	Dice d3;
	Player player;
	int bet;
	Game game;
	List<DiceValue> cdv;

	@Before
	public void setUp() throws Exception {
        d1 = mock(Dice.class);
        d2 = mock(Dice.class);
        d3 = mock(Dice.class);

        player = new Player("Fred", 100);
        bet = 5;
        game = new Game(d1, d2, d3);
        cdv = game.getDiceValues();
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
	public void test1To1OddsPayoutOf5IncreaseBalanceFail() {
		DiceValue pick = DiceValue.CROWN;
		when(d1.getValue()).thenReturn(DiceValue.CROWN);
		when(d2.getValue()).thenReturn(DiceValue.HEART);
		when(d3.getValue()).thenReturn(DiceValue.ANCHOR);
		
		System.out.println("Current balance: " + player.getBalance());
		
		assertTrue(player.getBalance() == 100);
		int winnings = game.playRound(player, pick, bet);
		
		assertTrue(winnings == 5);
		assertTrue(player.getBalance() == 105);
		
		cdv = game.getDiceValues();
        System.out.printf("Rolled %s, %s, %s\n",
        		cdv.get(0), cdv.get(1), cdv.get(2));
         
         
        System.out.printf("%s won %d, balance now %d\n\n",
        player.getName(), winnings, player.getBalance());
         
	}
	
	@Test
	public void test2To1OddsPayoutOf10IncreaseBalanceFail() {
		DiceValue pick = DiceValue.CROWN;
		when(d1.getValue()).thenReturn(DiceValue.CROWN);
		when(d2.getValue()).thenReturn(DiceValue.CROWN);
		when(d3.getValue()).thenReturn(DiceValue.ANCHOR);
		
		System.out.println("Current balance: " + player.getBalance());
		
		assertTrue(player.getBalance() == 100);
		int winnings = game.playRound(player, pick, bet);
		
		assertTrue(winnings == 10);
		assertTrue(player.getBalance() == 110);
		
		cdv = game.getDiceValues();
        System.out.printf("Rolled %s, %s, %s\n",
        		cdv.get(0), cdv.get(1), cdv.get(2));
        
        System.out.printf("%s won %d, balance now %d\n\n",
        player.getName(), winnings, player.getBalance());
  
	}
	
	@Test
	public void test3To1OddsPayoutOf15IncreaseBalanceFail() {
		DiceValue pick = DiceValue.CROWN;
		when(d1.getValue()).thenReturn(DiceValue.CROWN);
		when(d2.getValue()).thenReturn(DiceValue.CROWN);
		when(d3.getValue()).thenReturn(DiceValue.CROWN);
		
		System.out.println("Current balance: " + player.getBalance());
		
		assertTrue(player.getBalance() == 100);
		int winnings = game.playRound(player, pick, bet);
		
		assertTrue(winnings == 15);
		assertTrue(player.getBalance() == 115);
		
		cdv = game.getDiceValues();
        System.out.printf("Rolled %s, %s, %s\n",
        		cdv.get(0), cdv.get(1), cdv.get(2));
        
        System.out.printf("%s won %d, balance now %d\n\n",
        player.getName(), winnings, player.getBalance());

	}

}
