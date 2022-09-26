package com.app.dto;

import java.util.ArrayList;

import com.app.pojos.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryResponseDTO {
	private ArrayList<Order> orderlist;
}
