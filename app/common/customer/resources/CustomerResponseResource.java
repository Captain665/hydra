package common.customer.resources;

import scala.Int;

import java.math.BigInteger;
import java.util.List;

public class CustomerResponseResource {
	public Integer count;
	public List<CustomerListResponseResource> customerList;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<CustomerListResponseResource> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerListResponseResource> customerList) {
		this.customerList = customerList;
	}

	public CustomerResponseResource(Integer count, List<CustomerListResponseResource> customerList) {
		this.count = count;
		this.customerList = customerList;
	}

	public CustomerResponseResource() {
	}
}
