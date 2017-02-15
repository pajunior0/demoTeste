package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id //anotação para indentificar qual variavel é a chave primaria da tabela
	@GeneratedValue(strategy = GenerationType.AUTO)//anotação que especifica a estratégia para geração de valores de chaves primarias 
	@Column(name = "user_id")//anotação onde constará o nome do coluna da tabela
	private int id;
	
	@Column(name = "email")//anotação onde constará o nome do coluna da tabela
	@Email(message = "*Por favor, Informar um email valido")//anotação de validação de email
	@NotEmpty(message = "*Por favor, Informar um email")//anotação de configuração para preenchimento obrigatório
	private String email;
	
	@Column(name = "password")//anotação onde constará o nome do coluna da tabela
	@Length(min = 5, message = "*Your password must have at least 5 characters")//anotação para limitar o tamanho minimo
	@NotEmpty(message = "*Please provide your password")//anotação de configuração para preenchimento obrigatório
	@Transient//anotação para especificar um campo que não será persistida
	private String password;
	
	@Column(name = "name")//anotação onde constará o nome do coluna da tabela
	@NotEmpty(message = "*Please provide your name")//anotação de configuração para preenchimento obrigatório
	private String name;
	
	@Column(name = "last_name")//anotação onde constará o nome do coluna da tabela
	@NotEmpty(message = "*Please provide your last name")//anotação de configuração para preenchimento obrigatório
	private String lastName;
	
	@Column(name = "active")//anotação onde constará o nome do coluna da tabela
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)//anotação para definir um relacionamento de muitos valores com multiplicidade de muitos para muitos
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))//Especifica o mapeamento de associações, ele é aplicado ao lado proprietário de uma associação
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}