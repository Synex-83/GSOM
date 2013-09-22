/**
 * 
 */
package scbw.com;

import java.util.StringTokenizer;

/**
 * @author 		Manjusri Ishwara
 * @date		Sep 22, 2013 - 3:54:12 PM
 * @type        LinkedList
 *
 */
public class LinkedList {

		private MapNode firstMap;
		private PlayerNode firstPlayer;
		private boolean isMapList;
		
		public LinkedList(boolean isMap)
		{
			isMapList = isMap;
			
			if(isMap)
			{
				firstMap = null;
			}
			else
			{
				firstPlayer = null;
			}
		}
		
		public boolean isEmpty()
		{
			if(isMapList)
			{
				return firstMap == null;
			}
			else
			{
				return firstPlayer == null;
			}
		}
		
		public void insert(String name)
		{
			if(isMapList)
			{
				MapNode map = new MapNode(name);
				map.next = firstMap;
				firstMap = map;
			}
			else
			{
				String parts[]  = name.split(",");
				PlayerNode player = new PlayerNode(parts[1].trim());
				
				if(parts[2].trim().equals("Protoss"))
	    		{
	    			player.PROTOSS++;
	    		}
	    		else if(parts[2].trim().equals("Terran"))
	    		{
	    			player.TERRAN++;
	    		}
	    		else if(parts[2].trim().equals("Zerg"))
	    		{
	    			player.ZERG++;
	    		}
				
				player.next = firstPlayer;
				firstPlayer = player;
			}
		}
		
		public boolean exist(String name)
		{
			if(isMapList)
			{
				MapNode tempM = firstMap;
				//System.out.println("===============================================================" + name);
				while(tempM != null)
				{
					//System.out.println("****************************************" + tempM.MAP_NAME);
			    	if(tempM.MAP_NAME.equals(name))
			    	{
			    		tempM.NUMBER_OF_GAMES++;
			    		return true;
			    	}
			    	tempM = tempM.next;
				}					
			}
			else
			{
				String parts[]  = name.split(",");
				PlayerNode tempP = firstPlayer;
				while(tempP != null)
				{
			    	if(tempP.PLAYER_NAME.equals(parts[1].trim()))
			    	{
			    		tempP.NUMBER_OF_GAMES++;
			    		
			    		if(parts[2].trim().equals("Protoss"))
			    		{
			    			tempP.PROTOSS++;
			    		}
			    		else if(parts[2].trim().equals("Terran"))
			    		{
			    			tempP.TERRAN++;
			    		}
			    		else if(parts[2].trim().equals("Zerg"))
			    		{
			    			tempP.ZERG++;
			    		}
			    			
			    		return true;
			    	}
			    	tempP = tempP.next;
				}
			}
			
			return false;
		}
		
		public void print()
		{
			if(isMapList)
			{
				MapNode tempM = firstMap;
				System.out.print("List: ");
				while(tempM != null)
				{
					System.out.println(tempM.MAP_NAME + " " + tempM.NUMBER_OF_GAMES);
					tempM = tempM.next;
				}					
			}
			else
			{
				PlayerNode tempP = firstPlayer;
				System.out.print("List: ");
				while(tempP != null)
				{
					System.out.println("*********************************************");
				    System.out.println("Name : " + tempP.PLAYER_NAME + "\nNumber of Game : " + tempP.NUMBER_OF_GAMES + "\nProtoss : " + tempP.PROTOSS + "\nTerran : " +tempP.TERRAN + "\nZerg : " + tempP.ZERG);
					System.out.println("*********************************************");
					tempP = tempP.next;
				}
			}
		}
		
		
}
