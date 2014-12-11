/**
 * 
 */
package maps.Structures;

/**
 * @author Manjusri
 *
 */
public class HitHolder 
{
	public String element = "";
	public int firstIteration = -1;
	public int lastIteration = -1;
	public int numberOfHits = 0;
	
	
	public HitHolder(String ele, int iter)
	{
		element = ele;
		firstIteration = iter;
		lastIteration = iter;
		numberOfHits = 1;
	}
	
	public void incrementHits()
	{
		numberOfHits++;
	}
	
	public String getElement()
	{
		return element;
	}
	
	public int getFirstIteration()
	{
		return firstIteration;
	}
	
	public int getLastIteration()
	{
		return lastIteration;
	}
	
	public int getHits()
	{
		return numberOfHits;
	}

}
