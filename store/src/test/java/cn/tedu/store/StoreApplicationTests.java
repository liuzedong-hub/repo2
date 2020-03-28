package cn.tedu.store;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {
	
	@Autowired
	DataSource dataSource;
	

	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	public void getConnection() throws SQLException {
		Connection conn=dataSource.getConnection();
		System.err.println(conn);
		hashCode();
	}
	
	@Test
	public void getConnectiofdn() throws SQLException {
		Object bo=new Object();
		System.out.println(bo.toString());
		System.out.println(bo.hashCode());
			String str1 = "OK";
			StringBuffer str2 = new StringBuffer(str1);
			String str3 = new String(str1);
			StringBuilder str4 = new StringBuilder(str1);
			System.out.println(str1.toString());
			System.out.println(str2.hashCode());
			System.out.println(str3.hashCode());
			System.out.println(str4.hashCode());
		
	}


}
