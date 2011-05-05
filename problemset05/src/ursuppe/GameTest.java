package ursuppe;


import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;


import org.junit.runner.RunWith;

@RunWith(JExample.class)

public class GameTest {
	
	private Player hans;
	private Player fritz;
	private Player nathanael;

	@Test
	public Game newGame() {
		
		Game game = new Game();
		
		Queue<Player> players = game.getPlayers();
		hans=players.remove();
		fritz=players.remove();
		nathanael=players.remove();
		
		assertTrue(players.isEmpty());
		
		assertNotNull(game.getSquare(3, 2));
		
		return game;	
	}
	@Given("newGame")
	public Game testNullSquares(Game game){
		assertTrue (game.getSquare(0,0).isNullSquare());
		assertTrue(game.getSquare(4,0).isNullSquare());
		assertTrue(game.getSquare(4,1).isNullSquare());
		assertTrue(game.getSquare(4,2).isNullSquare());
		assertTrue(game.getSquare(4,4).isNullSquare());
		return game;
	}
	@Given("newGame")
	public Game testCompass(Game game){
		assertTrue(game.getSquare(2,2).isNullSquare());
		return game;
	}
	
	@Given("newGame")
	public Game testFoodstuff(Game game){
		assertTrue(game.getSquare(3,2).countFood("green")==2);
		assertTrue(game.getSquare(3,2).countFood("blue")==2);
		assertTrue(game.getSquare(3,2).countFood("red")==2);
		assertTrue(game.getSquare(3,2).countTotalFood()==6);
		return game;
	}
	
	@Given("newGame")
	public Game testPlayFirstPhase(Game game){
		game.playFirstPhase();
		assertTrue(hans.countAmoebas()==2);
		assertTrue(fritz.countAmoebas()==2);
		assertTrue(nathanael.countAmoebas()==2);
		
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
				assertTrue(game.getBoard().getSquare(i, j).countAmoebas()<=1);
		
		return game;
		
	}
	
	@Given("testPlayFirstPhase")
	public Game testPlaySecondPhase1(Game game){
		game.playSecondPhase1();
		
		assertTrue(hans.getAmoebas().get(1).getSquare().countAmoebas()>=1);
		assertTrue(fritz.getAmoebas().get(1).getSquare().countAmoebas()>=1);
		assertTrue(nathanael.getAmoebas().get(1).getSquare().countAmoebas()>=1);
		
		/*assertTrue(hans.getAmoebas().get(0).getSquare().countFood(hans.getColour().toString())>2);
		assertTrue(fritz.getAmoebas().get(1).getSquare().countFood(fritz.getColour().toString())>2);
		assertTrue(nathanael.getAmoebas().get(1).getSquare().countFood(nathanael.getColour().toString())>2);*/
		
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
				assertTrue(game.getSquare(i, j).countTotalFood()<=6);
		return game;
	}
	
	@Given("testPlaySecondPhase1")
	public Game testPlaySecondPhase2(Game game){
		game.playSecondPhase2();
		
		assertNotNull(game.getBoard().getWindDirection());
		return game;
	}
	
	@Given("testPlaySecondPhase2")
	public Game testPlaySecondPhase4(Game game){
		game.playSecondPhase4();
		
		return game;
	}
}
