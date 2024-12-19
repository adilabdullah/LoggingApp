package com.spring.logging.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="audit_log")
public class logs {

	@Column @Id private int sno;
	@Column private String state;

	@Column private String log_message;
	@Column private String correlation_id;
	@Column private String message_id;
	@Column private String source_ip;
	@Column private String request_url;
	@Column private String put_date;
	
	
	@Override
	public String toString() {
		return "logs [state=" + state + ", log_message=" + log_message + ", correlation_id=" + correlation_id
				+ ", message_id=" + message_id + ", source_ip=" + source_ip + ", request_url=" + request_url
				+ ", sno=" + sno + ", put_date=" + put_date + "]";
	}
	public logs(String state, String log_message, String correlation_id, String message_id, String source_ip,
			String request_url, String put_date,int sno) {
		super();
		this.state = state;
		this.log_message = log_message;
		this.correlation_id = correlation_id;
		this.message_id = message_id;
		this.source_ip = source_ip;
		this.request_url = request_url;
		this.put_date = put_date;
		this.sno=sno;
	}
	public logs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLog_message() {
		return log_message;
	}
	public void setLog_message(String log_message) {
		this.log_message = log_message;
	}
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getSource_ip() {
		return source_ip;
	}
	public void setSource_ip(String source_ip) {
		this.source_ip = source_ip;
	}
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
	public String getPut_date() {
		return put_date;
	}
	public void setPut_date(String put_date) {
		this.put_date = put_date;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
}
