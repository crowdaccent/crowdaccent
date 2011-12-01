/**
 * 
 */
package com.crowdaccent.orchestration.gateway;

import org.apache.log4j.Logger;

import com.amazonaws.mturk.addon.HITDataReader;

/**
 * @author kbhalla
 * 
 */
public class HITDataInputReader extends HITDataReader {
	/**
	 * @param fieldNames
	 * @param rows
	 */
	public HITDataInputReader(String[] fieldNames, String[] rows) {
		super.fieldNames = fieldNames;
		super.rows = rows;
	}

	protected static Logger log = Logger.getLogger(HITDataInputReader.class);

}
