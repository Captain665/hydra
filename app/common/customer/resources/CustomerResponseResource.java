package common.customer.resources;

import java.util.List;

public class CustomerResponseResource {
	public Integer customerCount;
	public List<CustomerListResponseResource> customerList;

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public List<CustomerListResponseResource> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerListResponseResource> customerList) {
		this.customerList = customerList;
	}

	public CustomerResponseResource(Integer customerCount, List<CustomerListResponseResource> customerList) {
		this.customerCount = customerCount;
		this.customerList = customerList;
	}

	public CustomerResponseResource() {
	}

	@Override
	public String toString() {
		return "CustomerResponseResource{" +
				"customerCount=" + customerCount +
				", customerList=" + customerList +
				'}';
	}
}
