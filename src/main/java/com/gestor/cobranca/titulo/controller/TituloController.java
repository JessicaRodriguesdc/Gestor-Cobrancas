package com.gestor.cobranca.titulo.controller;

import java.util.Arrays;
import java.util.List;

import com.gestor.cobranca.titulo.dto.TituloDto;
import com.gestor.cobranca.usuario.entity.Usuario;
import com.gestor.cobranca.titulo.service.PainelStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestor.cobranca.titulo.entity.StatusTitulo;
import com.gestor.cobranca.titulo.entity.Titulo;
import com.gestor.cobranca.titulo.repository.filter.TituloFilter;
import com.gestor.cobranca.titulo.service.CadastroTituloService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/cobranca/titulos")
public class TituloController {

	public static final String HOME_VIEW = "Home";
	
	public static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private CadastroTituloService cadastroTituloService;

	@Autowired
	private PainelStatusService graficoStatusService;

	@RequestMapping
    public ModelAndView home(HttpServletRequest request) {
		Usuario usuario = usuarioSessao(request);

		Long pen = graficoStatusService.pendente(usuario);
		Long rec = graficoStatusService.recebido(usuario);

		ModelAndView mv = new ModelAndView(HOME_VIEW);
		mv.addObject("pendente",pen);
		mv.addObject("recebido",rec);
		return mv;
    }
	
	
	@RequestMapping("/novo")
    public ModelAndView novo() {
		ModelAndView mv= new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
        return mv;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors,
						 RedirectAttributes attributes, HttpServletRequest request) {
		Usuario usuario = usuarioSessao(request);
		if(errors.hasErrors()) {
				return CADASTRO_VIEW;
			}
			try {
				cadastroTituloService.salvar(titulo,usuario);
				attributes.addFlashAttribute("mensagem","Titulo salvo com sucesso!");
				return "redirect:/cobranca/titulos/novo";
			}catch(IllegalAccessException e) {
				errors.rejectValue("dataVencimento", null, e.getMessage());
				return CADASTRO_VIEW;
			}
	}
	
	@RequestMapping("/pesquisar")
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro,
								  HttpServletRequest request) {
		Usuario usuario = usuarioSessao(request);

		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro,usuario);
		
		ModelAndView mv = new ModelAndView("PainelTitulos");
		mv.addObject("titulos",todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoTitulo,
							   HttpServletRequest request){
		Usuario usuario = usuarioSessao(request);

		Titulo titulo = cadastroTituloService.editar(codigoTitulo,usuario);

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes,
						  HttpServletRequest request){
		Usuario usuario = usuarioSessao(request);
		cadastroTituloService.excluir(codigo,usuario);

		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/cobranca/titulos/pesquisar";
	}
	
	@RequestMapping(value="/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo,HttpServletRequest request){
		Usuario usuario = usuarioSessao(request);

		return cadastroTituloService.receber(codigo,usuario);
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}

	private Usuario usuarioSessao(HttpServletRequest request){
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioLogado");
		return usuario;
	}
}
