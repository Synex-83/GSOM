/**
 * 
 */
package validation.maze;

/**
 * @author   Manjusri Ishwara
 * @date     Sep 15, 2012 11:53:34 PM
 * @file     Decision.java
 * @package  validation.maze
 */
public class Decision {
	
	private String lineNumber = "";
	private int junctionType = 0;
	private int junctionNumber = 0;
	private int decision = 0;
	
	/**
	 * @param line_Number
	 * @param junction_Type
	 * @param junction_Number
	 * @param decision_Made
	 * 
	 * Takes the current line in the maze, junction type, unique junction number and the decision made in the junction
	 * and initializes a junction object.
	 */
	public Decision(String line_Number, int junction_Type, int junction_Number, String decision_Made)
	{
		lineNumber = line_Number;
		
		/*
		 * If a line number contains I it represents the inner square of the maze which is treated separately. To distinctly
		 * identify these lines 90 is added to the original read value from the game data. This 90 has to be subtracted when
		 * processing data corresponding to inner square of the maze.
		 */
		if(lineNumber.contains("I"))
		{
			junctionNumber = junction_Number + 90;
		}
		else
		{
			junctionNumber = junction_Number;
		}
		
		junctionType = junction_Type;
		

		if(decision_Made.equals("Right"))
		{
			decision = 1;
		}
		else if(decision_Made.equals("Left"))
		{
			decision = 2;
		}
		else if(decision_Made.equals("Up"))
		{
			decision = 3;
		}
		else if(decision_Made.equals("Down"))
		{
			decision = 4;
		}
	}
	
	/**
	 * @return 
	 * Returns the junction type associated with the current decision.
	 */
	public int getJunctionType()
	{
		return junctionType;
	}

	/**
	 * @return 
	 * Returns the junction number associated with the current decision.
	 */
	public int getJunctionNumber()
	{
		return junctionNumber;
	}
	
	/**
	 * @return 
	 * Returns the decision associated with the current decision.
	 */
	public int getDecision()
	{
		return decision;
	}
	
	/**
	 * @return 
	 * Returns the horizontal line number in the maze associated with the current position of the player.
	 */
	public String getLineNumber()
	{
		return lineNumber;
	}
}
