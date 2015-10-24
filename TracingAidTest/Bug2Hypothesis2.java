import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Bug2Hypothesis2 {
	int bet;

	@Before
	public void setUp() throws Exception {
		bet = 5;
	}

	@After
	public void tearDown() throws Exception {
	}
	
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
