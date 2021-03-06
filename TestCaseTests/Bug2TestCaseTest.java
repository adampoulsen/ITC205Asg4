import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Bug2TestCaseTest {
	
	Player player;
	Game game;
	int bet;
	Dice d1;
	Dice d2;
	Dice d3;
	List<DiceValue> cdv;

	@Before
	public void setUp() throws Exception {
		player = new Player("Fred", 100);
        bet = 5;
        d1 = new Dice();
        d2 = new Dice();
        d3 = new Dice();
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
	}

	@Test
	public void testUAT() throws Exception {
		DiceValue pick = DiceValue.CROWN;
		while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
        {
        	System.out.printf("%s bet %d on %s\n",
        			 player.getName(), bet, pick); 
        	
        	int winnings = game.playRound(player, pick, bet);
    	   
            cdv = game.getDiceValues();
            
            System.out.printf("Rolled %s, %s, %s\n",
            		cdv.get(0), cdv.get(1), cdv.get(2));
            
            if (winnings > 0) {
                System.out.printf("%s won %d, balance now %d\n\n",
                		player.getName(), winnings, player.getBalance());
            }
            else {
                System.out.printf("%s lost, balance now %d\n\n",
                		player.getName(), player.getBalance());
            }
            if (player.getBalance() > 200) {
    	    	tearDown();
    	    	setUp();
    	    	testUAT();
    		}
        }

        System.out.print(String.format("End Game %d: ", 1));
        System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

	    assertTrue(player.getBalance() == 0); 
	}
}

