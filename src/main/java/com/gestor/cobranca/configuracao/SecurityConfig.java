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

    //Configurando a seguranca
    //Add rotas do sistema na configuracao
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
                .mvcMatchers(HttpMethod.PUT,"/cobranca/titulos/{codigo}/receber").permitAll()
                .mvcMatchers(HttpMethod.DELETE,"/cobranca/titulos/{codigo}").denyAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/cobranca")
                .permitAll();
        http.csrf().disable();
        http.headers().cacheControl();
    }

    //Mostrando a visualizacao para os arquivos de fontes e Script's
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/fonts/**","/js/**");
    }
}
