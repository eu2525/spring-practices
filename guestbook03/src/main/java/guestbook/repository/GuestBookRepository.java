package guestbook.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import guestbook.repository.template.JdbcContext;
import guestbook.repository.template.StatementStrategy;
import guestbook.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	private JdbcContext jdbcContext;
	
	private DataSource dataSource;
	
	public GuestBookRepository(JdbcContext jdbcContext, DataSource dataSource) {
		this.jdbcContext = jdbcContext;
		this.dataSource = dataSource;
	}
	

	public List<GuestBookVo> findAll() {
		List<GuestBookVo> result = new ArrayList<>();
		
		try (
			Connection conn =  dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select id, name,contents, date_format(reg_date, '%Y-%m-%d') , reg_date from guestbook order by reg_date desc" );
			ResultSet rs = pstmt.executeQuery();
		){
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setId(id);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;		
	}


	public int insert(GuestBookVo vo) {
		

		return jdbcContext.insert("insert into guestbook values(null, ?, ?, now(), ?)",
		new Object[] {vo.getName(), vo.getPassword(),  vo.getContents()});

	}

	public int deleteByIdandPassword(Long id, String password) {
		
//		int count =0;
//		
//		try (
//			Connection conn =  dataSource.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where password = ? and id = ?");
//		){	
//			pstmt.setString(1, password);
//			pstmt.setLong(2, id);
//			count = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		
		return count;
	}
}
