/**
 * 
 */
package com.crowdaccent.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Hit;

/**
 * @author kbhalla
 *
 */
@Service
public class ScheduledTasks {
	public static final Logger _log = LoggerFactory
			.getLogger(ScheduledTasks.class);

	private @Autowired HitService hitService;
	@Scheduled(cron = "00 */30 * * * ?")
	public void getAndUpdateActiveAndExpiredHITs() {
		_log.info("Fetching results for all HITs at " + new Date());
		List<Hit> hits = hitService.getUpdateableHITs();
		for (Hit hit: hits){
			_log.info("Start    results for HIT " + hit.getHit_id() + " with status " + hit.getHit_status());
			hitService.getAsyncResultsForHIT(hit);
			_log.info("Finished results for HIT " + hit.getHit_id() + " with status " + hit.getHit_status());
			
		}
	}

}
