package guestbook.repository;

import org.springframework.stereotype.Repository;

import guestbook.repository.template.JdbcContext;

@Repository
public class GuestBookLogRepository {
	private JdbcContext jdbcContext;
	
	public GuestBookLogRepository(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}
	
	public int insert() {
		return jdbcContext.update("insert into guestbook_log values(current_date() , 1)");
	}
	
	public int update() {
		return jdbcContext.update("update guestbook_log set count = count + 1 where date = current_date()");
	}
	
	public int update(String regDate) {
		return jdbcContext.update("update guestbook_log set count = count - 1 where date_format(date, '%Y-%m-%d') = ? ", regDate);
	}
	
}
