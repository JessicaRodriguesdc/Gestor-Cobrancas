package com.gestor.cobranca.controller;

import com.gestor.cobranca.configuracao.Util;
import com.gestor.cobranca.model.Usuario;
import com.gestor.cobranca.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cobranca")
public class UsuarioController {

    public static final String LOGIN_VIEW = "Login";

    @Autowired
    private Usuarios repository;

    @RequestMapping
    public String login(Usuario usuario) {
        return LOGIN_VIEW;
    }

    @RequestMapping(value="/logar", method = RequestMethod.POST)
    public String logar(Usuario usuario, HttpSession session, RedirectAttributes attributes, HttpServletRequest request ) {
        request.getSession();

        usuario = this.repository
                .findByLoginAndSenha(usuario.getLogin(),Util.md5(usuario.getSenha()));

        if(usuario != null){
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
    public String cadastrar(@Validated Usuario usuario) {

        Usuario usuarioCadastrar = usuario;
        usuarioCadastrar.setSenha(Util.md5(usuario.getSenha()));
        repository.save(usuarioCadastrar);

        return "redirect:/cobranca";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/cobranca";
    }
}
