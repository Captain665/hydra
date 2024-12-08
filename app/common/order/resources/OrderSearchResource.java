package common.order.resources;

import play.mvc.QueryStringBindable;

import java.util.Map;
import java.util.Optional;

public class OrderSearchResource implements QueryStringBindable<OrderSearchResource> {
	public Integer page;
	public Integer size;
	
	@Override
	public Optional<OrderSearchResource> bind(String key, Map<String, String[]> data) {
		String[] pageStr = data.get("page");
		String[] sizeStr = data.get("size");

		try {
			if (pageStr != null && pageStr.length > 0) {
				page = Integer.parseInt(pageStr[0]);
				if (page < 1) {
					page = 1;
				}
			} else {
				page = 1;
			}

			if (sizeStr != null && sizeStr.length > 0) {
				size = Integer.parseInt(sizeStr[0]);
				if (size < 1) {
					size = 10;
				}
			} else {
				size = 10;
			}

			return Optional.of(this);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public String unbind(String key) {
		return null;
	}

	@Override
	public String javascriptUnbind() {
		return null;
	}

	@Override
	public String toString() {
		return "OrderSearchResource{" +
				"page=" + page +
				", size=" + size +
				'}';
	}
}
