/**
 * 
 */
package com.crowdaccent.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.crowdaccent.entity.Hit;
import com.crowdaccent.service.HitService;

/**
 * @author kbhalla
 *
 */
@Controller
@RequestMapping ("/hits")
public class HitController {
	private @Autowired HitService hitService;

	
	@RequestMapping(method = RequestMethod.GET, value = "/list/{id}")
	public ModelAndView listHit(@PathVariable String id){
		ModelAndView m = new ModelAndView();
		Hit h = hitService.getById(id);
		m.addObject(h);
		return m;
	}

   
   @RequestMapping(method = RequestMethod.POST)
   public String create(@Valid Hit hit, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       if (bindingResult.hasErrors()) {
           uiModel.addAttribute("hit", hit);
           addDateTimeFormatPatterns(uiModel);
           return "hits/create";
       }
       uiModel.asMap().clear();
       hitService.save(hit);
       return "redirect:/hits/" + encodeUrlPathSegment(hit.getId().toString(), httpServletRequest);
   }
   
   @RequestMapping(params = "form", method = RequestMethod.GET)
   public String createForm(Model uiModel) {
       uiModel.addAttribute("hit", new Hit());
       addDateTimeFormatPatterns(uiModel);
       return "hits/create";
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public String show(@PathVariable("id") Long id, Model uiModel) {
       addDateTimeFormatPatterns(uiModel);
       uiModel.addAttribute("hit", hitService.getById(id + ""));
       uiModel.addAttribute("itemId", id);
       return "hits/show";
   }
   
   @RequestMapping(method = RequestMethod.GET)
   public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       if (page != null || size != null) {
           int sizeNo = size == null ? 10 : size.intValue();
           final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
           uiModel.addAttribute("hits", hitService.findHitEntries(firstResult, sizeNo));
           float nrOfPages = (float) hitService.countHits() / sizeNo;
           uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       } else {
           uiModel.addAttribute("hits", hitService.getAll());
       }
       addDateTimeFormatPatterns(uiModel);
       return "hits/list";
   }
   
   @RequestMapping(method = RequestMethod.PUT)
   public String update(@Valid Hit hit, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       if (bindingResult.hasErrors()) {
           uiModel.addAttribute("hit", hit);
           addDateTimeFormatPatterns(uiModel);
           return "hits/update";
       }
       uiModel.asMap().clear();
       hitService.save(hit);
       return "redirect:/hits/" + encodeUrlPathSegment(hit.getId().toString(), httpServletRequest);
   }
   
   @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
   public String updateForm(@PathVariable("id") Long id, Model uiModel) {
       uiModel.addAttribute("hit", hitService.getById(id+""));
       addDateTimeFormatPatterns(uiModel);
       return "hits/update";
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       Hit hit = hitService.getById(id+"");
       hitService.delete(hit);
       uiModel.asMap().clear();
       uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
       uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
       return "redirect:/hits";
   }
   
   @ModelAttribute("hits")
   public Collection<Hit> populateHits() {
       return hitService.getAll();
   }
   
   void addDateTimeFormatPatterns(Model uiModel) {
       uiModel.addAttribute("hit_datecreated_date_format", DateTimeFormat.patternForStyle("SL", LocaleContextHolder.getLocale()));
   }
   
   String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
       String enc = httpServletRequest.getCharacterEncoding();
       if (enc == null) {
           enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
       }
       try {
           pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
       }
       catch (UnsupportedEncodingException uee) {}
       return pathSegment;
   }
   
   @RequestMapping(value = "/listByProduct/{id}", method = RequestMethod.GET)
   public String listByProduct(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       if (page != null || size != null) {
           int sizeNo = size == null ? 10 : size.intValue();
           final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
           uiModel.addAttribute("hits", hitService.findHitEntriesByTask(id, firstResult, sizeNo));
           float nrOfPages = (float) hitService.countHitsByTask(id) / sizeNo;
           uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       } else {
           uiModel.addAttribute("hits", hitService.getAllByTask(id));
       }
       addDateTimeFormatPatterns(uiModel);
       return "hits/list";
   }
}
