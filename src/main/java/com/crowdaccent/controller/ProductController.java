/**
 * 
 */
package com.crowdaccent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crowdaccent.entity.Product;
import com.crowdaccent.service.ProductService;

/**
 * @author kbhalla
 *
 */
@Controller
@RequestMapping ("/product")
public class ProductController {
	private ProductService productService;

	/**
	 * @param productService the productService to set
	 */
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
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
   public ModelAndView createIntroductionHITWithImage(@PathVariable String id){
       ModelAndView m = new ModelAndView();
       Product p = productService.createIntroductionHITWithImage(id);
       m.addObject(p);
       return m;
   }
}
