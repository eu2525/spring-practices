package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;
	
	public JdbcContext(DataSource dataSource) {
		this.dataSource = dataSource;
		
	}
	
	public int insert(String sql, Object[] parameters) {
		return executeUpdate(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for(int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i + 1, parameters[i]);
				}
	
				return pstmt;
			}
		});
	}
	
	public int executeUpdate(StatementStrategy statementStrategy)  {
		int count = 0;	
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = statementStrategy.makeStatement(conn); 
		) {
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		return count;
	}
	
	
	public int update(String sql, Object parameter) {
		return 0;
	}
	
}
