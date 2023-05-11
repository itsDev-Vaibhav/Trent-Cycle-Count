package com.tatatrent.cyclecount.utils;

public interface AppConstants {
	String API_VERSION = "/trent_api/v1/process_cycle_count";
//	String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	String DRIVER_CLASS = "oracle.jdbc.OracleDriver";
	String DB_PREFIX = "jdbc:oracle:thin:@";
	String DB_POSTFIX = "1521:THQAD";
	String QUERY = "INSERT INTO WMSTAGE.TL_I_WSRFIDCC_TEAMA (WHSEID, CCKEY, CCDETAILKEY, STORERKEY, SKU, ALTSKU, QTY, RFUSER, LOCATIONID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String ADMIN_ROLE = "ADMIN";
	String USER_ROLE = "USER";
}
