package com.crowdaccent.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.crowdaccent.entity.ContentModeration;
import com.crowdaccent.service.ContentModerationService;


/**
 * @author kbhalla
 *
 */
@Controller
@RequestMapping ("/contentmoderations")
public class ContentModerationController {
	private @Autowired ContentModerationService contentModerationService;

	@RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid ContentModeration contentModeration, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("contentModeration", contentModeration);
	            return "contentmoderations/create";
	        }
	        uiModel.asMap().clear();
	        contentModerationService.save(contentModeration);
	        return "redirect:/contentmoderations/" + encodeUrlPathSegment(contentModeration.getId().toString(), httpServletRequest);
	    }
	    
	    @RequestMapping(params = "form", method = RequestMethod.GET)
	    public String createForm(Model uiModel) {
	        uiModel.addAttribute("contentModeration", new ContentModeration());
	        return "contentmoderations/create";
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public String show(@PathVariable("id") Long id, Model uiModel) {
	        uiModel.addAttribute("contentmoderation", contentModerationService.findContentModeration(id));
	        uiModel.addAttribute("itemId", id);
	        return "contentmoderations/show";
	    }
	    
	    @RequestMapping(method = RequestMethod.GET)
	    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	        if (page != null || size != null) {
	            int sizeNo = size == null ? 10 : size.intValue();
	            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
	            uiModel.addAttribute("contentmoderations", contentModerationService.findContentModerationEntries(firstResult, sizeNo));
	            float nrOfPages = (float) contentModerationService.countContentModerations() / sizeNo;
	            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
	        } else {
	            uiModel.addAttribute("contentmoderations", contentModerationService.findAllContentModerations());
	        }
	        return "contentmoderations/list";
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT)
	    public String update(@Valid ContentModeration contentModeration, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("contentModeration", contentModeration);
	            return "contentmoderations/update";
	        }
	        uiModel.asMap().clear();
	        contentModerationService.save(contentModeration);
	        return "redirect:/contentmoderations/" + encodeUrlPathSegment(contentModeration.getId().toString(), httpServletRequest);
	    }
	    
	    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
	        uiModel.addAttribute("contentModeration", contentModerationService.findContentModeration(id));
	        return "contentmoderations/update";
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	        ContentModeration contentModeration = contentModerationService.findContentModeration(id);
	        contentModerationService.delete(contentModeration);
	        uiModel.asMap().clear();
	        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
	        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
	        return "redirect:/contentmoderations";
	    }
	    
	    @ModelAttribute("contentmoderations")
	    public Collection<ContentModeration> populateContentModerations() {
	        return contentModerationService.findAllContentModerations();
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
	    @RequestMapping(method = RequestMethod.GET, value = "/createHIT/{id}")
	    public String createHIT(@PathVariable String id, HttpServletRequest httpServletRequest){
	    	ContentModeration p = contentModerationService.createHIT(id);
	        return "redirect:/hits/listByTask/" + encodeUrlPathSegment(p.getId().toString(), httpServletRequest);
	    }
}
