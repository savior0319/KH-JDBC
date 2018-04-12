package book.controller;

import java.util.ArrayList;

import book.model.DAO.CustomerDAO;
import book.model.VO.CustomerVO;
import book.model.service.CustomerService;

public class CustomerController {

	private CustomerDAO cDao = new CustomerDAO();
	private CustomerService cService = new CustomerService();

	public CustomerController() {
	}

	public ArrayList<CustomerVO> customerSearchAll() {
		if (cService.customerSearchAll().isEmpty()) {
			return null;
		} else
			return cService.customerSearchAll();
	}

	public ArrayList<CustomerVO> customerSearchName(String userName) {
		if (cService.customerSearchName(userName).isEmpty()) {
			return null;
		} else
			return cService.customerSearchName(userName);
	}

	public CustomerVO customerSearchId(String userId) {
		return cService.customerSearchId(userId);
	}

	public int customerSignUp(CustomerVO cv) {
		return cService.customerSignUp(cv);
	}

	public int customerInfoUpdate(CustomerVO cVo) {
		return cService.customerInfoUpdate(cVo);
	}

	public int customerDelete(String userId) {
		return cService.customerDelete(userId);
	}

}
