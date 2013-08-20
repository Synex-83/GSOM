import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * erwerw
 */

/**
 * @author Manjusri 
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Create a real matrix with two rows and three columns
/*		double[][] matrixData = { {1d,2d,3d}, {2d,5d,3d}};
		RealMatrix m = new Array2DRowRealMatrix(matrixData);

		// One more with three rows, two columns
		double[][] matrixData2 = { {1d,2d}, {2d,5d}, {1d, 7d}};
		RealMatrix n = new Array2DRowRealMatrix(matrixData2);

		// Note: The constructor copies  the input double[][] array.

		// Now multiply m by n
		RealMatrix p = m.multiply(n);
		System.out.println(p.toString());    // 2
		System.out.println(p.getColumnDimension()); // 2
*/
		// Invert p, using LU decomposition
	//	RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();
/*		System.out.println(pInverse.toString());
		System.out.println("D8D");*/
		
		double temp[] = {5.0,6.0,2.0};
		double temp1[] = {5.0,6.0,2.0};
		ArrayRealVector arv = new ArrayRealVector(temp);
		
		System.out.print(arv.toString());
		
		ArrayRealVector lol = (ArrayRealVector) arv.mapMultiplyToSelf(5);
		
		System.out.print((arv.add(lol).toString()));
		
	}

}
