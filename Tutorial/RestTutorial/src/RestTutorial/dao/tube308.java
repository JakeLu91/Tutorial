package RestTutorial.dao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONArray;


import RestTutorial.util.ToJSON;

public class tube308 {
	
	private static final Logger LOGGER = Logger.getLogger(tube308.class.getName());
		private ResultSet rs;
		private Connection connection;
		
		public JSONArray queryAllFromMembers() {
			
			ToJSON mtj = new ToJSON();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "910307");
				Statement statement = connection.createStatement();
				rs = statement.executeQuery("select * from members");
				//connection.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return mtj.toJSONArray(rs);
		}
		
		public JSONArray queryImageURLByID(String id) {
			ToJSON mtj = new ToJSON();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "910307");
				
				PreparedStatement query = connection.prepareStatement("select * from imageUrl where id=?");
				query.setString(1, id.toUpperCase());
				rs = query.executeQuery();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			LOGGER.log(Level.INFO, "");
			
			return mtj.toJSONArray(rs);
		}
		
		
		
		public int insertIntoMembers(int id,
									String fname,
									String lname,
									String email,
									String uname,
									String pass,
									String regdate) {
			PreparedStatement update = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "910307");
				
				update = connection.prepareStatement("insert into members (id, first_name, last_name, email, uname, pass, regdate)"
						+ "								 values(?, ?, ?, ?, ?, ?, ?)");
				
				
				update.setInt(1, id);
				update.setString(2, fname);
				update.setString(3, lname);
				update.setString(4, email);
				update.setString(5, uname);
				update.setString(6, pass);
				update.setString(7, regdate);
				update.executeUpdate();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				return 500;
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			return 200;
		}

		public int updateEmailForMember(String email, String uname) throws Exception {
			PreparedStatement update = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "910307");
				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select id from members where uname='"+ uname + "'");
				rs.next();
				int id = rs.getInt(1);
				update = connection.prepareStatement("update members set email=? where id=?");
				update.setString(1, email);
				update.setInt(2, id);
				update.executeUpdate();
			
			
			} catch (Exception e) {
				e.printStackTrace();
				return 500;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}

			return 200;
		}
 		
		
		public int deleteMember(int id) throws Exception {
			PreparedStatement update = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "910307");
			
				update = connection.prepareStatement("delete from members where id=?");
				update.setInt(1, id);
				
				update.executeUpdate();
			
			
			} catch (Exception e) {
				e.printStackTrace();
				return 500;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
			
			
			return 200;
		}
		
		public ResultSet getRS() {
			return rs;
		}
		
		public void closeConnection() {
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	
}
