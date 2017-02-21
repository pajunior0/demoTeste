package com.example.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/")//Permite que a configuração do HttpSecurity seja apenas invocada ao corresponder ao padrão fornecido.
					.permitAll()
				.antMatchers("/login")//Permite que a configuração do HttpSecurity seja apenas invocada ao corresponder ao padrão fornecido.
					.permitAll()
				.antMatchers("/registration")//Permite que a configuração do HttpSecurity seja apenas invocada ao corresponder ao padrão fornecido.
					.permitAll()
				.antMatchers("/admin/**")//Permite que a configuração do HttpSecurity seja apenas invocada ao corresponder ao padrão fornecido.
					.hasAuthority("ADMIN")
						.anyRequest()
				.authenticated()
			.and()
				.csrf()
				.disable()
				.formLogin()//Permite que os usuários se autentiquem com o login baseado em formulário
					.loginPage("/login")//request mapping da pagina de login
						.failureUrl("/login?error=true")//redirecionamento em caso de erro
						.usernameParameter("email")//paramentro usuário
						.passwordParameter("password")//paramentro senha
						.defaultSuccessUrl("/admin/home")//redirecionamento em caso de sucesso
			.and()
				.logout()//fornece suporte ao logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}