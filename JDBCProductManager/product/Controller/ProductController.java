package product.Controller;

import java.util.ArrayList;

import product.model.VO.ProductVO;
import product.model.service.ProductService;

public class ProductController {

	private ProductService ps = new ProductService();

	public ProductController() {
	}

	public ArrayList<ProductVO> getProductAll() {
		ArrayList<ProductVO> aList = ps.getProductAll();
		if (aList.isEmpty()) {
			return null;
		} else
			return aList;
	}

}
