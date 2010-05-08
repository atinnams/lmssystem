package UTIL;



import UTIL.ILMSConfigMBean;
import javax.servlet.*;

public class LMSConfig implements ILMSConfigMBean {

        ServletContext context;
        
        public LMSConfig (ServletConfig config)
        {            
            context = config.getServletContext();
        }
	public String getTypeOfDatabase() {
		return context.getInitParameter("db_Type");
	}
	
	public String getHost() {
		return context.getInitParameter("host");
	}
	
	public String getPort() {
		return context.getInitParameter("port");
	}
	
	public String getDBName() {
		return context.getInitParameter("dbname");
	}
	
	public String getUserName() {
		return context.getInitParameter("username");
	}
	
	public String getPassword() {
		return context.getInitParameter("password");
	}

		
}
