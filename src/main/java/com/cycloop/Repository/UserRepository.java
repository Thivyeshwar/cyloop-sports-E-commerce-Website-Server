package com.cycloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cycloop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
