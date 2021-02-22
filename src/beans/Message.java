package beans;

public class Message {
	private boolean status;
	private String message;
	public Message() {
		this.status = false;
		this.message = "";
	}
	public Message(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	public Message(Message msg) {
		this.status = msg.status;
		this.message = msg.message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [status=" + status + ", message=" + message + "]";
	}
}
