/* Transform.java
 * 
 * Authors: Michèle Wyss, Viviane Tanner */
 
/** A Transform object is responsible to execute a transformation
 * or to reset the image to its starting position if the transformation
 * won't keep it in the container. */

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/*DR Fix problemset03 for next week!
 * I need your UML and your contracts (see Oscar's slieds) to finish my corrections*/
public class Transform
{
	private Rectangle2D size;
	private Rectangle2D containerSize;
	private AffineTransform affTrans = new AffineTransform();
	
	public Transform(Rectangle2D size, Rectangle2D containerSize)
	{
		this.size = size;
		this.containerSize = containerSize;
		assert invariant();
	}
	
	private boolean invariant()
	{
		return (this.size!=null && this.containerSize!=null);
	}
	
	public void addTransformation(String description)
	{		
		assert invariant();
		String kindOfTransformation = this.extractKindOfTransformation(description);
		Double[] arguments;

		if(!isValidDescription(description))
			System.out.println("Please enter a valid transformation.");
		else {
			arguments = this.extractArguments(kindOfTransformation, description);
			executeTransformation(kindOfTransformation, arguments);
		}
		
		if (!isInWindow()){
			reset();
		}
		assert invariant();
	}

	public AffineTransform getAffineTransform()
	{
		return affTrans;
	}
	
	private void executeTransformation(String kindOfTransformation, Double[] arguments)
	{
		AffineTransform newTrans = new AffineTransform();
		
		if (kindOfTransformation.equals("translate"))
			newTrans = affTrans.getTranslateInstance(arguments[0], arguments[1]);
			
		if (kindOfTransformation.equals("rotate"))
			newTrans = affTrans.getRotateInstance(arguments[0], size.getCenterX(), size.getCenterY());
	
		if (kindOfTransformation.equals("scale"))
			newTrans = affTrans.getScaleInstance(arguments[0], arguments[0]);
			
		affTrans.preConcatenate(newTrans);		
	}
	
	private void reset() {
		affTrans = new AffineTransform();
	}
	
	private boolean isValidDescription(String description) {
		boolean test;
		
		test = isValidTranslation(description);
		test = test || isValidRotation(description);
		test = test || isValidScale(description);
				
		return test;
	}

	private boolean isValidScale(String description) {
		String[] descriptionPartition = splitDescription(description);
		
		if (descriptionPartition.length != 2)
			return false;
		
		if (!descriptionPartition[0].equals("scale"))
			return false;
			
		try {
			Double.parseDouble(descriptionPartition[1]);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isValidRotation(String description) {
		String[] descriptionPartition = splitDescription(description);
		if (descriptionPartition.length != 3)
			return false;
		
		if (!descriptionPartition[0].equals("rotate"))
				return false;
		try {
			Double.parseDouble(descriptionPartition[1]);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isValidTranslation(String description)
	{
		String[] descriptionPartition = splitDescription(description);
		
		if (descriptionPartition.length != 3)
			return false;
		
		if (!descriptionPartition[0].equals("translate"))
			return false;
		
		try {
			Double.parseDouble(descriptionPartition[1]);
			Double.parseDouble(descriptionPartition[2]);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	private Double[] extractArguments(String kindOfTransformation, String description)
	{		
		String[] descriptionPartition = splitDescription(description);
		Double[] arguments = new Double[2];
		
		if(kindOfTransformation.equals("translate"))
			arguments = this.getTranslateArguments(descriptionPartition);
					
		if(kindOfTransformation.equals("rotate"))
			arguments[0] = this.getRotateArgument(descriptionPartition);
		
		if(kindOfTransformation.equals("scale"))
			arguments[0] = getScaleArgument(descriptionPartition);
		
		return arguments;
	}
	
	private String[] splitDescription(String description)
	{
		return description.split(" ");
	}
	
	private String extractKindOfTransformation(String description)
	{		
		String kindOfTransformation;
				
		kindOfTransformation = splitDescription(description)[0];
		
		return kindOfTransformation;
	}
		
	private boolean isInWindow()
	{
		Point2D[] boundsOfImage = getBoundsOfImage();
		Point2D target = new Point2D.Double(0,0);
		boolean test = true;
		
		for(int i = 0; i<boundsOfImage.length; i++)
		{
			affTrans.transform(boundsOfImage[i], target);
			if (!containerSize.contains(target))
				test = false;
		}
		return test;
	}
	
	private Point2D[] getBoundsOfImage() {
		Point2D[] boundsOfImage;
		
		Point2D upperRight = new Point2D.Double(size.getMaxX(), size.getMaxY());
		Point2D downRight = new Point2D.Double(size.getMaxX(), size.getMinY());
		Point2D upperLeft = new Point2D.Double(size.getMinX(), size.getMaxY());
		Point2D downLeft = new Point2D.Double(size.getMinX(), size.getMinY());
		
		boundsOfImage = new Point2D[] {upperRight, downRight, upperLeft, downLeft};
		
		return boundsOfImage;
	}

	private Double[] getTranslateArguments(String[] descriptionPartition)
	{
		Double[] translateArguments = new Double[2];
		
		translateArguments[0] = Double.parseDouble(descriptionPartition[1]);
		translateArguments[1] = Double.parseDouble(descriptionPartition[2]);
		
		return translateArguments;
	}
	
	private Double getRotateArgument(String[] descriptionPartition)
	{		
		Double rotateArgument = Double.parseDouble(descriptionPartition[1]);
		String rotateDirectory = descriptionPartition[2];
		
		if(rotateDirectory.equals("anticlockwise"))
			rotateArgument *= -1;
		
		return rotateArgument;
	}
	
	private Double getScaleArgument(String[] descriptionPartition)
	{
		Double scaleArgument = Double.parseDouble(descriptionPartition[1]);
		return scaleArgument;
	}	
}
