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

@RunWith(JExample.class)
public class TrapDoorTest {
	
	private Player jack;
	private Player jill;

	@Test
	public IGame newGame() {
		Injector injector=Guice.createInjector(new SnakesAndLaddersModule());
		IGame game = injector.getInstance(IGame.class);
		List<Player> players= game.getPlayers();
		jack=players.get(0);
		jill=players.get(1);
		game.setSquareToLargeSquare(4);//had to change the sequence, (no TrapDoor without LargeSquare)
		game.setSquareToTrapDoor(2, 2);
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	@Given("newGame")
	public IGame jackToTrapDoor(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(2, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}
	
	@Given("jackToTrapDoor")
	public IGame jillToTrapDoor(IGame game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(4, jack.position());
		assertEquals(4, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	//DR AWESOME I like new tests and they even check functionality of the TrapDoor.
	@Given("jillToTrapDoor")
	public IGame checkStrings(IGame game){
		assertEquals("[2==>4]", game.getSquare(2).toString());
		assertEquals("[1][2==>4][3][|4|<Jack><Jill>][5][6][7->9][8][9][10][5<-11][12]", game.toString());
		return game;
	}
}
