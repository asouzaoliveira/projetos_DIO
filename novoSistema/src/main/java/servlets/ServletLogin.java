package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;


@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"})/*MAPEAMENTO DE URL QUE VEM DA TELA*/
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
    
    public ServletLogin() {
        super();
        
    }

    /*O CONTROLLER SÃO AS SERVLETS */
	/*RECEBE OS DADOS PELA URL EM PARAMETROS*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao!=null && !acao.isEmpty() && acao.equalsIgnoreCase("Logout")) {
			
			request.getSession().invalidate();//invalida a sessão
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			
			redirecionar.forward(request, response);
			
		}else {
		
		
		doPost(request, response);
		}
	}

	/*RECEBE OS DADOS ENVIADOS POR UM FORMULARIO*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*Pegando os dados da tela*/
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
		
		
		/*Validação de login e senha*/
		if(login!=null && !login.isEmpty() && senha!=null && !senha.isEmpty()) {
			
			/*setando os dados ao objeto*/
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			/*validando o usuario*/
			if (daoLoginRepository.validarAutenticação(modelLogin)) {
				
				/*manter usuario logado*/
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
			
				if(url == null || url.equals("null")) {
					
					url = "principal/principal.jsp";
					
				}
				
				
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
				
			}else {
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe Login ou Senha Corretamente!");
				redirecionar.forward(request, response);
			}
			
			
			
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe Login ou Senha Corretamente!");
			redirecionar.forward(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
