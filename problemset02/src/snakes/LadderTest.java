package snakes;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(JMock.class)

public class LadderTest {
	Mockery context = new JUnit4Mockery();

    @Test
    public void testIsValidTransport() {
    	
        final IGame game = context.mock(IGame.class);

        
        context.checking(new Expectations() {{
			allowing (game).isValidPosition(with(any(Integer.class))); will(returnValue(true));
        }});
        
        Ladder ladder1 = new Ladder(3, game, 3);
        Ladder ladder2;
        try{
        	ladder2=new Ladder(0, game, 5);
        }catch(Throwable assertionError){
        	ladder2=null;
        }

		
        context.assertIsSatisfied();
        assertNotNull(ladder1);
        assertEquals(ladder1.position(),3);
        assertNull(ladder2);
        
       
    }
}

