package snakes;

//DR ok
/**
 * Provides a random integer between 1 and 6 to determine the number of {@link Square}s a Player should move forward.
 * @author vita
 *
 */

public class Die implements IDie {
	static final int FACES = 6;
	
	/* (non-Javadoc)
	 * @see snakes.IDie#roll()
	 */
	@Override
	public int roll() {
		int result = 1 + (int) (FACES * Math.random());
		assert result >= 1 && result <= FACES;
		return result;
	}
}
