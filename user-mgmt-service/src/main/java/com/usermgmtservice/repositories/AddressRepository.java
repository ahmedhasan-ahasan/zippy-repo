package com.usermgmtservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usermgmtservice.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query("select address from Address address where address.systemUser.systemUserId = :systemUserId")
	List<Address> findBySystemUser(@Param("systemUserId") long systemUserId);
	
}
