package common.order.resources;

import play.mvc.QueryStringBindable;

import java.util.Map;
import java.util.Optional;

public class OrderSearchResource implements QueryStringBindable<OrderSearchResource> {

	public Integer page;
	public Integer size;


	public OrderSearchResource(Integer page, Integer size) {
		this.page = page;
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public Optional<OrderSearchResource> bind(String key, Map<String, String[]> data) {
		return Optional.empty();
	}

	@Override
	public String unbind(String key) {
		return null;
	}

	@Override
	public String javascriptUnbind() {
		return null;
	}
}
