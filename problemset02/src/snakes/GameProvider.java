package snakes;

import com.google.inject.*;

public class GameProvider implements Provider<IGame>{
	
	private final IDie die;

	  @Inject
	  public GameProvider(IDie die) {
	    this.die = die;
	  }

	  public IGame get() {
		  Player jack = new Player("Jack");
		  Player jill = new Player("Jill");
		  Player[] args = { jack, jill };
	    IGame game = new Game(12, args, die);
	    game.setSquareToLadder(2, 4);
		game.setSquareToLadder(7, 2);
		game.setSquareToSnake(11, -6);
	    return game;
	  }

}
