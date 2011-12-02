/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import com.crowdaccent.entity.WorkerStatistics;

/**
 * @author mkutare
 *
 */
public interface WorkerStatisticsDAO {
	/**
	 * @param product
	 */
	public void save(WorkerStatistics workerstats);
	/**
	 * @param product
	 */
	public void delete(WorkerStatistics workerstats);
	/**
	 * @param id
	 * @return
	 */
	public WorkerStatistics getById(String id);
	/**
	 * 
	 * @return
	 */
	public List<WorkerStatistics> getAll();
	/** Returns limited number of assignments.
	 * @param number number of assignments to return.
	 * @return List of Assignments.
	 */
	public List<WorkerStatistics> getNumWorkerStatistics(int number);
	/**
	 * @param assignmentId
	 * @return
	 */
	public WorkerStatistics getWorkerStatisticsByWorkerId(String workerId);

}
