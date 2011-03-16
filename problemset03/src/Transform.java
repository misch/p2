/* Transform.java
 * 
 * Authors: Michèle Wyss, Viviane Tanner
 * 
 * Responsibilities of class Transform:
 * The class is responsible to execute a transformation
 * if it won't bring the image out of the window.
 */

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Transform
{
	String kindOfTransformation;
	double translateArgument1;
	double translateArgument2;
	double rotateArgument;
	String rotateDirect;
	double scaleArgument;
	String[] descriptionPartition;
	private Rectangle2D size;
	private Rectangle2D containerSize;
	
	AffineTransform firstTrans = new AffineTransform();
	AffineTransform scndTrans = new AffineTransform();
	
	
	public Transform(Rectangle2D size, Rectangle2D containerSize)
	{
		this.size = size;
		this.containerSize = containerSize;
		assert invariant();
	}
	
	private boolean invariant()
	{
		return isInWindow();
	}
	
	public void addTransformation(String description)
	{		
		assert invariant();
		this.prepareTransformation(description);
			
		if (kindOfTransformation.equals("translate"))
			firstTrans.translate(translateArgument1, translateArgument2);
				
		if (kindOfTransformation.equals("rotate"))
			firstTrans.rotate(rotateArgument, size.getCenterX(), size.getCenterY());
			
		if (kindOfTransformation.equals("scale"))
			firstTrans.scale(scaleArgument, scaleArgument);
			
		scndTrans.concatenate(firstTrans);
		
		if (!isInWindow())
		{
			throw new RuntimeException();
		}
		assert invariant();
	}

	public AffineTransform getAffineTransform()
	{
		return scndTrans;
	}

	//sets up the values of the requested transformation
	private void prepareTransformation(String description)
	{		
		descriptionPartition = description.split(" ");
				
		kindOfTransformation = descriptionPartition[0];
		
		if(kindOfTransformation.equals("translate"))
			this.initializeTranslateArguments(descriptionPartition);
		
		if(kindOfTransformation.equals("rotate"))
			this.initializeRotateArguments(descriptionPartition);
		
		if(kindOfTransformation.equals("scale"))
			this.initializeScaleArgument(descriptionPartition);
	}
	
	private boolean isInWindow()
	{
		Point2D upperRight = new Point2D.Double(size.getMaxX(), size.getMaxY());
		Point2D downRight = new Point2D.Double(size.getMaxX(), size.getMinY());
		Point2D upperLeft = new Point2D.Double(size.getMinX(), size.getMaxY());
		Point2D downLeft = new Point2D.Double(size.getMinX(), size.getMinY());
		Point2D target = new Point2D.Double(0, 0);
		
		scndTrans.transform(upperRight, target);
		boolean test = containerSize.contains(target);
		scndTrans.transform(downRight, target);
		test = test && containerSize.contains(target);
		scndTrans.transform(upperLeft, target);
		test = test && containerSize.contains(target);
		scndTrans.transform(downLeft, target);
		test = test && containerSize.contains(target);
		
		return test;
	}
	
	// helper-methods
	
	private void initializeTranslateArguments(String[] s)
	{
		translateArgument1 = Double.parseDouble(descriptionPartition[1]);
		translateArgument2 = Double.parseDouble(descriptionPartition[2]);
	}
	
	private void initializeRotateArguments(String[] s)
	{
		rotateArgument = Double.parseDouble(descriptionPartition[1]);
		rotateDirect = descriptionPartition[2];
		if(rotateDirect.equals("anticlockwise"))
			rotateArgument = (-1)*rotateArgument;
	}
	
	private void initializeScaleArgument(String[] descriptionPartition2)
	{
		scaleArgument = Double.parseDouble(descriptionPartition[1]);
	}	
}
