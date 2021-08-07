package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5432/webnovo?autoreconect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	
	static {/*quando n�o tiver instancia*/
		conectar();
	}
	
	
	
	
	public SingleConnectionBanco() {/*quando tiver uma instancia ira conectar*/
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if(connection==null) {
				Class.forName("org.postgresql.Driver");/*carrega os drivers de conex�o*/
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);/*n�o efetua altera��es no banco sem nosso comando*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();/*Mostrar falhas no console*/
		}
	}
	

}
