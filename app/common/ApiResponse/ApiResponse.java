package common.ApiResponse;

import common.enums.Status;

public class ApiResponse {


	public Status status;
	public String message;
	public Object result;

	public ApiResponse(Status status, String message, Object result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
