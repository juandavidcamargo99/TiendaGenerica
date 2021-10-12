package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.core.ExceptionDepthComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/listar-clientes")
	public String listarClientes(Model model) {
		ClienteDao Dao=new ClienteDao();
		model.addAttribute("clientes", Dao.listaDeClientes());
		return "ListarClientes";
	}
}
