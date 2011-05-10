package ursuppe;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

import ursuppe.Game.Colour;

@RunWith(JExample.class)

public class NullSquareTest {
	
	@Test
	public ISquare newNullSquare(){
		ISquare nullSquare = new NullSquare(2,3);
		return nullSquare;
	}
	
	@Given("newNullSquare")
	public void testCountFood(ISquare nullSquare){
		
		assertTrue(nullSquare.countFood(Colour.blue.toString())==0);
		assertTrue(nullSquare.countFood(Colour.green.toString())==0);
		assertTrue(nullSquare.countFood(Colour.red.toString())==0);
	}
	
	@Given("newNullSquare")
	public void testCountTotalFood(ISquare nullSquare){
		
		assertTrue(nullSquare.countTotalFood()==0);
	}
	
	@Given("newNullSquare")
	public void testCountAmoebas(ISquare nullSquare){
		
		assertTrue(nullSquare.countAmoebas()==0);
	}
	
	@Given("newNullSquare")
	public void testIsNullSquare(ISquare nullSquare){
		assert(nullSquare.isNullSquare());
	}
	
	@Given("newNullSquare")
	public void testToString(ISquare nullSquare){
		assertTrue(nullSquare.toString().equals(" |____________| "));
	}
	
	@Given("newNullSquare")
	public void testGetHorizontalPosition(ISquare nullSquare){
		assertTrue(nullSquare.getHorizontalPosition()==2);
	}
	
	@Given("newNullSquare")
	public void testGetVerticalPosition(ISquare nullSquare){
		assertTrue(nullSquare.getVerticalPosition()==3);
	}

}
