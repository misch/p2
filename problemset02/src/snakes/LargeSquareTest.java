package snakes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

import com.google.inject.Guice;
import com.google.inject.Injector;

//DR You tested all the functionality of the LargeSquares even with the String representation, well done!
@RunWith(JExample.class)
public class LargeSquareTest {
	
	private Player jack;
	private Player jill;

	@Test
	public IGame newGame() {
		Injector injector=Guice.createInjector(new SnakesAndLaddersModule());
		IGame game = injector.getInstance(IGame.class);
		List<Player> players= game.getPlayers();
		jack=players.get(0);
		jill=players.get(1);
		game.setSquareToLargeSquare(3);
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}

	//DR Excellent testing the String representation!
	@Given("newGame")
	public IGame initialStrings(IGame game) {
		assertEquals("Jack", jack.toString());
		assertEquals("Jill", jill.toString());
		assertEquals("[1<Jack><Jill>]", game.firstSquare().toString());
		assertEquals("[|3|]", game.getSquare(3).toString());
		assertEquals("[1<Jack><Jill>][2->6][|3|][4][5][6][7->9][8][9][10][5<-11][12]", game.toString());
		return game;
	}
	
	@Given("newGame")
	public IGame move1jackToLargeSquare(IGame game) {
		game.movePlayer(2);
		assertTrue(game.notOver());
		assertEquals(3, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}

	//DR Excellent again!
	@Given("move1jackToLargeSquare")
	public IGame move1strings(IGame game) {
		assertEquals("[1<Jill>]", game.firstSquare().toString());
		assertEquals("[|3|<Jack>]", game.getSquare(3).toString());
		return game;
	}

	@Given("move1jackToLargeSquare")
	public IGame move2jillToLargeSquare(IGame game) {
		game.movePlayer(2);
		assertTrue(game.notOver());
		assertEquals(3, jack.position());
		assertEquals(3, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	@Given("move2jillToLargeSquare")
	public IGame move3jackAwayFromLargeSquare(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(4, jack.position());
		assertEquals(3, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}
}
