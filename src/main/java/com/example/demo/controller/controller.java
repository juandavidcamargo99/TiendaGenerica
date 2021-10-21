package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ExceptionDepthComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.dao.ClienteDao;
import com.example.demo.dto.Cliente;
import com.example.demo.dto.Usuario;

@Controller
public class controller {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	/*************
	 * LOGIN Y LOGOUT
	 *************/

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView loginpost(HttpServletRequest request, HttpSession session) {
		UsuarioDao Dao = new UsuarioDao();
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (Dao.login(username, password)) {
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

	/*************
	 * USUARIOS
	 *************/

	@GetMapping("/crear-usuario")
	public String crearUsuario(Model model) {
		return "crearUsuario";
	}

	@PostMapping("/crear-usuario")
	public String crearUsuarioPost(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		UsuarioDao Dao = new UsuarioDao();
		// buscar usuario por accountName
		if (Dao.buscarUsuarioPorAccountName(accountName)) {
			redirectAttributes.addFlashAttribute("msg", "El usuario ya se encuentra registrado");
		} else {
			// crear usuario si no existe
			if (Dao.crearUsuario(name, lastName, accountName, password)) {
				redirectAttributes.addFlashAttribute("msg", "Usuario creado con exito");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Usuario creado con exito");
			}
		}
		return "redirect:/listar-usuarios";
	}

	@GetMapping("/listar-usuarios")
	public String listarUsuarios(Model model) {
		UsuarioDao Dao = new UsuarioDao();
		model.addAttribute("usuarios", Dao.listarUsuarios());
		return "listarUsuarios";
	}

	@GetMapping("/actualizar-usuario/{id}")
	public String ActualizarUsuario(Model model, @PathVariable Integer id, HttpServletResponse res) {
		UsuarioDao Dao = new UsuarioDao();
		ArrayList<Usuario> user = new ArrayList<Usuario>();
		user = Dao.buscarUsuarioPorId(id);
		if (user.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			model.addAttribute("usuario", user.get(0));		
		}
		return "actualizarUsuario";
	}

	@PostMapping("/actualizar-usuario")
	public String ActualizarUsuarioPut(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		UsuarioDao Dao = new UsuarioDao();
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		if(Dao.actualizarUsuario(name, lastName, accountName, password)) {
			redirectAttributes.addFlashAttribute("msg", "Usuario actualizado con exito");	
		}
		return "redirect:/listar-usuarios";
	}
	
	@PostMapping("/eliminar-usuario")
	public String eliminarUsuario(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		UsuarioDao Dao = new UsuarioDao();
		System.out.print(id);
		if(Dao.eliminarUsuario(id)) {
			redirectAttributes.addFlashAttribute("msg", "Usuario eliminado con exito");
		}else {
			redirectAttributes.addFlashAttribute("msg", "Nose ha podido eliminar el usuario");
		}
		return "redirect:/listar-usuarios";
	}
	
	
	/*************
	 * CLIENTES
	 *************/

	@GetMapping("/listar-clientes")
	public String listarClientes(HttpServletRequest request, Model model) {
		ClienteDao Dao = new ClienteDao();
		model.addAttribute("clientes", Dao.listaDeClientes());
		return "ListarClientes";
	}
}
