import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Bug3Simplification {

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
	public void testDemonstrateBug() throws Exception {		
		Main.main(null);
	}

	@Test
	public void test() {
		
	}

}