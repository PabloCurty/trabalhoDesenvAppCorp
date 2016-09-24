package br.uff.ic.alomundo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Contador
 */
@WebServlet(urlPatterns = "/Contador")
public class Contador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String EDGE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586";
	private static final String CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
	private static final String MOZILA = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0";

	// variaveis de instância
	private int contadorEdge = 0;
	private int contadorChrome = 0;
	private int contadorMozila = 0;
	// variável para mostrar um dos browsers
	private int contador = 0;
	// número de vezes que qualquer usuário usou o serviço desde que o servidor
	// foi iniciado
	private int contadorMaster = 0;
	private int contadorSessao = 0;

	// variável de sessão
	HttpSession session;

	// servlet context
	ServletContext servletContext;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Contador() {
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
		this.contadorSessao = 0;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// variavel de sessão
		session = request.getSession();
		Object objParam = session.getAttribute("contadorSessao");
		if (objParam != null) {
			contadorSessao = Integer.parseInt(objParam.toString());
			contadorSessao++;
			session.setAttribute("contadorSessao", contadorSessao);
		} else {
			contadorSessao = 0;
			contadorSessao++;
			session.setAttribute("contadorSessao", contadorSessao);
		}

		// servletContext
		servletContext = request.getServletContext();
		Object objAtt = servletContext.getAttribute("contadorMaster");
		if (objAtt != null) {
			contadorMaster = Integer.parseInt(objAtt.toString());
			contadorMaster++;
			servletContext.setAttribute("contadorMaster", contadorMaster);
		} else {
			contadorMaster = 0;
			contadorMaster++;
			servletContext.setAttribute("contadorMaster", contadorMaster);
		}

		// eu prevejo os 3 browsers mais importantes, EDGE, MOZILLA, CHROME...
		// se não for esses é o IE
		// eu não tenho outros browsers para ver o id na cabeçalho http
		String nomeBrowser = "Internet explorer";
		// parametro inicial
		int inicio = Integer.parseInt(request.getParameter("inicio"));
		// parametro final
		int fim = Integer.parseInt(request.getParameter("fim"));
		// somatório desde um número inicial até um número final fornecidos como
		// parâmetros de chamada
		int soma = inicio;
		for (int i = inicio + 1; i <= fim; i++) {
			soma += i;
		}

		// browser
		String userAgent = request.getHeader("User-Agent");

		// recebe o writer
		PrintWriter out = response.getWriter();

		// escreve o texto
		out.println("<html>");
		out.println("<body>");
		RequestDispatcher dispatcher;
		dispatcher = getServletContext().getRequestDispatcher("/cabecalho.html");
		if (dispatcher != null) {
			dispatcher.include(request, response);
		}
		out.println("</br>");
		out.println("<h1>Chamando com GET</h1>");
		out.println("</br>");
		// sessão
		out.println("<p>Você usou o serviço " + contadorSessao + " vezes nessa sessão</p>");
		out.println("</br>");
		// somatorio
		out.println("<p>O somatório de " + inicio + " até " + fim + " é: " + soma + "</p>");
		out.println("</br>");

		// a cada requisição de um certo browser a mesma variável é incrementada
		if (userAgent.equals(EDGE)) {
			contadorEdge++;
			contador = contadorEdge;
			nomeBrowser = "EDGE";
		} else if (userAgent.equals(CHROME)) {
			contadorChrome++;
			contador = contadorChrome;
			nomeBrowser = "CHROME";
		} else if (userAgent.equals(MOZILA)) {
			contadorMozila++;
			contador = contadorMozila;
			nomeBrowser = "MOZILA";
		}
		// numero de vezes que utilizou
		out.println("<p>O usuário utilizou o serviço na mesma seção " + contador + " vezes e com o browser "
				+ nomeBrowser + "</p>");
		out.println("</br>");

		// número de vezes que qualquer usuário usou o serviço desde que o
		// servidor foi iniciado
		out.println("<p>O número de vezes que qualquer usuário usou o serviço desde que o servidor foi iniciado é de :"
				+ contadorMaster + "vezes" + "</p>");
		out.println("</br>");
		dispatcher = getServletContext().getRequestDispatcher("/rodape.html");
		if (dispatcher != null) {
			dispatcher.include(request, response);
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// variavel de sessão
		session = request.getSession();
		Object objParam = session.getAttribute("contadorSessao");
		if (objParam != null) {
			contadorSessao = Integer.parseInt(objParam.toString());
			contadorSessao++;
			session.setAttribute("contadorSessao", contadorSessao);
		} else {
			contadorSessao = 0;
			contadorSessao++;
			session.setAttribute("contadorSessao", contadorSessao);
		}

		// servletContext
		servletContext = request.getServletContext();
		Object objAtt = servletContext.getAttribute("contadorMaster");
		if (objAtt != null) {
			contadorMaster = Integer.parseInt(objAtt.toString());
			contadorMaster++;
			servletContext.setAttribute("contadorMaster", contadorMaster);
		} else {
			contadorMaster = 0;
			contadorMaster++;
			servletContext.setAttribute("contadorMaster", contadorMaster);
		}
		// eu prevejo os 3 browsers mais importantes, EDGE, MOZILLA, CHROME...
		// se não for esses é o IE
		// eu não tenho outros browsers para ver o id na cabeçalho http
		String nomeBrowser = "Internet explorer";
		// parametro inicial
		int inicio = Integer.parseInt(request.getParameter("inicio"));
		// parametro final
		int fim = Integer.parseInt(request.getParameter("fim"));
		// somatório desde um número inicial até um número final fornecidos como
		// parâmetros de chamada
		int soma = inicio;
		for (int i = inicio + 1; i <= fim; i++) {
			soma += i;
		}

		// browser
		String userAgent = request.getHeader("User-Agent");

		// recebe o writer
		PrintWriter out = response.getWriter();

		// escreve o texto
		out.println("<html>");
		out.println("<body>");
		RequestDispatcher dispatcher;
		dispatcher = getServletContext().getRequestDispatcher("/cabecalho.html");
		if (dispatcher != null) {
			dispatcher.include(request, response);
		}
		out.println("</br>");
		out.println("<h1>Chamando com GET</h1>");
		out.println("</br>");
		// sessão
		out.println("<p>Você usou o serviço " + contadorSessao + " vezes nessa sessão</p>");
		out.println("</br>");
		// somatorio
		out.println("<p>O somatório de " + inicio + " até " + fim + " é: " + soma + "</p>");
		out.println("</br>");

		// a cada requisição de um certo browser a mesma variável é incrementada
		if (userAgent.equals(EDGE)) {
			contadorEdge++;
			contador = contadorEdge;
			nomeBrowser = "EDGE";
		} else if (userAgent.equals(CHROME)) {
			contadorChrome++;
			contador = contadorChrome;
			nomeBrowser = "CHROME";
		} else if (userAgent.equals(MOZILA)) {
			contadorMozila++;
			contador = contadorMozila;
			nomeBrowser = "MOZILA";
		}
		// numero de vezes que utilizou
		out.println("<p>O usuário utilizou o serviço na mesma seção " + contador + " vezes e com o browser "
				+ nomeBrowser + "</p>");
		out.println("</br>");

		// número de vezes que qualquer usuário usou o serviço desde que o
		// servidor foi iniciado
		out.println("<p>O número de vezes que qualquer usuário usou o serviço desde que o servidor foi iniciado é de :"
				+ contadorMaster + "vezes" + "</p>");
		out.println("</br>");
		dispatcher = getServletContext().getRequestDispatcher("/rodape.html");
		if (dispatcher != null) {
			dispatcher.include(request, response);
		}
		out.println("</body>");
		out.println("</html>");
	}

}
