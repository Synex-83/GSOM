/**
 * 
 */
package maps.Structures;


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
	
	public NumberPole()
	{
		LIST = null;
		ZERO = null;
		
		for(int i = 0; i < ARRAY.length ; i ++)
		{
			ARRAY[i] =new SimpleNode(i-50);
		}
	}
	
	public void add(GSOMNode node)
	{
		int y = node.getY();
		
		ARRAY[y+50].xObjectList.add(node);

	}


}
