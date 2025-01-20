package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import guestbook.repository.GuestBookLogRepository;
import guestbook.repository.GuestBookRepository;
import guestbook.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private PlatformTransactionManager 	transactionManager;
	
	@Autowired
	private DataSource dataSource;
	
	private final GuestBookRepository guestBookRepository;
	private final GuestBookLogRepository guestBookLogRepository;
	
	public GuestBookService(GuestBookRepository guestBookRepository, GuestBookLogRepository guestBookLogRepository) {
		this.guestBookLogRepository = guestBookLogRepository;
		this.guestBookRepository = guestBookRepository;
	}
	
	
	public List<GuestBookVo> getContentsList(){
		return guestBookRepository.findAll();
	}
	
	public void deleteContents(Long id, String password) {
		// Transaction:BEGIN //
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			GuestBookVo vo = guestBookRepository.findById(id);
			if(vo == null) {
				return;
			}
			
			int count = guestBookRepository.deleteByIdAndPassword(id, password);
	//		if (count == 1) {
			guestBookLogRepository.update(vo.getRegDate());
		//	}
			// Transaction:END(SUCCESS) //
			transactionManager.commit(txStatus);
			
		} catch(Throwable ex) {
			// Transaction:END(FAIL) //
			transactionManager.rollback(txStatus);			
		}
	}
	
	public void addContents(GuestBookVo vo) {
		// 트랜잭션 동기화(Connection)
		// 같은 트랜잭션에서 같은 Connection을 공유하기 때문에 ??? 여기서 동기화하기 이전에 왜 오류가 발생했는지 물어보기!!!
//		TransactionSynchronizationManager.initSynchronization();
//		Connection conn = DataSourceUtils.getConnection(dataSource);
		
		try {
			Connection conn = dataSource.getConnection();
			// 트랜잭션 : Begin //
			conn.setAutoCommit(false);		
		
			int count = guestBookLogRepository.update();
			if (count == 0) {
				guestBookLogRepository.insert();
			}
			guestBookRepository.insert(vo);
			// 트랜잭션 : END(SUCCESS) //			
			conn.commit();
		} catch (Throwable e) {
			// 트랜잭션 : END(Fail) //	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			throw new RuntimeException();
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}

	}
	
}
