/**
 * 
 */
package gsom.core;

/**
 * @File 	DataRecord.java
 * @author 	Manjusri Ishwara
 * @date   	Aug 21, 2012 - 7:44:07 PM
 * @package	gsom.core
 */
public class DataRecord {

	private String dataElementName; //class name or the data name of the element
	private double dataValues[]; //
	
	/**
	 * @param element
	 * Element name or the class name of the data record
	 * @param data
	 * Input vector in boolean format or normalized format
	 */
	public DataRecord(String element, double data[])
	{
		dataElementName = element;
		dataValues = data;
	}
	
	/**
	 * @return
	 * The data name or the class name associated with the data object
	 */
	public String getElement()
	{
		return dataElementName;
	}
	
	/**
	 * @return
	 * Returns the data record associated with the object in the form of a double array.
	 */
	public double[] getDataValues()
	{
		return dataValues;				
	}
	
}
