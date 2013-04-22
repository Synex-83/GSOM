import java.awt.Color;
import java.awt.image.BufferedImage;




public class SelfOrganizingMap {
	
	public void methodTrain(DisplayLattice dl)
	{
		//DisplayLattice
		for(int i = 0; i < 10; i++)
		{
			//new DisplayLattice(exportImageNorm());
			dl.setImage(exportImageNorm());
		}
		
	}
	
	private BufferedImage exportImageNorm()
	{
		BufferedImage colorNodes = new BufferedImage(200, 200, 1);
		double[][] normL2values = new double[200][200];
		float t = 0.0f;
		for(int i = 0; i < normL2values.length ; i++){
			for(int j = 0; j < normL2values[0].length; j++){
				t = (float) Math.random();
				colorNodes.setRGB(i, j, (new Color(t,t,t)).getRGB());
			}
		}
		System.out.println("LL");
		return colorNodes;
	}

}







































