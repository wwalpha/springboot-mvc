package mvc.spring.form;

import lombok.Data;
import mvc.spring.model.Product;

@Data
public class ProductDetailForm {

	private Product product;
	private String kbn;

}
