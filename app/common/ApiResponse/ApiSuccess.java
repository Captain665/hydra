package common.ApiResponse;

import common.enums.Status;

public class ApiSuccess extends ApiResponse {


	public ApiSuccess(Object result) {
		super(Status.SUCCESS, "", result);
	}
	//format

}
