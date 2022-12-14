package com.app.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.Patient;
import com.app.pojos.PaymentStatus;

public interface IOrderRepository extends JpaRepository<Order, Long> {

	@Query("select o from Order o where o.patient=?1 and o.id=?2")
	Order findByPatient(Patient p, long id);

	@Modifying
	@Query("update Order o set o.amount=?1, o.order_date=?2, o.order_status=?3, o.payment_status=?4 where o.id=?5 ")
	void updateOrder(double amount, LocalDate date, OrderStatus orderStatus, PaymentStatus payment_status, long id);

	@Modifying
	@Query("update Order o set o.payment_status='PAID', o.order_status='PROCESSING' where o.id=?1")
	void updateOrderStatusDetails(long orderId);

	ArrayList<Order> findByPatientId(long id);

	@Query("select o from Order o where o.payment_status='PAID'")
	Page<Order> fetchOrderList(Pageable pageable);

	@Modifying
	@Query("update Order o set  o.order_status='DISPATCHED' where o.id=?1")
	void updateOrderStatus(long orderId);
}
