package book.controller;

import java.util.ArrayList;

import book.model.DAO.CustomerDAO;
import book.model.VO.CustomerVO;

public class CustomerController {

	private CustomerDAO cDao = new CustomerDAO();

	public CustomerController() {
	}

	public ArrayList<CustomerVO> customerSearchAll() {
		if (cDao.customerSearchAll().isEmpty()) {
			return null;
		} else
			return cDao.customerSearchAll();
	}

	public ArrayList<CustomerVO> customerSearchName(String userName) {
		if (cDao.customerSearchName(userName).isEmpty()) {
			return null;
		} else
			return cDao.customerSearchName(userName);
	}

	public CustomerVO customerSearchId(String userId) {
		return cDao.customerSearchId(userId);
	}

	public int customerSignUp(CustomerVO cv) {
		return cDao.customerSignUp(cv);
	}

	public int customerInfoUpdate(CustomerVO cVo) {
		return cDao.customerInfoUpdate(cVo);
	}

	public int customerDelete(String userId) {
		return cDao.customerDelete(userId);
	}

}
