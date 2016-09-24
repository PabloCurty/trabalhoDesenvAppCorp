package br.uff.ic.alomundo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AloMundoServlet
 */
@WebServlet("/AloMundoServlet")
public class AloMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AloMundoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log("Iniciando a servlet");
	}

	public void destroy() {
		super.destroy();
		log("Destruindo a servlet");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		/*
		 * Use o request para ler o que chega pelo HTTP(HTML, Cookies, etc..)
		 * 
		 * Use o response para escrever o resultado do Servlet ( conteúdo,
		 * content type, cookies, etc..) Use o "out" para enviar conteúdo e
		 * escrever no navegador
		 */

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>");

		// parametros de header
		Enumeration<String> headerNames = request.getHeaderNames();
		String headerName;
		out.println("<h1>Parametros de Header</h1>");
		out.println(" ");
		while (headerNames.hasMoreElements()) {
			headerName = (String) headerNames.nextElement();
			out.println("<P>" + headerName + " = " + request.getHeader(headerName) + " ---- " + "</P>");
		}

		// parametros de request
		Enumeration<String> paramNames = request.getParameterNames();
		String paramName;
		out.println("<h1>Todos os parametros</h1>");
		out.println(" ");
		while (paramNames.hasMoreElements()) {
			// primeiro parametro
			paramName = (String) paramNames.nextElement();
			out.println("<h1>Primeiro valor de dado parametro</h1>");
			out.println("<P>" + paramName + " = " + request.getParameter(paramName) + " ---- " + "</P>");
			// lista de parametros com mesmo nome
			String[] paransName = request.getParameterValues(paramName);
			out.println(" ");
			for (int i = 0; i < paransName.length; i++) {
				out.println("<h1>" + i + "Valor de parametro nome" + paramName + "</h1>");
				out.println("<P>" + paramName + " " + i + " = " + paransName[i] + " ---- " + "</P>");

			}
		}
		out.println("</br>");
		out.println("</br>");
		//variavel de sessão
		out.println("<p1>Variaveis de sessão</p1>");
		session = request.getSession();
		Enumeration<String> atribNames = session.getAttributeNames();
		while (atribNames.hasMoreElements()) {
			String paramName2 = (String) atribNames.nextElement();
			out.println("<P>" + paramName2 + " = " + session.getAttribute(paramName2) + " ---- " + "</P>");
		}
		out.println("</BODY></HTML>");
		out.close();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>");
		out.println("<P>Alo Mundo!</P>");
		out.println("</BODY></HTML>");
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
