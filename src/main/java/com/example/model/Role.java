package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id//anotação para informar qual campo será a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.AUTO)//anotação para especificar qual será a estratégia para geração de valores da chave primaria
	@Column(name="role_id")//anotação onde constará o nome do coluna da tabela
	private int id;
	
	@Column(name="role")//anotação onde constará o nome do coluna da tabela
	private String role;
	
}
