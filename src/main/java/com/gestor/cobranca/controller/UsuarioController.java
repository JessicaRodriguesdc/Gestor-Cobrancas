package com.gestor.cobranca.controller;

import com.gestor.cobranca.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cobranca")
public class UsuarioController {

    public static final String LOGIN_VIEW = "Login";

    public static final String HOME_VIEW = "Home";

    @RequestMapping
    public String login(Usuario usuario) {
        return LOGIN_VIEW;
    }

    @RequestMapping(value="/logar", method = RequestMethod.POST)
    public String logar(Usuario usuario, HttpSession session,RedirectAttributes attributes) {
        if(usuario.getLogin().equals("dada") && usuario.getSenha().equals("123")){
            usuario.setNome("Jessica");
            //Guardar sessao o objeto usuario
            session.setAttribute("usuarioLogado",usuario);
            return "redirect:/cobranca/titulos";
        }else {
            //enviar mensagem de erro
            attributes.addFlashAttribute("mensagem","Login/Senha invalidos");
            return "redirect:/cobranca";
        }
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastrar(Usuario usuario) {
        return "redirect:/cobranca/logar";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/cobranca";
    }
}
