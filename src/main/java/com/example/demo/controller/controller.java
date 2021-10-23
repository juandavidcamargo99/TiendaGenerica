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
import com.example.demo.dao.ProveedorDao;
import com.example.demo.dto.Cliente;
import com.example.demo.dto.Proveedor;
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
		String cardId = request.getParameter("cardId");
		String password = request.getParameter("password");
		UsuarioDao Dao = new UsuarioDao();
		// buscar usuario por accountName
		if (Dao.buscarUsuarioPorAccountName(accountName)) {
			redirectAttributes.addFlashAttribute("msg", "El usuario ya se encuentra registrado");
		} else {
			// crear usuario si no existe
			if (Dao.crearUsuario(name, lastName, accountName, cardId, password)) {
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
		Integer id =  Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		if(Dao.actualizarUsuario(id, name, lastName, accountName, password)) {
			redirectAttributes.addFlashAttribute("msg", "Usuario actualizado con exito");	
		}
		return "redirect:/listar-usuarios";
	}
	
	@PostMapping("/eliminar-usuario")
	public String eliminarUsuario(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		UsuarioDao Dao = new UsuarioDao();
		if(Dao.eliminarUsuario(id)) {
			redirectAttributes.addFlashAttribute("msg", "Usuario eliminado con exito");
		}else {
			redirectAttributes.addFlashAttribute("msg", "Nose ha podido eliminar el usuario");
		}
		return "redirect:/listar-usuarios";
	}
	
	@PostMapping("/usuario-por-cedula")
	public String buscarUsuarioPorCedula(@RequestParam String cardId, RedirectAttributes redirectAttributes) {
		UsuarioDao Dao = new UsuarioDao();
		ArrayList<Usuario> user = new ArrayList<Usuario>();
		user = Dao.buscarPorCedula(cardId);
		if(user.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Nose ha podido encontrar el ususario");
		}else {
			redirectAttributes.addFlashAttribute("usuariosPorCedula", user);
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
	
	@GetMapping("/crear-cliente")
	public String crearClientes(Model model) {
		return "crearCliente";
	}

	@PostMapping("/crear-cliente")
	public String crearClientePost(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String cardId = request.getParameter("cardId");
		ClienteDao Dao = new ClienteDao();
		// buscar cliente por accountName
		if (Dao.buscarPorCedula(cardId).isEmpty()) {
			// crear cliente si no existe
			if (Dao.crearCliente(name, lastName, cardId)) {
				redirectAttributes.addFlashAttribute("msg", "Clietne creado con exito");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Clietne creado con exito");
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "El cliente ya se encuentra registrado");
		}
		return "redirect:/listar-clientes";
	}
	
	@PostMapping("/cliente-por-cedula")
	public String buscarClientePorCedula(@RequestParam Integer cardId, RedirectAttributes redirectAttributes) {
		ClienteDao Dao = new ClienteDao();
		ArrayList<Cliente> cliente  = new ArrayList<Cliente>();
		cliente = Dao.buscarClientePorId(cardId);
		if(cliente.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Nose ha podido encontrar el ususario");
		}else {
			redirectAttributes.addFlashAttribute("clientesPorCedula", cliente);
		}
		return "redirect:/listar-clientes";
	}
	
	@GetMapping("/actualizar-cliente/{id}")
	public String ActualizarCliente(Model model, @PathVariable Integer id, HttpServletResponse res) {
		ClienteDao Dao = new ClienteDao();
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		cliente = Dao.buscarClientePorId(id);
		if (cliente.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			model.addAttribute("cliente", cliente.get(0));		
		}
		return "actualizarCliente";
	}

	@PostMapping("/actualizar-cliente")
	public String ActualizarClientePut(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ClienteDao Dao = new ClienteDao();
		Integer id =  Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		if(Dao.actualizarCliente(id, name, lastName)) {
			redirectAttributes.addFlashAttribute("msg", "Usuario actualizado con exito");	
		}
		return "redirect:/listar-clientes";
	}
	
	@PostMapping("/eliminar-cliente")
	public String eliminarCLiente(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		ClienteDao Dao = new ClienteDao();
		if(Dao.eliminarCliente(id)) {
			redirectAttributes.addFlashAttribute("msg", "Cliente eliminado con exito");
		}else {
			redirectAttributes.addFlashAttribute("msg", "No se ha podido eliminar el cliente");
		}
		return "redirect:/listar-clientes";
	}
	
	/*************
	 * PROVEEDORES
	 *************/
	
	@GetMapping("/listar-proveedores")
	public String listarProveedores(HttpServletRequest request, Model model) {
		ProveedorDao Dao = new ProveedorDao();
		model.addAttribute("proveedores", Dao.listaDeProveedores());
		return "ListarProveedores";
	}
	
	@GetMapping("/crear-proveedor")
	public String crearProveedor(Model model) {
		return "crearProveedor";
	}

	@PostMapping("/crear-proveedor")
	public String crearProveedorPost(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String cardId = request.getParameter("cardId");
		ProveedorDao Dao = new ProveedorDao();
		// buscar proveedor por accountName
		if (Dao.buscarPorNIT(cardId).isEmpty()) {
			// crear proveedor si no existe
			if (Dao.crearProveedor(name, address, cardId)) {
				redirectAttributes.addFlashAttribute("msg", "Proveedor creado con exito");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Proveedor creado con exito");
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "El Proveedor ya se encuentra registrado");
		}
		return "redirect:/listar-proveedores";
	}
	
	@GetMapping("/actualizar-proveedor/{id}")
	public String ActualizarProveedor(Model model, @PathVariable Integer id, HttpServletResponse res) {
		ProveedorDao Dao = new ProveedorDao();
		ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
		proveedor = Dao.buscarProvedorPorId(id);
		if (proveedor.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			model.addAttribute("proveedor", proveedor.get(0));		
		}
		return "actualizarProveedor";
	}
	
	@PostMapping("/actualizar-proveedor")
	public String ActualizarProveedorPut(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ProveedorDao Dao = new ProveedorDao();
		Integer id =  Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String cardId = request.getParameter("cardId");
		if(Dao.actualizarProveedor(id, name, address, cardId)) {
			redirectAttributes.addFlashAttribute("msg", "Usuario actualizado con exito");	
		}
		return "redirect:/listar-proveedores";
	}
	
	@PostMapping("/eliminar-proveedor")
	public String eliminarProveedor(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		ProveedorDao Dao = new ProveedorDao();
		if(Dao.eliminarProveedor(id)) {
			redirectAttributes.addFlashAttribute("msg", "Proveedor eliminado con exito");
		}else {
			redirectAttributes.addFlashAttribute("msg", "No se ha podido eliminar el Proveedor");
		}
		return "redirect:/listar-proveedores";
	}
}
