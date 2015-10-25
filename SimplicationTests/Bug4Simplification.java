import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Bug4Simplification {
	
	Dice d1;
	Dice d2;
	Dice d3;
	Player player;
	int bet;
	int limit;
	Game game;
	List<DiceValue> cdv;

	@Before
	public void setUp() throws Exception {
		d1 = new Dice();
        d2 = new Dice();
        d3 = new Dice();

        player = new Player("Fred", 100);
        bet = 5;
        limit = 0;
        player.setLimit(limit);
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
	public void testDemonstrateBug() throws Exception {		
		Main.main(null);
	}
	
			//Bug 4 exists in Main's main() method
	@Test
	public void testMainForStatement() throws Exception {		
		for (int i = 0; i < 2; i++)
        {
        	String name = "Fred";
        	int balance = 100;
        	int limit = 0;
            player = new Player(name, balance);
            player.setLimit(limit);
            int bet = 5;

            System.out.println(String.format("Start Game %d: ", i));
            System.out.println(String.format("%s starts with balance %d, limit %d", 
            		player.getName(), player.getBalance(), player.getLimit()));

            int turn = 0;
            
            while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
            {
                turn++;
            	DiceValue pick = DiceValue.getRandom();
               
            	System.out.printf("Turn %d: ",turn); 
            	
            	game.playRound(player, pick, bet);
                cdv = game.getDiceValues();
                
                System.out.printf("Rolled %s, %s, %s\n",
                		cdv.get(0), cdv.get(1), cdv.get(2));
            }
        }
	}

			//Bug 4 exists when the playRound() method is called
	@Test
	public void testPlayRoundMethod() throws Exception {
		DiceValue pick = DiceValue.getRandom();
		for (int j=0; j<5; j++) {
			game.playRound(player, pick, bet);
			System.out.println(game.getDiceValues().toString());
		}
		
	}
	
}
