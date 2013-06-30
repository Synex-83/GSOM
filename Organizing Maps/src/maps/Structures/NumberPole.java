/**
 * 
 */
package maps.Structures;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * @author 		User
 * @date		Jun 29, 2013 - 10:51:55 PM
 * @type        NumberPole
 *
 */
public class NumberPole {

	private SimpleNode LIST;
	private SimpleNode ZERO;
	private SimpleNode TAIL_UP;
	private SimpleNode TAIL_DOWN;
	private SimpleNode[] ARRAY = new SimpleNode[101]; //101 elements including 100.
	private int OFFSET = 50;
	
	public NumberPole()
	{
		LIST = null;
		ZERO = null;
		
		for(int i = 0; i < ARRAY.length ; i ++)
		{
			ARRAY[i] =new SimpleNode(i-OFFSET);
		}
	}
	
	public void add(GSOMNode node)
	{
		int y = node.getY();
		
		ARRAY[y+OFFSET].xObjectList.add(node);

	}

	public LinkedList<GSOMNode> getList(int y)
	{
		return ARRAY[y+OFFSET].xObjectList;
	}
	
	public boolean nodeExists(GSOMNode node)
	{
		int y = node.getY();
		LinkedList<GSOMNode> temp = ARRAY[y+OFFSET].xObjectList;
		
		ListIterator<GSOMNode> iterator = temp.listIterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getX() == node.getX())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public GSOMNode[] getNeighborNodes(int x, int y) //standard agreement with code in the order of L[0], R[1], U[2], D[3] in the return array.
	{
		
		GSOMNode[] returnNodes = new GSOMNode[4];
		GSOMNode temp = null;
		
		ListIterator<GSOMNode> xIterator = ARRAY[y + OFFSET].xObjectList.listIterator();
 		ListIterator<GSOMNode> upIterator = ARRAY[y + OFFSET + 1].xObjectList.listIterator();
 		ListIterator<GSOMNode> downIterator = ARRAY[y + OFFSET - 1].xObjectList.listIterator();
		
		while(xIterator.hasNext())
		{
			temp = xIterator.next();
			if(temp.getX() == (x - 1))
			{
				returnNodes[0] = temp;
			}
			
			if(temp.getX()== (x + 1))
			{
				returnNodes[1] = temp;
			}
		}
		
		
		while(upIterator.hasNext())
		{
			temp = upIterator.next();
			if(temp.getX() == x)
			{
				returnNodes[2] = temp;
			}

		}
		
		while(downIterator.hasNext())
		{
			temp = downIterator.next();
			if(temp.getX() == x)
			{
				returnNodes[3] = temp;
			}

		}
		return returnNodes;
	}
}
