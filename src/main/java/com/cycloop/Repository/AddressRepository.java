package com.cycloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cycloop.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
