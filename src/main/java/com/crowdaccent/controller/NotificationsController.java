/**
 * 
 */
package com.crowdaccent.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.mturk.requester.EventType;
import com.crowdaccent.service.AssignmentService;
import com.crowdaccent.service.HitService;

/**
 * @author kbhalla
 * 
 */
@Controller
@RequestMapping("/notifications")
public class NotificationsController {
	/**
	 * 
	 */
	private static final String EVENT_TIME = ".EventTime";
	/**
	 * 
	 */
	private static final String ASSIGNMENT_ID = ".AssignmentId";
	/**
	 * 
	 */
	private static final String HIT_ID = ".HITId";
	/**
	 * 
	 */
	private static final String HIT_TYPE_ID = ".HITTypeId";
	/**
	 * 
	 */
	private static final String EVENT = "Event.";
	/**
	 * 
	 */
	private static final String EVENT_TYPE = ".EventType";
	private static final Logger _log = LoggerFactory
			.getLogger(NotificationsController.class);
	private @Autowired
	HitService hitService;
	private @Autowired
	AssignmentService assignmentService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "method", required = false) String method,
			@RequestParam(value = "Signature", required = false) String signature,
			@RequestParam(value = "Timestamp", required = false) String timestamp,
			@RequestParam(value = "Version", required = false) String version,
			HttpServletRequest request) {
		_log.info("Received notification from AWS as " + method + ":"
				+ signature + ":" + timestamp + ":" + version);
		/*
		 * &Event.1.EventType=AssignmentAccepted
		 * &Event.1.EventTime=2006-04-21T18:55:23Z
		 * &Event.1.HITTypeId=KDSFO4455LKDAF3 &Event.1.HITId=KDSFO4455LKDAF3
		 * &Event.1.AssignmentId=KDSFO4455LKDAF3KDSFO4455LKDAF3
		 */
		int counter = 1;
		while (true) {
			String eventType = request.getParameter(EVENT+ counter + EVENT_TYPE);
			if (eventType == null){
				break;
			}
			String eventTime = request.getParameter(EVENT+ counter + EVENT_TIME);
			String hitTypeId = request.getParameter(EVENT+ counter + HIT_TYPE_ID);
			String hitId = request.getParameter(EVENT+ counter + HIT_ID);
			String assignmentId = request.getParameter(EVENT+ counter + ASSIGNMENT_ID);
			_log.info("Event " + eventType + " eventTime " + eventTime + " HitTypeId " + hitTypeId + " HITId " 
			+ hitId + " assignmentId " + assignmentId);
			counter++;
			if (EventType.HITReviewable.equals(EventType.fromString(eventType))) {
				_log.info("Update HIT results for " + hitId);
				hitService.getAsyncResultsForHIT(hitId);
			} else if (EventType.AssignmentSubmitted.equals(EventType.fromString(eventType))){
				//TODO: No way to update single assignment using AMT. Darn!
				//assignmentService.updateAsyncAssignment(hitId, assignmentId);
			}
		}
		
		return "/";
	}
}
