/**
 * 
 */
package ashu.android.poc.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * @author Ashok Kumar Jha
 *
 */
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5257932571811845100L;

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      if an I/O error occurs
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json = new JSONObject();
		Enumeration<?> paramNames = request.getParameterNames();
		String params[] = new String[2];
		int i = 0;
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			params[i] = paramValues[0];
			i++;
		}
		String sql = "SELECT uname, password FROM users where uname=? and password=?";
		Connection con = DBConnectionHandler.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, params[0]);
			ps.setString(2, params[1]);
			ResultSet rs = ps.executeQuery();
			json.put("info", (rs.next()) ? "success" : "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
