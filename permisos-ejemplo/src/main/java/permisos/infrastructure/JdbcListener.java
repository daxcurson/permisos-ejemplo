package permisos.infrastructure;

//import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Enumeration;
//import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class JdbcListener implements ServletContextListener
{
 private static final Logger LOG = Logger.getLogger(JdbcListener.class);

 public void contextDestroyed(ServletContextEvent sce)
 {
	 cancelTimerThread();
    deregisterJdbcDriver();
 }

 public void contextInitialized(ServletContextEvent sce)
 {

 }

 private void cancelTimerThread()
 {
	 try
	 {
		// LOG.info(String.format("Abandoned connection thread shutdown..."));
		// AbandonedConnectionCleanupThread.shutdown();
		// LOG.info(String.format("Abandoned connection thread shutdown done"));
		/* LOG.info(String.format("Is there a timer thread to stop?"));
		 if (ConnectionImpl.class.getClassLoader() == getClass().getClassLoader()) 
		 {
			 LOG.info(String.format("Yes, there is, I'll stop it."));
			 Field f = ConnectionImpl.class.getDeclaredField("cancelTimer");
			 if(f!=null)
			 {
				 f.setAccessible(true);
				 Timer timer = (Timer) f.get(null);
				 if(timer!=null)
					 timer.cancel();
				 LOG.info(String.format("Timer stopped"));
			 }
			 else
				 LOG.info(String.format("Timer not found"));
		}*/
	 }
//	catch(IllegalAccessException e)
//	 {
//		 LOG.info(String.format("Error stopping thread"), e);
//	 }
//	catch(NoSuchFieldException e)
//	 {
//		 LOG.info(String.format("Error stopping thread"), e);
//	 }
	catch(SecurityException e)
	 {
		 LOG.info(String.format("Error stopping thread"), e);
	 }
	 catch(Exception e)
	 {
		 LOG.info(String.format("Unrecognized exception"),e);
	 }
 }
 private void deregisterJdbcDriver()
 {
    // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements())
    {
       Driver driver = drivers.nextElement();
       try
       {
          DriverManager.deregisterDriver(driver);
          LOG.info(String.format("Deregistering jdbc driver: %s", driver));
       }
       catch (SQLException e)
       {
          LOG.info(String.format("Error deregistering driver %s", driver), e);
       }
    }
 }

}
