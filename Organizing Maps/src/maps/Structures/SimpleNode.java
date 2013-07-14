/**
 * 
 */
package maps.Structures;

import java.util.LinkedList;


/**
 * @author 		User
 * @date		Jun 29, 2013 - 11:00:26 PM
 * @type        SimpleNode
 *
 */
public class SimpleNode {
	int yValue;
	SimpleNode Up;
	SimpleNode Down;
	LinkedList<GSOMNode> xObjectList;
	
	public SimpleNode(int y)
	{
		yValue = y;
		xObjectList = new LinkedList<GSOMNode>();
	}
		
	public SimpleNode(int y, SimpleNode up, SimpleNode down) 
	{
		yValue = y;
		Up = up;
		Down = down;
		xObjectList = new LinkedList<GSOMNode>();
	}
	
	public LinkedList<GSOMNode> getObjectList()
	{
		return xObjectList;
	}
	
	public void addNode(GSOMNode node)
	{

		xObjectList.add(node);
		//
	}
	
}
