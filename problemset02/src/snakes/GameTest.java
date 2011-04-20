package snakes;
import static org.junit.Assert.*;

import java.util.List;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;



public class GameTest {

	private Player jack;
	private Player jill;

	    @Test
	    public void testPlay() {
	    	
	    	Injector injector=Guice.createInjector(new TestModule());
			IGame game = injector.getInstance(IGame.class);
			List<Player> players= game.getPlayers();
			jack=players.get(0);
			jill=players.get(1);
	        
			
			MockDie die= (MockDie)game.getDie();
			die
				.setValue(6)
				.setValue(5)
				.setValue(3);
			
			game.play();
			
			//DR Excellent
	        assertTrue( jack.wins());
			assertTrue(!jill.wins());
			assertTrue( jill.square().position()==6);
			assertTrue( game.isOver());
	       
	    }
	}
