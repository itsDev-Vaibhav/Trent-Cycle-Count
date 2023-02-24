package com.tatatrent.cyclecount.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class RequestDto {
	
	@NotEmpty(message = "Warehouse id is required.")
	private String whseId;
	
	@NotEmpty(message = "Cycle count key is required.")
	private String ccKey;
	
	@NotEmpty(message = "Cycle count detail key is required.")
	private String ccDetailKey;
	
	@NotEmpty(message = "Storer key is required.")
	private String storerKey;
	
	@NotEmpty(message = "SKU is required.")
	private String sku;
	
	@NotEmpty(message = "Altsku count is required.")
	private String altSku;
	
	@Min(value = 1, message = "Qantity cannot be less than 1.")
	@NotNull(message = "Qantity cannot be null.")
	private Integer qty;
	
	@NotEmpty(message = "User is required.")
	private String user;	
}
