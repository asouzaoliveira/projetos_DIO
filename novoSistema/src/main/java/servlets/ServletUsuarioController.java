package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOusuarioRepository;
import model.ModelLogin;


@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOusuarioRepository daOusuarioRepository = new DAOusuarioRepository();
   
    public ServletUsuarioController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		String acao = request.getParameter("acao");
		
		if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			
			String idUser = request.getParameter("id");
			daOusuarioRepository.deletarUser(idUser);
			request.setAttribute("msg", "Usuario Excluido Com Sucesso!");
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		    
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) { /*exemplo em ajax*/
			
			String idUser = request.getParameter("id");
			daOusuarioRepository.deletarUser(idUser);
			
			response.getWriter().write("Usuario Excluido Com Sucesso!");
		    
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserajax")) { /*exemplo em ajax*/
			
			String nomeBusca = request.getParameter("nomeBusca");
			List<ModelLogin> dadosJsonUser = daOusuarioRepository.consultaUsuarioList(nomeBusca);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String json  = mapper.writeValueAsString(dadosJsonUser);
			
			response.getWriter().write(json);
			
			
		}
		
		
		
		
		else {
			
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
		
	
		
		
		}catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String msg = "Usuario Cadastrado com Sucesso!";
		
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id !=null && !id.isEmpty()? Long.parseLong(id): null );
	    modelLogin.setNome(nome);
	    modelLogin.setEmail(email);
	    modelLogin.setLogin(login);
	    modelLogin.setSenha(senha);
	    
	    if(daOusuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
	    	
	    	msg = "Usuario Duplicado, Informe Outro Login!";
	    	
	    	
	    }else {
	    	
	    	if(modelLogin.isNovo()) {
	    		
	    		msg="Gravado Com Sucesso!";
	    		
	    	}else {
	    		msg="Atualizado Com Sucesso!";
	    		
	    	}
	    	
	    	 modelLogin = daOusuarioRepository.gravarUsuario(modelLogin);
	    	
	    }
	    	
		request.setAttribute("msg", msg);
	    RequestDispatcher redireciona = request.getRequestDispatcher("principal/usuario.jsp");
	    request.setAttribute("modelLogin", modelLogin);
	    redireciona.forward(request, response);
	    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
