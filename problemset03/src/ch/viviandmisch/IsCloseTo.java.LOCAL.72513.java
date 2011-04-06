package ch.viviandmisch;

import java.awt.geom.Point2D;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/*DR Accepted! But try do exactly read the task!
 * 
 * Everything was well done (responsibilities and matcher are ok)
 */
public class IsCloseTo extends TypeSafeMatcher<Point2D>{

	private Point2D point;

	public IsCloseTo(Point2D point){
		this.point = point;
	}
	
	@Override
	protected boolean matchesSafely(Point2D item) {
		return (item.distance(point)<0.01);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("is close to " + point.toString());
	}
	
	@Factory
	public static <T> Matcher<Point2D> closeTo(Point2D point) {
	    return new IsCloseTo(point);
	}
}
