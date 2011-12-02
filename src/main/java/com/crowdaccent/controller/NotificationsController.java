/**
 * 
 */
package com.crowdaccent.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crowdaccent.service.HitService;

/**
 * @author kbhalla
 * 
 */
@Controller
@RequestMapping("/notifications")
public class NotificationsController {
	private static final Logger _log = LoggerFactory
			.getLogger(NotificationsController.class);
	private HitService hitService;

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
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if (key.matches("Event.*HITId")) {
				String value = request.getParameter(key);
				_log.info("Getting Results for " + value);
				hitService.getAsyncResultsForHIT(value);
				_log.info("Finished Results for " + value);
			}
		}
		return "/";
	}

	/**
	 * @param hitService the hitService to set
	 */
	@Autowired
	public void setHitService(HitService hitService) {
		this.hitService = hitService;
	}

}
