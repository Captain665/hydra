package common.ApiResponse;import common.enums.Status;public class ApiFailure extends ApiResponse {	public ApiFailure(String message) {		super(Status.FAILURE, message, null);	}	// code formatting	// code again formatting}