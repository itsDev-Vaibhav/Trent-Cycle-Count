package com.tatatrent.cyclecount.rest.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Message {
	
	private Integer code;
	private String status;
	
	public Message() {
		this.code  = HttpStatus.BAD_REQUEST.value();
		this.status = "Some went wrong!";;
	}
}
