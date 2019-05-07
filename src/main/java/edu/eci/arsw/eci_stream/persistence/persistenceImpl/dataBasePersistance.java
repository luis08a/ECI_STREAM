package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.User;
@Service
public class dataBasePersistance {

	private final String url = "jdbc:postgresql://ec2-184-73-216-48.compute-1.amazonaws.com:5432/d7svuls8keggss";
	private final String user = "almapleezqdgmv";
	private final String password = "bd35eb45aa7ddbc1fd5e4445d9830896931eefcc99cb12f1a6176b8048330e79";

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
		long id = 0;
		String SQL = "INSERT INTO usuarios(username,email,pasword) " + "VALUES(?,?,?)";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());

			int affectedRows = pstmt.executeUpdate();
			// check the affected rows
			if (affectedRows > 0) {
				// get the ID back
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						id = rs.getLong(1);
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(" id:" + id);
	}

	public boolean consultarUsuarios(String userN,String pass) throws dataBaseException {
		
		boolean correcto=false;;
		String SQL = "select exists(select 1 from usuarios WHERE username=(?) and password = (?))";
		try {Connection conn = connect();
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
}
