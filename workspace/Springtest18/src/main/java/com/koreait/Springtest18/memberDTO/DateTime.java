package com.koreait.Springtest18.memberDTO;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter
@ToString
public class DateTime {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	public DateTime(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	protected DateTime() {}
	
}
