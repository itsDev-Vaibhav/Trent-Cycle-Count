package com.tatatrent.cyclecount.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tatatrent.cyclecount.jdbc.config.JdbcDataSource;
import com.tatatrent.cyclecount.rest.dto.RequestDto;
import com.tatatrent.cyclecount.utils.AppConstants;

@Repository
public class CycleCountRepository {
	
	@Autowired
	private JdbcDataSource jdbcDataSource;
	
	final static Logger logger = LoggerFactory.getLogger(CycleCountRepository.class);

	
	public Integer saveCycleCount(RequestDto request) throws SQLException {
		logger.info("Inside CycleCountRepository.saveCycleCount()");
		try(Connection con = jdbcDataSource.dataSource().getConnection();) {
			con.setAutoCommit(false);
			try (PreparedStatement preparedstmt = con.prepareStatement(AppConstants.QUERY);) {
				preparedstmt.setString(1, request.getWhseId());
				preparedstmt.setString(2, request.getCcKey());
				preparedstmt.setString(3, request.getCcDetailKey());
				preparedstmt.setString(4, request.getStorerKey());
				preparedstmt.setString(5, request.getSku());
				preparedstmt.setString(6, request.getAltSku());
				preparedstmt.setInt(7, request.getQty());
				preparedstmt.setString(8, request.getUser());
//				logger.info("Returning from JdbcRepositoryImpl.getCount()");
				int execute = preparedstmt.executeUpdate();
				con.commit();
				logger.info("Returning from CycleCountRepository.saveCycleCount()");
				return execute;
			} catch (SQLException e) {
				logger.error("Something bad happend!" +e);
				con.rollback();
				con.setAutoCommit(true);
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error("Something bad happend!" +e1);
			e1.printStackTrace();
		}
		logger.info("Returning from CycleCountRepository.saveCycleCount()");
		return -1;	
	}


}
