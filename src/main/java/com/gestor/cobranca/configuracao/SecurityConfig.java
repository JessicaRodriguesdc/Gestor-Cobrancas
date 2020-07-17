package com.gestor.cobranca.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                        "/cobranca/titulos/*")
                .permitAll()
                .antMatchers(HttpMethod.PUT,"/cobranca/titulos/*/receber").denyAll()
                .antMatchers(HttpMethod.DELETE,"/cobranca/titulos/*").denyAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/cobranca")
                .permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/cobranca/titulos/*/receber").denyAll();
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/cobranca/titulos/*").denyAll();
        http.headers().cacheControl();

//        http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.POST).denyAll();
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/you/can/alsoSpecifyAPath").denyAll();
//        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/path/is/Case/Insensitive").denyAll();
//        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/and/can/haveWildcards/*").denyAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/fonts/**","/js/**");
    }
}
