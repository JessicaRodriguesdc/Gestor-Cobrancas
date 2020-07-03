package com.gestor.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cobranca")
public class UsuarioController {

    public static final String LOGIN_VIEW = "Login";

    public static final String HOME_VIEW = "Home";

    @RequestMapping
    public String login() {
        return LOGIN_VIEW;
    }

    @RequestMapping(value="/logar", method = RequestMethod.POST)
    public String logar() {
        return "redirect:/cobranca/titulos/dashboard";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastrar() {
        return "redirect:/cobranca/logar";
    }

}
