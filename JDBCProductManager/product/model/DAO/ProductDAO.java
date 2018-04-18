package product.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.mb.common.JDBCTemplate;
import product.model.VO.ProductVO;

public class ProductDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ProductDAO() {
	}

	public ArrayList<ProductVO> getProductAll(Connection conn) {

		ArrayList<ProductVO> aList = new ArrayList<ProductVO>();

		String query = "SELECT * FROM PRODUCT";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				ProductVO pv = new ProductVO();
				pv.setProductId(rs.getString("PRODUCT_ID"));
				pv.setpName(rs.getString("P_NAME"));
				pv.setPrice(rs.getInt("PRICE"));
				pv.setDescription(rs.getString("DESCRIPTION"));

				aList.add(pv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}
}
