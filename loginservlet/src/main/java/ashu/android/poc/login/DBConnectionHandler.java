/**
 * 
 */
package ashu.android.poc.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author USER
 *
 */
public class DBConnectionHandler {
	private static final String DBURL = "jdbc:mysql://localhost:3306/world";
	private static final String DBUSER = "root";
	private static final String DBPWD = "r00t";
	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
