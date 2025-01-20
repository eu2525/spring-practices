package guestbook.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import guestbook.repository.template.JdbcContext;
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
		return jdbcContext.query(
				"select id, name, contents, date_format(reg_date, '%Y-%m-%d %h:%i:%s') as regDate from guestbook order by reg_date desc",
				new BeanPropertyRowMapper<>(GuestBookVo.class));
	}

	public int insert(GuestBookVo vo) {
		return jdbcContext.update(
				"insert into guestbook values(null, ?, ?, now(), ?)", vo.getName(), vo.getPassword(), vo.getContents());
	}

	public int deleteByIdAndPassword(Long id, String password) {
		return jdbcContext.update(
				"delete from guestbook where id=? and password=?", id, password);
	}


	public GuestBookVo findById(Long id) {
		return jdbcContext.queryForObject(
				"select id, name, contents, date_format(reg_date, '%Y-%m-%d %h:%i:%s') as regDate from guestbook where id = ? ", 
				new Object[] {id}, 
				new BeanPropertyRowMapper<>(GuestBookVo.class)
				);
	}	
}
