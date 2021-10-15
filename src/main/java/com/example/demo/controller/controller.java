package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.ExceptionDepthComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.dao.ClienteDao;
import com.example.demo.dto.Cliente;

@Controller
public class controller {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView loginpost(HttpServletRequest request, HttpSession session) {
		UsuarioDao Dao=new UsuarioDao();
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(Dao.login(username, password)) {
			session.setAttribute("login", '1');			
		} else {
			mav.addObject("error", "Usuario o contraseña invalidas");
			mav.setViewName("login");
			return mav;
		}
		return new ModelAndView("redirect:/listar-clientes");
	}
	
	@PostMapping("/logout")
	public ModelAndView logout(Model model, HttpSession session) {
		session.removeAttribute("login");
		return new ModelAndView("redirect:/login");
	}
	
	
	
	@GetMapping("/listar-clientes")
	public String listarClientes(Model model) {
		ClienteDao Dao=new ClienteDao();
		model.addAttribute("clientes", Dao.listaDeClientes());
		return "ListarClientes";
	}
}
