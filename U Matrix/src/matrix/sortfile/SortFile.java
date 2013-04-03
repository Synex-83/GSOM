/**
 * 
 */
package matrix.sortfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Manjusri Wickramasinghe
 *
 */
public class SortFile {

	public SortFile()
	{
		
	}
	
	
	private String LINES_TO_WRITE = "";
	private int DIMENSION = 0;
	private int NUMBER_OF_LINES = 0;
	private double[][] WEIGHTS_ARRAY = null;
	private String[] SPLIT_ARRAY = null;
	private int I = 0;
	private int J = 0;
	private double MAXIMUM_I = -1.0;
	private double MAXIMUM_J = -1.0;
	private double MINIMUM_I = 100;
	private double MINIMUM_J = 100;
	private Object MAP_ARRAY[][]= null;
	private Object TEMP_MATRIX[][]=null;
	private double U_MATRIX[][]=null;
	
	/**
	 * @param fileName
	 * Name of the file to be read
	 * @param parent
	 * Parent directory of the file to be read
	 */
	public void readFromFile(String fileName, String parent)
	{

		BufferedReader lineNumber = null;	
		BufferedReader br = null;
		int counter = 0;
		try
		{
			lineNumber = new BufferedReader(new FileReader(parent + "\\" +fileName));
			br = new BufferedReader(new FileReader(parent + "\\" +fileName));
			String strLine = null;
			
		
			while (lineNumber.readLine() != null) NUMBER_OF_LINES++;
			lineNumber.close();
			
			if((strLine = br.readLine()) != null)
			{
				DIMENSION = Integer.parseInt(strLine);
				WEIGHTS_ARRAY = new double[NUMBER_OF_LINES - 2][DIMENSION+2];
				strLine = br.readLine();
			}
			
			while((strLine = br.readLine()) != null)
			{
				SPLIT_ARRAY =  strLine.split("\t");
				
				WEIGHTS_ARRAY[counter][0] = Double.parseDouble(SPLIT_ARRAY[2]);
				WEIGHTS_ARRAY[counter][1] = Double.parseDouble(SPLIT_ARRAY[1]);
				WEIGHTS_ARRAY[counter][2] = Double.parseDouble(SPLIT_ARRAY[3]);
				WEIGHTS_ARRAY[counter][3] = Double.parseDouble(SPLIT_ARRAY[4]);
				WEIGHTS_ARRAY[counter][4] = Double.parseDouble(SPLIT_ARRAY[5]);
				WEIGHTS_ARRAY[counter][5] = Double.parseDouble(SPLIT_ARRAY[6]);
				WEIGHTS_ARRAY[counter][6] = Double.parseDouble(SPLIT_ARRAY[7]);
				WEIGHTS_ARRAY[counter][7] = Double.parseDouble(SPLIT_ARRAY[8]);
				WEIGHTS_ARRAY[counter][8] = Double.parseDouble(SPLIT_ARRAY[9]);
				WEIGHTS_ARRAY[counter][9] = Double.parseDouble(SPLIT_ARRAY[10]);
				WEIGHTS_ARRAY[counter][10] = Double.parseDouble(SPLIT_ARRAY[11]);
				WEIGHTS_ARRAY[counter][11] = Double.parseDouble(SPLIT_ARRAY[12]);
				WEIGHTS_ARRAY[counter][12] = Double.parseDouble(SPLIT_ARRAY[13]);
				WEIGHTS_ARRAY[counter][13] = Double.parseDouble(SPLIT_ARRAY[14]);
				WEIGHTS_ARRAY[counter][14] = Double.parseDouble(SPLIT_ARRAY[15]);
				
				counter++;
			}
			
			br.close();
			bubbleSort();
			bubbleSort2();
			
			//printArray();
			maxMin();
		
			System.out.println("MAX I = " + MAXIMUM_I + "  MIN I = " + MINIMUM_I );
			System.out.println("MAX J = " + MAXIMUM_J + "  MIN J = " + MINIMUM_J );
			
			//getWeightArray(17, 7);
			
			redimMapArray();
			
			UMatrixFillMap();
			
			printUMatrix();
			
     		exportToCSV();
		}
		catch(IOException e)
		{
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(br != null)
					br.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	private void printArray()
	{
		for(int i = 0 ; i < WEIGHTS_ARRAY.length; i++){
			for(int j = 0; j < WEIGHTS_ARRAY[0].length; j++){
				System.out.print(WEIGHTS_ARRAY[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void printUMatrix()
	{
		for(int i = 0 ; i < U_MATRIX.length; i++){
			for(int j = 0; j < U_MATRIX[0].length; j++){
				System.out.print(U_MATRIX[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\output22.txt",false));
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
    private void bubbleSort() {
        int counter, index;
        double temp[] = null;
        
        for(counter=0; counter<WEIGHTS_ARRAY.length-1; counter++) 
        { 
            for(index=0; index<WEIGHTS_ARRAY.length-1-counter; index++) 
            { 
                if(WEIGHTS_ARRAY[index][0] > WEIGHTS_ARRAY[index+1][0]) 
                { 
                    temp = WEIGHTS_ARRAY[index]; 
                    WEIGHTS_ARRAY[index] = WEIGHTS_ARRAY[index+1];
                    WEIGHTS_ARRAY[index+1] = temp;
                }
            }
        }
    }
	
    private void bubbleSort2() {
        int counter, index;
        double temp[] = null;
        
        for(counter=0; counter<WEIGHTS_ARRAY.length-1; counter++) 
        { 
            for(index=0; index<WEIGHTS_ARRAY.length-1-counter; index++) 
            { 
                if((WEIGHTS_ARRAY[index][1] > WEIGHTS_ARRAY[index+1][1] ) && (WEIGHTS_ARRAY[index][0] == WEIGHTS_ARRAY[index+1][0] )) 
                { 
                    temp = WEIGHTS_ARRAY[index]; 
                    WEIGHTS_ARRAY[index] = WEIGHTS_ARRAY[index+1];
                    WEIGHTS_ARRAY[index+1] = temp;
                }
            }
        }
    }
    
    private void maxMin()
    {
    	for(int i = 0; i < WEIGHTS_ARRAY.length; i++)
    	{
    		if(WEIGHTS_ARRAY[i][0] > MAXIMUM_I)
    		{
    			MAXIMUM_I = WEIGHTS_ARRAY[i][0];
    		}
    		
    		if(WEIGHTS_ARRAY[i][1] > MAXIMUM_J)
    		{
    			MAXIMUM_J = WEIGHTS_ARRAY[i][1];
    		}
    		
    		if(WEIGHTS_ARRAY[i][0] < MINIMUM_I)
    		{
    			MINIMUM_I = WEIGHTS_ARRAY[i][0];
    		}
    		
    		if(WEIGHTS_ARRAY[i][1] < MINIMUM_J)
    		{
    			MINIMUM_J = WEIGHTS_ARRAY[i][1];
    		}
    	}
    }
    
    public void redimMapArray()
    {
    	maxMin();
    	double x = (MAXIMUM_I-MINIMUM_I);
    	double y = (MAXIMUM_J-MINIMUM_J);
    	double X = 0, Y = 0;

    	X = x + 1;
    	Y = y + 1;
    	
    	MAP_ARRAY = new Object[(int) X][(int) Y];
    	TEMP_MATRIX = new Object[(int) X][(int) Y];
    	U_MATRIX = new double[(int) (2*X-1)][(int) (2*Y-1)];
    	
    	for(int i=0; i < MAP_ARRAY.length; i++)
    	{
    		for(int j=0; j < MAP_ARRAY[0].length; j++)
    		{
    			MAP_ARRAY[i][j] = getWeightArray(i, j);
    		}
    	}
    	//testArray(0,0);
   // 	System.out.println("TEST");
    	
/*    	for(int i=0; i < MAP_ARRAY.length; i++)
    	{
    		for(int j=0; j < MAP_ARRAY[0].length; j++)
    		{ 			
    			if(MAP_ARRAY[i][j] == null)
    			{
    				System.out.print("null ");
    			}
    			else
    			{
    				System.out.print("AVAL ");
    			}
    		}
    		
    		System.out.println();
    	}*/
    	
    }
    
    private void testArray(int x, int y)
    {
    	double[] temp = null;
    	
    	double[] one = {1.0,1.0,1.0,1.0,1.0};
    	double[] two = {2.0,2.0,2.0,2.0,2.0};
    	double[] five= {5.0,5.0,5.0,5.0,5.0};
    	
    	MAP_ARRAY[0][0] = one;
    	MAP_ARRAY[0][1] = one;
    	MAP_ARRAY[0][2] = one;
    	MAP_ARRAY[0][3] = one;
    	MAP_ARRAY[0][4] = one;
    	
    	MAP_ARRAY[1][0] = one;
    	MAP_ARRAY[1][1] = two;
    	MAP_ARRAY[1][2] = two;
    	MAP_ARRAY[1][3] = two;
    	MAP_ARRAY[1][4] = one;
    	
    	MAP_ARRAY[2][0] = one;
    	MAP_ARRAY[2][1] = two;
    	MAP_ARRAY[2][2] = five;
    	MAP_ARRAY[2][3] = two;
    	MAP_ARRAY[2][4] = one;
    	
    	MAP_ARRAY[3][0] = one;
    	MAP_ARRAY[3][1] = two;
    	MAP_ARRAY[3][2] = two;
    	MAP_ARRAY[3][3] = two;
    	MAP_ARRAY[3][4] = one;
    	
    	MAP_ARRAY[4][0] = one;
    	MAP_ARRAY[4][1] = one;
    	MAP_ARRAY[4][2] = one;
    	MAP_ARRAY[4][3] = one;
    	MAP_ARRAY[4][4] = one;
    }
    
    private Object getWeightArray(int x, int y)
    {
    	double temp[] = new double[13];
    	double zero[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    	
		for(int i = 0 ; i < WEIGHTS_ARRAY.length; i++){
			if(WEIGHTS_ARRAY[i][0]==x)
			{
				for(int j = 0; j < WEIGHTS_ARRAY[0].length; j++){
					if(WEIGHTS_ARRAY[i][1]==y)
					{
						for(int k = 2; k < WEIGHTS_ARRAY[0].length; k++)
						{
							temp[k-2] = WEIGHTS_ARRAY[i][k];
						}
						return temp;
					}
				}
			}
		}
		return zero;
    }
    
    private void UMatrixFillMap()
    {   	
    	for(int i=0; i< MAP_ARRAY.length; i++)
    	{
    		for(int j=0; j < MAP_ARRAY[0].length; j++)
    		{ 			
    			//&& doesIndexExist( i, j, true) && doesIndexExist((i+1), j, true)
    			if(doesIndexExist((2*i+1), (2*j), false) )
    			{
    				if(doesIndexExist(i+1, j, true))
    				{
        				U_MATRIX[2*i+1][2*j] = getDistance((double [])MAP_ARRAY[i][j], (double [])MAP_ARRAY[i+1][j]);
    				}
    			}
    			if(doesIndexExist((2*i), (2*j + 1), false) )
    			{
       				if(doesIndexExist(i, j+1, true))
    				{
        				U_MATRIX[2*i][2*j+1] = getDistance((double [])MAP_ARRAY[i][j], (double [])MAP_ARRAY[i][j+1]);
    				}
    			}
    			if(doesIndexExist((2*i + 1), (2*j + 1), false) )
    			{
       				if(doesIndexExist(i+1, j+1, true) && doesIndexExist(i, j+1, true) && doesIndexExist(i+1, j, true))
    				{
        				U_MATRIX[2*i+1][2*j+1] = (getDistance((double [])MAP_ARRAY[i][j], (double [])MAP_ARRAY[i+1][j+1]) - getDistance((double [])MAP_ARRAY[i][j+1], (double [])MAP_ARRAY[i+1][j]))/2*Math.sqrt(2);
    				}
    			}
    			if(doesIndexExist((2*i), (2*j), false) )
    			{
    				int counter = 0;
    				double average =0.0;
    				
       				if(doesIndexExist(i+1, j, true))
    				{
        				average += U_MATRIX[i+1][j];
        				counter++;
    				}
       				
       				if(doesIndexExist(i-1, j, true))
    				{
       					average += U_MATRIX[i-1][j];
        				counter++;
    				}
       				
       				if(doesIndexExist(i, j+1, true))
    				{
       					average += U_MATRIX[i][j+1];
        				counter++;
    				}
       				
       				if(doesIndexExist(i, j-1, true))
    				{
       					average += U_MATRIX[i][j-1];
        				counter++;
    				}
       				
       				if(doesIndexExist(i-1, j+1, true))
    				{
       					average += U_MATRIX[i-1][j+1];
        				counter++;
    				}
       				
       				if(doesIndexExist(i-1, j-1, true))
    				{
       					average += U_MATRIX[i-1][j-1];
        				counter++;
    				}
       				
       				if(doesIndexExist(i+1, j+1, true))
    				{
       					average += U_MATRIX[i+1][j+1];
        				counter++;
    				}
       				
       				if(doesIndexExist(i+1, j-1, true))
    				{
       					average += U_MATRIX[i+1][j-1];
        				counter++;
    				}
       				
       				U_MATRIX[2*i][2*j] = average/counter;
    			}
    		}
    		System.out.println("");
       	}
    }
    
 
    private boolean doesIndexExist(int i, int j, boolean value)
    {
    	Object temp = null;
    	try
    	{
    		if(value)
    		{
        		temp = MAP_ARRAY[i][j];
    		}
    		else
    		{
    			temp = U_MATRIX[i][j];
    		}

    	}
    	catch(ArrayIndexOutOfBoundsException ex)
    	{
    		return false;
    	}
    	return true;
    }
    
    private double getDistance(double[] center, double[] neighbour)
    {
    	double temp = 0.0;
    	
    	for(int i = 0; i < center.length ; i++)
    	{
    		temp += Math.pow((center[i]-neighbour[i]),2);
    	}
    	
    	return Math.sqrt(temp);
    }
    
    private void exportToCSV()
    {
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("UMatrix52.csv",false));
			
			for(int i = 0 ; i < U_MATRIX.length; i++){
				for(int j = 0; j < U_MATRIX[0].length; j++){
					bw.write(U_MATRIX[i][j] + ",");
				}
				bw.newLine();
			}
			bw.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

}
