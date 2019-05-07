package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.UserPersistence;

@Service
public class dataBasePersistance {

	@Autowired
	UserPersistence up;

	private final String url = "jdbc:postgresql://ec2-54-225-95-183.compute-1.amazonaws.com:5432/d1eklfanov8b4e";
	private final String user = "lhwhsablrhjkzl";
	private final String password = "4ae800cc156641d730e12a8c0d5c76321dc49e24174bf119e16035c8a114cd34";

	public Connection connect() {
		Connection conn = null;
		try {

			System.out.println("crearla");
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("creada");
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public void agregarUsuarios(User user) throws dataBaseException {
		if (!(up.findByusername(user.getName()).size() == 0 && up.findByEmail(user.getEmail()).size() == 0)) {
			long id = 0;
			String SQL = "INSERT INTO users(username,email,pasword,profile,rating) " + "VALUES(?,?,?,?,?)";
			try (Connection conn = connect();
					PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getPassword());
				pstmt.setNull(4, 0);
				pstmt.setNull(5, 0);

				int affectedRows = pstmt.executeUpdate();
				// check the affected rows
				if (affectedRows > 0) {
					// get the ID back
					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						if (rs.next()) {
							id = rs.getLong(1);
						}
					} catch (SQLException ex) {
						System.out.println("");System.out.println("");System.out.println("");System.out.println("");
						System.out.println(ex.getMessage());
						System.out.println("");System.out.println("");System.out.println("");System.out.println("");
					}
				}
				
			} catch (SQLException ex) {
				System.out.println("");System.out.println("");System.out.println("");
				System.out.println(ex.getMessage());
				System.out.println("");System.out.println("");System.out.println("");
			}
			System.out.println(" id:" + id);
		}
		
	}

	public boolean consultarUsuarios(String userN, String pass) throws dataBaseException {

		boolean correcto = false;
		
		String SQL = "select exists(select 1 from users WHERE username=(?) and password = (?))";
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, userN);
			pstmt.setString(2, pass);

			correcto = pstmt.execute();
			// check the affected rows
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println(" si:" + correcto);
		return correcto;
	}


	public boolean updateRating(String r,String name){
		boolean cambiado=false;
		String SQL = "update users set rating=(?) where username=(?)";
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			


			pstmt.setString(1, r);
			pstmt.setString(2, name);

			cambiado = pstmt.execute();
			// check the affected rows
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println(" si:" + cambiado);
		

		return cambiado;
	}

	
}
