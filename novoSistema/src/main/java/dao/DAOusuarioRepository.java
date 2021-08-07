package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOusuarioRepository {

	private  Connection connection;
	
	
	public DAOusuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		if(objeto.isNovo()) {/*grava novo usuario*/
		
		String sql = "INSERT INTO model_login(login, senha,nome, email) VALUES (?, ?, ?, ?); ";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		
		preparedSql.execute();
		connection.commit();
		
		}else {
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = "+objeto.getId()+" ;";
			
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			
			preparedSql.executeUpdate();
			
			connection.commit();
		}
		
		return this.consultaUsusario(objeto.getLogin());
	}
	
	public ModelLogin consultaUsusario(String login) throws Exception{ /*consultar usuario no banco de dados para atualizar o usuario*/
		
		ModelLogin modelLogin = new ModelLogin();
		String sgl = "SELECT * FROM model_login where upper(login) = upper('"+login+"');";
		
		PreparedStatement statement = connection.prepareStatement(sgl);
		
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next())/*se tem resultado*/ {
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			
		}
		
		return modelLogin;
		
	}
	
	public List<ModelLogin>consultaUsuarioList(String nome) throws Exception{/*consulta lista de usuario*/
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) like upper(?);";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%" + nome + "%");
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {/* percorrer as linhas da lista no banco de dados*/
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			retorno.add(modelLogin);
		}
		
		return retorno;
		
	}
	
	
	public boolean validarLogin(String login) throws Exception {/* validação de login*/
		
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"');";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();/* Para encontrar os resultados do sql*/ 
			return resultado.getBoolean("existe");
	
	}
	
	public void deletarUser(String idUser) throws Exception{/*deletar usuario*/
		
		String sql = "DELETE FROM model_login WHERE id = ?;";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setLong(1, Long.parseLong(idUser));
		
		preparedSql.executeUpdate();
		
		connection.commit();
	
		
	}
	
	
}
