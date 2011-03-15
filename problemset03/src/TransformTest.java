import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransformTest {
	
	Transform transform;
	
	Point2D  target;
	Point2D  origin = new Point2D.Double(0,0);
	
	@Before
	public void setUp() {
		Rectangle2D imageContainerSize = new Rectangle2D.Double(0,0,1000, 1000);
		Rectangle2D imageSize = new Rectangle2D.Double(0,0,600, 600);
		transform = //Modify here
		target = new Point2D.Double();
	}
	
	@After
	public void tearDown() {
		target = null;
		transform = null;
	}
	
    @Test(expected=AssertionError.class)
	public void assertionsOn() {
		assert false;
	}
	
	@Test
	public void testTranslate() {
		transform.addTransformation("translate 2 1"); 
		transform.getAffineTransform().transform(new Point2D.Double(2,3),target);
		assertTrue(target.distance(new Point2D.Double(4,4)) < 0.001);
	}
	
	@Test
	public void testTranslateReals() {
		transform.addTransformation("translate 2.0 1.0");
		transform.getAffineTransform().transform(new Point2D.Double(2,3),target);
		assertTrue(target.distance(new Point2D.Double(4,4)) < 0.001);
	}
	
	@Test
	public void testRotateRadiansClockwise() {
			transform.addTransformation("rotate 1.57079633 clockwise");
			transform.getAffineTransform().transform(new Point2D.Double(1,1), target);
			assertTrue(target.distance(new Point2D.Double(1,599)) < 0.01);
	}
	

	@Test
	public void testRotateRadiansAnticlockwise()  {
			transform.addTransformation("rotate 1.57079633 anticlockwise");
			transform.getAffineTransform().transform(new Point2D.Double(1,-1), target);
			assertTrue(target.distance(new Point2D.Double(601,1)) < 0.01);
		}
	
	@Test
	public void testScale() {
		transform.addTransformation("scale 2");
		transform.getAffineTransform().transform(new Point2D.Double(2,3),target);
		assertTrue(target.distance(new Point2D.Double(4,6)) < 0.001);
	}
	
	@Test
	public void testTranslateThenScale() {
		transform.addTransformation("translate 2 1"); 
		transform.addTransformation("scale 2");
		transform.getAffineTransform().transform(new Point2D.Double(2,3),target);
		assertTrue(target.distance(new Point2D.Double(8,8)) < 0.001);
	}
}
