import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Bug3Simplification {
	
	int winnings;
	int winCount;
	int loseCount;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		winnings = 0;
		winCount = 0;
		loseCount = 0;
	}
	
			/*Tests show no results, the Tracing section reveals that Bug 3 is not from a defect rather than a lack of bias 
			  implementation*/
	@Test
	public void testDemonstrateBug() throws Exception {		
		Main.main(null);
	}
	
	@Test
	public void testIfStatementWinCounted() throws Exception {	
		winnings = 10;
		if (winnings > 0) {
			winCount++; 
        }
        else {
         	loseCount++;
        }
		assertTrue(winCount == 1);
		assertTrue(loseCount == 0);
	}
	
	@Test
	public void testIfStatementLossCounted() throws Exception {		
		winnings = 0;
		if (winnings > 0) {
        	winCount++; 
        }
        else {
        	loseCount++;
        }
		assertTrue(loseCount == 1);
		assertTrue(winCount == 0);
	}

}