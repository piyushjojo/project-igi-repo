package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.LabTests;

public interface LabTestRepository extends JpaRepository<LabTests, Long> {
//	@Modifying
//	@Query("update LabTests lt set lt.price = ?2 where lt.id = ?1 ")
//	void udpatePrice(long id, int newPrice);

}
