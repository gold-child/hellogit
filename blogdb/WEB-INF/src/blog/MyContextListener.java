package blog;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

//@WebListener
public class MyContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//�h���C�o�̉��
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while( drivers.hasMoreElements() ){
		    Driver driver = drivers.nextElement();
		    //System.out.println("unregister driver="+driver);
		    try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//�X���b�h�̒�~
		try {
			AbandonedConnectionCleanupThread.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

}
