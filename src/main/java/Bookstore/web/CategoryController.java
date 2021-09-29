package Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Bookstore.domain.Category;
import Bookstore.domain.CategoryRepository;

public class CategoryController {

	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/categorylist", method = RequestMethod.GET)
	public String listCategory(Model model) {
		
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
		
	}
	
	@RequestMapping(value ="/addcategory")
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    }     
	
	@RequestMapping(value ="/save", method = RequestMethod.POST)
    public String saveCategory(Category category){
		//jos id 0 tai null = SQL Insert, muuten SQL Update
        crepository.save(category);
        return "redirect:categorylist";
    }    
}
