package utilities;

public class ResponseDTO {

	public String status;
	public String message;
	public Object result;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public ResponseDTO(String status, String message, Object result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}
}
