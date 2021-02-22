package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mail {
	private String senderAddress;
	private String senderPass;
	private String receiverAddress;
	private String message;
	private String subject;
	private String date;
	public Mail() {
		this.senderAddress = "computer2073.2016@gmail.com";
		this.senderPass = "Dangerous@#$5658";
		this.receiverAddress = receiverAddress;
		this.message = message;
		this.subject = subject;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.date = formatter.format(date);
	}
	public Mail(String senderAddress, String senderPass, String receiverAddress, String message, String subject) {
		this.senderAddress = senderAddress;
		this.senderPass = senderPass;
		this.receiverAddress = receiverAddress;
		this.message = message;
		this.subject = subject;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.date = formatter.format(date);
	}
	public Mail(Mail m) {
		this.senderAddress = m.senderAddress;
		this.senderPass = m.senderPass;
		this.receiverAddress = m.receiverAddress;
		this.message = m.message;
		this.subject = m.subject;
		this.date = m.date;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getSenderPass() {
		return senderPass;
	}
	public void setSenderPass(String senderPass) {
		this.senderPass = senderPass;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	@Override
	public String toString() {
		return "Mail [senderAddress=" + senderAddress + ", senderPass=" + senderPass + ", receiverAddress="
				+ receiverAddress + ", message=" + message + ", subject=" + subject + "]";
	}
}
