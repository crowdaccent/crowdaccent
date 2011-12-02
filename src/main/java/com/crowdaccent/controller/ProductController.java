/**
 * 
 */
package com.crowdaccent.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

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

import com.crowdaccent.entity.Product;
import com.crowdaccent.service.ProductService;

/**
 * @author kbhalla
 *
 */
@Controller
@RequestMapping ("/products")
public class ProductController {
	private @Autowired ProductService productService;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listProducts(){
		ModelAndView m = new ModelAndView();
		List<Product> products = productService.getNumProducts(5);
		m.addObject(products);
		return m;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/{id}")
	public ModelAndView listProduct(@PathVariable String id){
		ModelAndView m = new ModelAndView();
		Product p = productService.getById(id);
		m.addObject(p);
		return m;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/createHIT/{id}")
	public ModelAndView createHIT(@PathVariable String id){
		ModelAndView m = new ModelAndView();
		Product p = productService.createHIT(id);
		m.addObject(p);
		return m;
	}

   @RequestMapping(method = RequestMethod.GET, value = "/createIntroductionHIT/{id}")
    public ModelAndView createIntroductionHIT(@PathVariable String id){
        ModelAndView m = new ModelAndView();
        Product p = productService.createIntroductionHIT(id);
        m.addObject(p);
        return m;
    }
   
   @RequestMapping(method = RequestMethod.GET, value = "/createIntroductionHITWithImage/{id}")
   public String createIntroductionHITWithImage(@PathVariable String id, HttpServletRequest httpServletRequest){
       Product p = productService.createIntroductionHITWithImage(id);
       return "redirect:/hits/listByProduct/" + encodeUrlPathSegment(p.getId().toString(), httpServletRequest);
   }
   
   @RequestMapping(method = RequestMethod.POST)
   public String create(@Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       if (bindingResult.hasErrors()) {
           uiModel.addAttribute("product", product);
           addDateTimeFormatPatterns(uiModel);
           return "products/create";
       }
       uiModel.asMap().clear();
       productService.save(product);
       return "redirect:/products/" + encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
   }
   
   @RequestMapping(params = "form", method = RequestMethod.GET)
   public String createForm(Model uiModel) {
       uiModel.addAttribute("product", new Product());
       addDateTimeFormatPatterns(uiModel);
       return "products/create";
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public String show(@PathVariable("id") Long id, Model uiModel) {
       addDateTimeFormatPatterns(uiModel);
       uiModel.addAttribute("product", productService.getById(id + ""));
       uiModel.addAttribute("itemId", id);
       return "products/show";
   }
   
   @RequestMapping(method = RequestMethod.GET)
   public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       if (page != null || size != null) {
           int sizeNo = size == null ? 10 : size.intValue();
           final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
           uiModel.addAttribute("products", productService.findProductEntries(firstResult, sizeNo));
           float nrOfPages = (float) productService.countProducts() / sizeNo;
           uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       } else {
           uiModel.addAttribute("products", productService.getAll());
       }
       addDateTimeFormatPatterns(uiModel);
       return "products/list";
   }
   
   @RequestMapping(method = RequestMethod.PUT)
   public String update(@Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       if (bindingResult.hasErrors()) {
           uiModel.addAttribute("product", product);
           addDateTimeFormatPatterns(uiModel);
           return "products/update";
       }
       uiModel.asMap().clear();
       productService.save(product);
       return "redirect:/products/" + encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
   }
   
   @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
   public String updateForm(@PathVariable("id") Long id, Model uiModel) {
       uiModel.addAttribute("product", productService.getById(id+""));
       addDateTimeFormatPatterns(uiModel);
       return "products/update";
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       Product product = productService.getById(id+"");
       productService.delete(product);
       uiModel.asMap().clear();
       uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
       uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
       return "redirect:/products";
   }
   
   @ModelAttribute("products")
   public Collection<Product> populateProducts() {
       return productService.getAll();
   }
   
   void addDateTimeFormatPatterns(Model uiModel) {
       uiModel.addAttribute("product_datecreated_date_format", DateTimeFormat.patternForStyle("SL", LocaleContextHolder.getLocale()));
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
   

}
