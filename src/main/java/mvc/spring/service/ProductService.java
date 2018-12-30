package mvc.spring.service;
//
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mvc.spring.model.Product;
import mvc.spring.model.ProductRepository;

/**
 * 商品サービス
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	/**
	 * 全件検索
	 * 
	 * @return
	 */
	public List<Product> findAll() {
		return this.repo.findAll();
	}

	/**
	 * 詳細検索
	 * 
	 * @param itemId 商品ID
	 * @return
	 */
	public Product find(Long itemId) {
		return this.repo.findById(itemId.longValue()).get();
	}

	/**
	 * 保存処理
	 * 
	 * @param item 商品
	 * @return
	 */
	@Transactional
	public Product save(Product item) {
		return this.repo.save(item);
	}

	/**
	 * 削除処理
	 * 
	 * @param item 商品
	 */
	@Transactional
	public void delete(Product item) {
		this.repo.delete(item);
	}
}
