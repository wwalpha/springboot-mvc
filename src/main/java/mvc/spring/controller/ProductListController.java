package mvc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.spring.service.ProductService;

@Controller
@RequestMapping("/list")
public class ProductListController {
	private final String PRODUCT_LIST = "productList";

	@Autowired
	private ProductService service;

	@GetMapping
	public String search(Model model) {
		model.addAttribute(PRODUCT_LIST, service.findAll());

		return "list";
	}
	
}
