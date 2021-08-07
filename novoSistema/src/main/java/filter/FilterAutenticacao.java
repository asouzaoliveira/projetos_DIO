package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;


@WebFilter(urlPatterns = {"/principal/*"})/*Intercepta todas as requisi��es que vierem do projeto ou mapeamento*/
public class FilterAutenticacao implements Filter {

	private static Connection connection;
    
    public FilterAutenticacao() {
        
    }

	/*encerra o processo quando o servidor � paralizado, mata os processos de conex�o com o banco de dados*/
	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	/*intercpta as requisi��es e da as respostas no sistema, tudo que fizer no sistema ira passar por ele,
	 * autentica��o,
	 * commit, rollback
	 * redirecionament de paginas*/
	public void doFilter(HttpServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
	}

	/*inicia os processos ou recursos quando o servidor sobe o projeto, inicia a conex�o com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnectionBanco.getConnection();
		
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		try {
				
			HttpServletRequest req = (HttpServletRequest) arg0;
			HttpSession session = req.getSession();
			String usuariologado = (String)session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath();/*url que esta sendo acessada*/
			
			/*validade se esta logado se n�o voltar a tela de login*/
			
			if(usuariologado==null ||
					urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/*usuario n�o esta logado*/
				RequestDispatcher redireciona = req.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				req.setAttribute("mgs", "Favor realize o login");
				redireciona.forward(req, arg1);
				return;
				
				
				/*redireciona para o login*/
				
			}else {
				
				
				arg2.doFilter(req, arg1);
			}
			
			connection.commit();/*conex�o correta conectar ao banco.*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = arg0.getRequestDispatcher("erro.jsp");
			arg0.setAttribute("msg", e.getMessage());
			redirecionar.forward(arg0, arg1);
			
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}

}
