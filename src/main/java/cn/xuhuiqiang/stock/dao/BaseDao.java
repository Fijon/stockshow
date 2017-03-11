package cn.xuhuiqiang.stock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import cn.xuhuiqiang.stock.model.News;


public class BaseDao {
	
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://mysql.sql122.cdncenter.net/sq_Fijon?useUnicode=true&characterEncoding=utf-8";
	private static final String USER_NAME = "sq_Fijon";
	private static final String USER_PASSWORD = "Fijon920630";
	
	private static Connection conn;
	private static Statement stmt;
	private static Logger log = Logger.getLogger(BaseDao.class);

	private static void connectSqlite() {
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
			stmt = conn.createStatement();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		System.out.println("Opened database successfully");
	}

	public static boolean tableIsExist(String tableName) {
		connectSqlite();
		boolean result = false;
		if (tableName == null) {
			return false;
		}
		try {
			String sql =  "select * from information_schema.TABLES where table_schema ='sq_Fijon' and table_name = 'News';";
			ResultSet set = stmt.executeQuery(sql);
			if(set.next()){
				System.out.println(set.getConcurrency());
				result = true;
			}
							
			stmt.close();
			conn.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = false;
		}
		return result;
	}
 

 

	public static String isExists(String code) {
		connectSqlite();
		String result = null;
		String sql = "select POST_TIME from News where CODE ='" + code
				+ "' order by POST_TIME ASC";
		System.out.println(sql);
		try {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				result = resultSet.getString("POST_TIME");
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	public static List<News> checkNews() {
		connectSqlite();
		List<News> result = new LinkedList<News>();
		String sql = "select * from News ORDER BY CODE ASC, POST_TIME DESC";
		News tmp = null;
		try {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				tmp = new News();
				tmp.setCode(resultSet.getString("CODE"));
				tmp.setTitle(resultSet.getString("TITLE"));
				tmp.setContent(resultSet.getString("CONTENT"));
				tmp.setUrl(resultSet.getString("URL"));
				tmp.setPost_time(resultSet.getString("POST_TIME"));
				result.add(tmp);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		System.out.println(result.size());
		return result;
	}

	public static List<News> queryByCode(String code) {
		connectSqlite();
		List<News> result = new LinkedList<News>();
		String sql = "select * from News WHERE CODE='" + code
				+ "' ORDER BY POST_TIME DESC";
		if (Integer.valueOf(code) <= 0) {
			sql = "select * from News ORDER BY POST_TIME DESC";
		}

		News tmp = null;
		try {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				tmp = new News();
				tmp.setCode(resultSet.getString("CODE"));
				tmp.setTitle(resultSet.getString("TITLE"));
				tmp.setContent(resultSet.getString("CONTENT"));
				tmp.setUrl(resultSet.getString("URL"));
				tmp.setPost_time(resultSet.getString("POST_TIME"));
				result.add(tmp);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return result;
	}

	public static void main(String[] args) {

		/*
		 * // checkNews(); String one = isExists("000001"); String two =
		 * isExists("000002"); String three = isExists("000003"); String four =
		 * isExists("000004"); String five = isExists("000005"); String six =
		 * isExists("000006"); String seven = isExists("000007"); String egith =
		 * isExists("000008");
		 * 
		 * if (one.compareTo(three) > 0) { System.out.println("one more"); }
		 * System.out.println(one + "  " + three);
		 */
		// INSERT INTO News
		// VALUES('74d34710-b710-4d1a-a7e4-104c95674fe1','000001','长实地产1�?20日回�?697万股
		// 耗资35700万港�?','','http://finance.sina.com.cn/stock/hkstock/ggscyd/2017-01-23/doc-ifxzusws0073754.shtml?source=cj&dv=1','2017-01-23
		// 10:42:14','0');

		/*String updateTime = BaseDao.isExists("000001");
		System.out.println("updateTime: " + updateTime);

		List<News> list = BaseDao.queryByCode("000001");
		for (News item : list) {
			System.out.println("postTime: " + item.getPost_time());
			System.out.println(updateTime.compareTo(item.getPost_time()));
		}*/
		// System.out.println(UUID.randomUUID());

		BaseDao.tableIsExist("News");
		UUID uuid = UUID.randomUUID();
		String value = String.valueOf(uuid);
		System.out.println(value + "===" +  value.length());
		System.out.println(value.replace("-", "") + "==" + value.length());
	}

}
