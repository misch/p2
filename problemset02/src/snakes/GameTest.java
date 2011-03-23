package snakes;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(JMock.class)

public class GameTest {

	    Mockery context = new JUnit4Mockery();

	    @Test
	    public void testPlay() {
	    	
	        final IDie die = context.mock(IDie.class);
	        
	        Player jack = new Player("Jack");
			Player jill = new Player("Jill");
			Player[] players = { jack, jill };
			IGame game = new Game(8, players);
	        
			
			context.checking(new Expectations() {{	
				atLeast(1).of (die).roll();
				will(onConsecutiveCalls(
			       returnValue(5),
			       returnValue(6),
			       returnValue(2)));
	        }});
			
			game.play(die);
			
			//DR Excellent!
	        context.assertIsSatisfied();
	        assert jack.wins();
			assert !jill.wins();
			assert jill.square().position()==7;
			assert game.isOver();
	       
	    }
	}