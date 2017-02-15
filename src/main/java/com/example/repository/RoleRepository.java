package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Role;

@Repository("roleRepository")//anotação para indicar que a classe é um repositório
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}