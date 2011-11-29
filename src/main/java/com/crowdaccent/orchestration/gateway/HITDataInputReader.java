/**
 * 
 */
package com.crowdaccent.orchestration.gateway;

import java.util.HashMap;
import java.util.Map;

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
		this.fieldNames = fieldNames;
		this.rows = rows;
	}

	private String[] fieldNames;
	private String[] rows;
	public final static String DEFAULT_DELIM = "&&";

	protected static Logger log = Logger.getLogger(HITDataInputReader.class);

	public Map<String, String> getRowAsMap(int rowNum) {
		String[] fieldNames = this.getFieldNames();
		String[] rowValues = this.getRowValues(rowNum);

		if (fieldNames == null || fieldNames.length == 0) {
			log.info("No field names were found in your HIT Input. Your first row in your input file must contain field names for each column.");
			return null;
		}

		if (rowValues == null || rowValues.length == 0) {
			log.info("No input rows were found in your HIT Input. Your input file must contain at least one row of input.");
			return null;
		}

		HashMap<String, String> rowValueMap = new HashMap<String, String>();

		for (int i = 0; i < fieldNames.length; i++) {
			rowValueMap.put(fieldNames[i], rowValues[i]);
		}

		return rowValueMap;

	}

	public String[] getRowValues(int rowNum) {
		String row = this.getRow(rowNum);
		if (row != null)
			return row.split(DEFAULT_DELIM);

		return null;
	}

	public int getNumRows() {
		if (this.rows == null) {
			return 0;
		}
		return rows.length;
	}

	public String[] getFieldNames() {
		if (this.fieldNames == null) {
			this.fieldNames = this.getRowValues(0);
		}
		return fieldNames;
	}

	/**
	 * @return the rows
	 */
	public String[] getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(String[] rows) {
		this.rows = rows;
		super.setRows(rows);
	}

	/**
	 * @param fieldNames
	 *            the fieldNames to set
	 */
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String getRow(int rowNum) {
		if (this.rows != null && this.rows.length >= rowNum) {
			return this.rows[rowNum];
		}

		return null;
	}
}
