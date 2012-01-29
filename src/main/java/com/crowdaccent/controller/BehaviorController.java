package com.crowdaccent.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.crowdaccent.entity.Behavior;
import com.crowdaccent.service.BehaviorService;

/**
 * @author mkutare
 *
 */
@Controller
@RequestMapping ("/behavior")
public class BehaviorController {
	private @Autowired BehaviorService behaviorService;

	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public String showBehaviorPage() {
	  return "behavior/show";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/results")
	public String submitBehavior(@Valid Behavior behavior, @RequestParam(value = "time", required = true) double time, @RequestParam(value = "key", required = true) String key , @RequestParam(value = "val", required = true) String val) {
		behaviorService.submit(behavior);
		return null;
	}

}
