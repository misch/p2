package snakes;

import com.google.inject.*;

public class SnakesAndLaddersModule extends AbstractModule{
	
	protected void configure() {
	    bind(IDie.class).to(Die.class);
	    
	  }
	

}
