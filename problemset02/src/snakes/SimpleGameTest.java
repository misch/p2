package snakes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;


import org.junit.runner.RunWith;

import com.google.inject.*;

@RunWith(JExample.class)
public class SimpleGameTest {
	
	private Player jack;
	private Player jill;

	@Test
	public IGame newGame() {
		//DR that is exactly what I would like to see in all the other Test classes!
		Injector injector=Guice.createInjector(new SnakesAndLaddersModule());
		IGame game = injector.getInstance(IGame.class);
		List<Player> players= game.getPlayers();
		jack=players.get(0);
		jill=players.get(1);
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}

	@Given("newGame")
	public IGame initialStrings(IGame game) {
		assertEquals("Jack", jack.toString());
		assertEquals("Jill", jill.toString());
		assertEquals("[1<Jack><Jill>]", game.firstSquare().toString());
		assertEquals("[2->6]", game.getSquare(2).toString());
		assertEquals("[5<-11]", game.getSquare(11).toString());
		assertEquals("[1<Jack><Jill>][2->6][3][4][5][6][7->9][8][9][10][5<-11][12]", game.toString());
		return game;
	}
	
	@Given("newGame")
	public IGame move1jack(IGame game) {
		game.movePlayer(4);
		assertTrue(game.notOver());
		assertEquals(5, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}

	@Given("move1jack")
	public IGame move1strings(IGame game) {
		assertEquals("[1<Jill>]", game.firstSquare().toString());
		assertEquals("[5<Jack>]", game.getSquare(5).toString());
		return game;
	}

	@Given("move1jack")
	public IGame move2jackBackwards(IGame game) {
		jack.moveForward(7+11); // move to end and back to start
		assertEquals(1, jack.position());
		assertEquals("[1<Jill><Jack>]", game.firstSquare().toString());
		return game;
	}

	@Given("move1jack")
	public IGame move2jillLadder(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(5, jack.position());
		assertEquals(6, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	@Given("move2jillLadder")
	public IGame move3jackMeetsJill(IGame game) {
		assertTrue(game.getSquare(5).isOccupied());
		game.movePlayer(1);
		assertTrue(!game.getSquare(5).isOccupied());
		assertTrue(game.notOver());
		assertEquals(1, jack.position());
		assertEquals(6, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}

	@Given("move3jackMeetsJill")
	public IGame move4jillSnake(IGame game) {
		game.movePlayer(5);
		assertTrue(game.notOver());
		assertEquals(1, jack.position());
		assertEquals(5, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}

	@Given("move4jillSnake")
	public IGame move5jackLadder(IGame game) {
		game.movePlayer(6);
		assertTrue(game.notOver());
		assertEquals(9, jack.position());
		assertEquals(5, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}
	
	@Given("move5jackLadder")
	public IGame move6jill(IGame game) {
		game.movePlayer(5);
		assertTrue(game.notOver());
		assertEquals(9, jack.position());
		assertEquals(10, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	@Given("move6jill")
	public IGame move7jackBouncesBackToJill(IGame game) {
		game.movePlayer(5);
		assertTrue(game.notOver());
		assertEquals(1, jack.position());
		assertEquals(10, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}

	@Given("move7jackBouncesBackToJill")
	public IGame move8jillWins(IGame game) {
		game.movePlayer(2);
		assertTrue(game.isOver());
		assertEquals(1, jack.position());
		assertEquals(12, jill.position());
		assertEquals(jack, game.currentPlayer());
		assertEquals(jill, game.winner());
		return game;
	}
}
