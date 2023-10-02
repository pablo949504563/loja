package com.dev.loja.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.modelos.Funcionario;
import com.dev.loja.repositorios.FuncionarioRepositorio;

@Controller
public class FuncionarioControler {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	
	@GetMapping("/administrativo/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv =  new ModelAndView("administrativo/funcionarios/cadastro");
		mv.addObject("funcionario",funcionario);
		//mv.addObject("listaCidades",cidadeRepositorio.findAll());
		return mv;
	}
	
//	@GetMapping("/administrativo/funcionarios/cadastrar")
//	public ModelAndView cadastrar(Funcionario funcionario) {
//		ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
//		mv.addObject("funcionario", funcionario);
//		return mv;
//	}
//	@GetMapping("/administrativo/usuarios/cadastro")
//	public ModelAndView cadastro(Funcionario funcionario) {
//		ModelAndView mv = new ModelAndView("administrativo/usuarios/cadastro");
//		mv.addObject("funcionario", funcionario);
//		return mv;
//	}
	
	@GetMapping("/administrativo/funcionarios/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll() );
		
		return mv;
	}
	
	@PostMapping("/administrativo/funcionarios/salvar")
	public ModelAndView salvar(@Validated Funcionario funcionario, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}
		funcionarioRepositorio.saveAndFlush(funcionario);
		return cadastrar(new Funcionario());
	}
}
