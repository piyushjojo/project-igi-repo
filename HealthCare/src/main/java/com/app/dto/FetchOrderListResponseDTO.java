package com.app.dto;

import java.util.List;

import com.app.pojos.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FetchOrderListResponseDTO {
	private List<Order> orderlist;
	private int pageno;
}
