package com.ezen.springmvc.domain.member.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >=10000")
public class Item {
	@NotNull
	private Long id;
	@NotBlank
	private String itemName;
	@NotNull
	@Range(min = 1000, max = 1000000)
	private Integer price;
	@NotNull
	@Max(9999)
	private Integer quantity;
}


