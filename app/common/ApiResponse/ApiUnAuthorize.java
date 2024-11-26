package common.ApiResponse;

import common.enums.Status;

public class ApiUnAuthorize extends ApiResponse {

	public ApiUnAuthorize(String message) {
		super(Status.UNAUTHORIZED, message, null);
	}
}
