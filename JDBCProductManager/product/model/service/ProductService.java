package product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.mb.common.JDBCTemplate;
import product.model.DAO.ProductDAO;
import product.model.VO.ProductVO;

public class ProductService {

	private ProductDAO pDao = new ProductDAO();
	private Connection conn = null;

	public ProductService() {
	}

	public ArrayList<ProductVO> getProductAll() {
		conn = JDBCTemplate.getConnect(conn);
		ArrayList<ProductVO> aList = pDao.getProductAll(conn);
		JDBCTemplate.close(conn);
		return aList;
	}
}
