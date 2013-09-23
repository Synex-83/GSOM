/**
 * 
 */
package scbw.com;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 		Manjusri Ishwara
 * @date		Sep 21, 2013 - 1:43:59 PM
 * @type        Test
 *
 */
public class Test {

	static scbw.com.LinkedList maps = new scbw.com.LinkedList(true);
	static scbw.com.LinkedList players = new scbw.com.LinkedList(false);
	
	/**
	 * @param files
	 * @param Option
	 */
	public static void showFiles(File[] files,int Option) {
		String filename = "";
		
		//countPlayers("TL742.rep.rgd", "E:\\PhD\\New Data Set\\TLGGICCUP_gosu_data\\TLGGICCUP_gosu_data\\TvZ-ODD");
		
	    for (File file : files) {
	        if (file.isDirectory()) {
	        	if(Option == 0)
	        	{
		            System.out.println("========================Directory: " + file.getName());
	        	}
	            showFiles(file.listFiles(),Option); 
	        } else {
	        	filename = file.getName();
	        	if (Option ==0) {
		            if(filename.contains(".rgd"))
		            {
		            	System.out.println("**********************File: " + file.getName());
		            	countPlayers(filename, file.getParent());		              	
		            }
				}
	        }
	    }
	    //System.out.println("================");
	    //maps.print();
	    players.print();
	}

	/**
	 * @param filename
	 * @param parent
	 */
	public static void countPlayers(String filename, String parent)
	{
		BufferedReader br  = null;
		int counter = 0;
		
		try
		{	    
			FileInputStream fstream = new FileInputStream(parent +"\\"+filename);
			DataInputStream in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			StringTokenizer tokens;
			String strLine;
			
			while ((strLine = br.readLine()) != null)
			{
				tokens = new StringTokenizer(strLine,":");
				counter++;
				if(counter == 3)
				{
				//	System.out.println(strLine);
					while(tokens.hasMoreTokens())
					{
						tokens.nextToken();
						addNodeToQueue(tokens.nextToken().toString().trim(), true);
					}
					
				}
				else if(counter==4)
				{
				//	System.out.println(strLine);
				}
				else if(counter == 6 || counter == 7) //6 //7
				{
					while(tokens.hasMoreTokens())
					{
						//tokens.nextToken();
						addNodeToQueue(strLine.toString().trim(), false);
						tokens.nextToken();
					}
				}
					
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void addNodeToQueue(String value, boolean isMap)
	{
		if(isMap)
		{
			if(!maps.exist(value))
			{
				maps.insert(value);
			}
		}
		else
		{
			if(!players.exist(value))
			{
				players.insert(value);
			}
		}
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
	    File[] files = new File("E:\\PhD\\New Data Set\\TLGGICCUP_gosu_data\\TLGGICCUP_gosu_data\\TvZ").listFiles();
	    showFiles(files,0);
	}

}
