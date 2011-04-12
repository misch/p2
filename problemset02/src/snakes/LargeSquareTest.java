package snakes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

//DR You tested all the functionality of the LargeSquares even with the String representation, well done!
@RunWith(JExample.class)
public class LargeSquareTest {
	
	private Player jack;
	private Player jill;

	@Test
	public IGame newGame() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		Player[] args = { jack, jill };
		IGame game = new Game(12, args, new Die());
		game.setSquareToLargeSquare(2);
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
		assertEquals("[|2|]", game.getSquare(2).toString());
		assertEquals("[1<Jack><Jill>][|2|][3][4][5][6][7][8][9][10][11][12]", game.toString());
		return game;
	}
	
	@Given("newGame")
	public IGame move1jackToLargeSquare(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(2, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}

	//DR Excellent again!
	@Given("move1jackToLargeSquare")
	public IGame move1strings(IGame game) {
		assertEquals("[1<Jill>]", game.firstSquare().toString());
		assertEquals("[|2|<Jack>]", game.getSquare(2).toString());
		return game;
	}

	@Given("move1jackToLargeSquare")
	public IGame move2jillToLargeSquare(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(2, jack.position());
		assertEquals(2, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	@Given("move2jillToLargeSquare")
	public IGame move3jackAwayFromLargeSquare(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(3, jack.position());
		assertEquals(2, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}
}
