
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Database.ManageAccount;
import beans.Accounts;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DataSource ds;
    Connection conn = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super(); 
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	System.out.println("init");
    	// TODO Auto-generated method stub
    	try {
			InitialContext ic = new InitialContext();
			Context env = (Context) ic.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/alien_x");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void callDispatcher (String name, HttpServletRequest request, HttpServletResponse response, boolean URLencode, boolean isJSP) throws ServletException, IOException {
    	System.out.println("dispatcher name:"+name);
    	if(isJSP && URLencode) {
    		
    	}
    	else if(isJSP) {
    		name = "/WEB-INF"+name;
    	}
    	
    	if (URLencode) {
    		System.out.println("url encoded!");
    		String path = response.encodeRedirectURL(getServletContext().getContextPath())+name;
			response.sendRedirect(path);
    	}
    	else {
    		request.getRequestDispatcher(name).forward(request, response);
    	}
    	
		manageDatabaseConnection(false, request,response);
		
		

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	System.out.println("doGet");

		manageDatabaseConnection(true, request,response);
		manageRedirects(request, response);
		//manageDatabaseConnection(false, request,response);

	}
		
	protected void manageDatabaseConnection(boolean open, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (open) {
			try {
				conn = ds.getConnection();
				System.out.println("connected!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			try {
				conn.close();
				System.out.println("close");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	protected void manageRedirects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("action");
		System.out.println("action parameter:"+name);
		
		if (name == null) {
			request.getSession().setAttribute("title", "index");
			callDispatcher("/index.jsp", request, response, false, false);
		}
		else if (name.equalsIgnoreCase("login")) {
			
			
			request.setAttribute("username", "");
			request.setAttribute("password", "");
			request.setAttribute("login_message", "");
			request.getSession().setAttribute("title", "login");
			callDispatcher("/login.jsp", request, response, false, true);
		}
		else if (name.equalsIgnoreCase("logs")) {
			
			
			//scope variables
			HttpSession session = request.getSession();
			ServletContext context = getServletContext();
			String logger = "This is the logger object.";
			session.setAttribute("title", "Logs List");
			session.setAttribute("logger", logger+Math.random()*10);
			context.setAttribute("logger", logger+Math.random()*10);
			request.setAttribute("logger", logger+Math.random()*10);
			//for maps
			Map <String, String> mapTest = new HashMap<String, String>();
			mapTest.put("technology","ios");
			mapTest.put("manufacturer","apple");
			mapTest.put("website","<a href='https://www.apple.com/ph/mac/'>Go to Apple</a>");
			System.out.println(mapTest.get("website"));
			request.setAttribute("map", mapTest);
			
			//for lists
			ArrayList<String> animals = new ArrayList<String>();
			animals.add("cat");
			animals.add("dog");
			animals.add("monkey");
			animals.add("kangaroo");
			session.setAttribute("animals", animals);
			
			//set cookies
			Cookie cook1 = new Cookie("code", ""+Math.random()*1231+1432);
			response.addCookie(cook1);
			
			
			/*
			 * String path =
			 * response.encodeRedirectURL(getServletContext().getContextPath())+
			 * "/WEB-INF/logs.jsp"; response.sendRedirect(path);
			 */
			
			//request.getRequestDispatcher("/WEB-INF/logs.jsp").forward(request, response);
			request.getRequestDispatcher("/WEB-INF/logs.jsp").include(request, response);
			//response.sendRedirect(getServletContext().getContextPath()+"/log_hack.jsp");
			
		}
		else if (name.equalsIgnoreCase("about")) {
			request.getSession().setAttribute("title", "about");
			callDispatcher("/about.jsp", request, response, false, true);
		}
		else if (name.equalsIgnoreCase("copyright")) {
			request.getSession().setAttribute("title", "copyright");
			System.out.println("from controller -> copyright");
			callDispatcher("/copyright.jsp", request, response, true, true);
		}
	}
		
	protected void managePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext con = getServletContext();
		String action_name = request.getParameter("action");
		String[] persistence_array = request.getParameterValues("persistence");

		/*
		 * System.out.println("persistence:"+persistence);
		 * System.out.println(persistence_array.length);
		 */
		if (action_name == null) {
			callDispatcher("/index.jsp", request, response, false, true);
		}
		else if (action_name.equalsIgnoreCase("submitlogin")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			Accounts u = new Accounts();
			u.setUsername(username);
			u.setPassword(password);
			//request.setAttribute("username", username);
			//request.setAttribute("password", password);
			
			
			//db check
			ManageAccount a = new ManageAccount(conn);
			
			try {
				if (u.validate_login()) {
					//set session
					
					
					if (a.login(username, password)) {
						boolean encoded = true;
						
						if(persistence_array!=null)
						for (String s : persistence_array) {
							System.out.println(s);
							
							if (s.equalsIgnoreCase("session")) {
								session.setAttribute("accounts", u);
								encoded = true;
							}
							//dummy object for servlet/application/server level
							if (s.equalsIgnoreCase("servlet")) {
								Accounts ax = u;
								con.setAttribute("dummy", ax);
								encoded = true;
							}
							//dummy object for cookie level
							if (s.equalsIgnoreCase("cookie")) {
								Cookie cook = new Cookie("sunny",u.getUsername());
								cook.setMaxAge(1500);
								response.addCookie(cook);
								encoded = true;
							}
							
						}
						
						
						//add new
						if (!a.exists("accounts", "username", username))
						a.newAccount("zzzzzz", "zzzzzz", "zzzzzz", "zzzzz", "zzzz");
						else {
							System.out.println("exists already!");
						}
						request.getSession().setAttribute("title", "dashboard");
						callDispatcher("/dashboard.jsp", request, response, encoded, true);
					}
					else {
						request.setAttribute("login_message", "username/password not found!");
						callDispatcher("/login.jsp", request, response, false, true);
					}
				}
				else {
					request.setAttribute("login_message", u.getMessage());
					callDispatcher("/login.jsp", request, response, false, true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("login_message", "username/password not found!");
				callDispatcher("/login.jsp", request, response, false, true);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		manageDatabaseConnection(true, request,response);
		managePost(request,response);
		
		
	}

}
