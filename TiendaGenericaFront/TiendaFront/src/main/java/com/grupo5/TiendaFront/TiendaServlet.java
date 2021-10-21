package com.grupo5.TiendaFront;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/TiendaServlet")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TiendaServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String listar=request.getParameter("Listar");
		String agregar=request.getParameter("Agregar");
		if(agregar != null) {
			agregarUsuario(request, response);
		}
		if(listar != null) {
			listarUsuarios(request, response);
		}
	}
		
		public void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
			Usuarios usuario=new Usuarios();
			usuario.setNombre_usuario(request.getParameter("nombre"));
			usuario.setCedula_usuario(Long.parseLong(request.getParameter("cedula")));
			usuario.setEmail_usuario(request.getParameter("email"));
			usuario.setUsuario(request.getParameter("usuario"));
			usuario.setPassword(request.getParameter("password"));
			int respuesta=0;
			try {
				respuesta=TestJSON.postJSON(usuario);
				PrintWriter writer=response.getWriter();
				if(respuesta==200) {
					writer.println("Registro Agregado!");
				} else {
					writer.println("Error: "+respuesta);
				}
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		public void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
			try {
				ArrayList<Usuarios> lista=TestJSON.getJSON();
				String pagina="/resultado.jsp";
				request.setAttribute("lista", lista);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(pagina);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
