package snakes;

import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDie.class).to(MockDie.class);
	    bind(IGame.class).toProvider(GameProvider.class);
	}

}
