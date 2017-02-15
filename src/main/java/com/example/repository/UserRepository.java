package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository("userRepository")//anotação para indicar que a classe é um repositório.
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}