package mvc.spring.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mvc.spring.form.ProductDetailForm;
import mvc.spring.model.Product;
import mvc.spring.service.ProductService;

@Controller
@RequestMapping("/detail")
@SessionAttributes("detailForm")
public class ProductController {

	private static final String MODE_INSERT = "1";
	private static final String MODE_UPDATE = "3";
	private static final String MODE_DELETE = "4";

	@Autowired
	private ProductService service;

	@GetMapping()
	public String init(@ModelAttribute("detailForm") ProductDetailForm form) {
		form.setProduct(new Product());
		return "detail";
	}

	@GetMapping("/{id}")
	public String init(@ModelAttribute("detailForm") ProductDetailForm form, @PathVariable long id) {
		Product product = service.find(id);

		form.setProduct(product);

		return "detail";
	}

	@PostMapping("/test")
	public String submit(@Validated @ModelAttribute("detailForm") ProductDetailForm form, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("validationError", "Validateエラー");
			return "detail";
		}

		if (StringUtils.equals(form.getKbn(), MODE_INSERT) || StringUtils.equals(form.getKbn(), MODE_UPDATE)) {
			service.save(form.getProduct());
		}
		if (StringUtils.equals(form.getKbn(), MODE_DELETE)) {
			service.delete(form.getProduct());
		}

		return "redirect:/";
	}

	@ModelAttribute("detailForm")
	public ProductDetailForm createForm(@RequestParam("kbn") String kbn) {
		ProductDetailForm form = new ProductDetailForm();

		form.setKbn(kbn);

		return form;
	}
}
