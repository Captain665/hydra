package common.order.resources;

import java.util.List;

public class OrderListCountResponseResource {

	public Integer orderCount;

	public List<OrderListResponseResource> orderList;

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public List<OrderListResponseResource> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderListResponseResource> orderList) {
		this.orderList = orderList;
	}

	public OrderListCountResponseResource(Integer orderCount, List<OrderListResponseResource> orderList) {
		this.orderCount = orderCount;
		this.orderList = orderList;
	}

	public OrderListCountResponseResource() {
	}
}
