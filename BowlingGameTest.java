import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;

public class BowlingGameTest extends TestCase {
	private Player g;
	
//	  protected void setUp() throws Exception {
//	    g = new Game();
//	  }

	
	  public void testMyGame() throws Exception {
	    
			String[][] frames = {
					{"1","/"},
					{"X"},
					{"X"},
					{"X"},
					{"X"},
					{"1","/"},
					{"X"},
					{"1","/"},
					{"1","/"},
					{"1","/","3"},
			};
		  
		g = new Player(frames);
		assertEquals(300, g.score());
	  }
//	
//	  private void rollMany(int n, int pins) {
//	    for (int i = 0; i < n; i++)
//	      g.roll(pins);
//	  }
//	
//	  public void testGutterGame() throws Exception {
//	    rollMany(20, 0);
//	    assertEquals(0, g.score());
//	  }
//	
//	  public void testAllOnes() throws Exception {
//	    rollMany(20,1);
//	    assertEquals(20, g.score());
//	  }
//	
//	  public void testOneSpare() throws Exception {
//	    rollSpare();
//	    g.roll(3);
//	    rollMany(17,0);
//	    assertEquals(16,g.score());
//	  }
//	
//	  public void testOneStrike() throws Exception {
//	    rollStrike();
//	    g.roll(3);
//	    g.roll(4);
//	    rollMany(16, 0);
//	    assertEquals(24, g.score());
//	  }
//	
//	  public void testPerfectGame() throws Exception {
//	    rollMany(12,10);
//	    assertEquals(300, g.score());
//	  }
//	
//	  private void rollStrike() {
//	    g.roll(10);
//	  }
//	
//	  private void rollSpare() {
//	    g.roll(5);
//	    g.roll(5);
//	  }
}
