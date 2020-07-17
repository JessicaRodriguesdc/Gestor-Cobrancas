package com.gestor.cobranca.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/cobranca/logar",
                        "/cobranca/cadastrar",
                        "/cobranca/logout",
                        "/cobranca/titulos",
                        "/cobranca/titulos/novo",
                        "/cobranca/titulos/cadastrar",
                        "/cobranca/titulos/pesquisar",
                        "/cobranca/titulos/{**}",
                        "/cobranca/titulos/{**}/{**}",
                        "/cobranca/titulos/logout")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/cobranca")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/fonts/**","/js/**","/webjars/**");
    }
}
