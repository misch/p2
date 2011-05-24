package ursuppe;


import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

import ursuppe.Board.WindDirection;

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
		assertTrue(game.getSquare(3,2).countFood("G")==2);
		assertTrue(game.getSquare(3,2).countFood("B")==2);
		assertTrue(game.getSquare(3,2).countFood("R")==2);
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
		
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
				assertTrue(game.getSquare(i, j).countTotalFood()<=6);
		return game;
	}
	
	@Given("testPlaySecondPhase1")
	public Game testPlaySecondPhase2(Game game){
		game.playSecondPhase2(false);
		
		assertNotNull(game.getBoard().getWindDirection());
		return game;
	}
	
	@Given("testPlaySecondPhase2")
	public Game testPlaySecondPhase4(Game game){
		int hansAmoebasBefore = hans.countAmoebas();
		int nathanaelAmoebasBefore = nathanael.countAmoebas();
		int fritzAmoebasBefore = fritz.countAmoebas();
		game.playSecondPhase4();
		
		assertTrue(hans.countAmoebas() > hansAmoebasBefore);
		assertTrue(nathanael.countAmoebas() > nathanaelAmoebasBefore);
		assertTrue(fritz.countAmoebas() > fritzAmoebasBefore);
		return game;
	}
	
	@Given("testPlaySecondPhase4")
	public Game testPlaySecondPhase5(Game game){
		game.playSecondPhase5();
		ArrayList<Amoeba> hansAmoebas = hans.getAmoebas();
		ArrayList<Amoeba> nathanaelAmoebas = nathanael.getAmoebas();
		ArrayList<Amoeba> fritzAmoebas = fritz.getAmoebas();
		
		for (int i = 0; i< hansAmoebas.size(); i++)
			assertTrue(hansAmoebas.get(i).getDamagePoints()<2);
		
		for (int i = 0; i< nathanaelAmoebas.size(); i++)
			assertTrue(nathanaelAmoebas.get(i).getDamagePoints()<2);
		
		for (int i = 0; i< fritzAmoebas.size(); i++)
			assertTrue(fritzAmoebas.get(i).getDamagePoints()<2);
		
		return game;
	}
	
	@Given("newGame")
	public Game testPlaySecondPhase6(Game game){
		int hansScoreBefore = hans.getScore();
		int nathanaelScoreBefore = nathanael.getScore();
		int fritzScoreBefore = fritz.getScore();
		
		game.playSecondPhase6();
		
		assertTrue(hans.getScore()<=hansScoreBefore);
		assertTrue(nathanael.getScore()<=nathanaelScoreBefore);
		assertTrue(fritz.getScore()<=fritzScoreBefore);
		return game;
	}
	
	@Given("newGame")
	public Game testPlay(Game game){
		
		Output.setOutput(new NullOutput());
		game.play();
		
		assertNotNull(game.getWinner());
		return game;
	}
	
	@Given("newGame")
	public Game testGetSquareInDirection(Game game){
		WindDirection east = WindDirection.east;
		assertTrue(game.getSquareInDirection(game.getSquare(3,2), east).equals(game.getSquare(3,3)));
		return game;
	}
	
}
