package snakes;

import java.util.LinkedList;
import java.util.Queue;

public class MockDie implements IDie {
	
	private Queue<Integer> values = new LinkedList<Integer>();

	@Override
	public int roll() {
		return values.remove();
	}
	public MockDie setValue(int value){
		this.values.add(value);
		return this;
	}

}
