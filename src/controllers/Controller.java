
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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
    
    protected void callDispatcher (String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.getRequestDispatcher(name).forward(request, response);
		manageDatabaseConnection(false, request,response);

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		
		if (name == null) {
			callDispatcher("/index.jsp", request, response);
		}
		else if (name.equalsIgnoreCase("login")) {
			
			
			request.setAttribute("username", "");
			request.setAttribute("password", "");
			request.setAttribute("login_message", "");
			
			callDispatcher("/login.jsp", request, response);
		}
		else if (name.equalsIgnoreCase("logs")) {
			//set cookies
			Cookie cook1 = new Cookie("code", ""+Math.random()*1231+1432);
			Cookie cook2 = new Cookie("code2", ""+Math.random()*1231+1432);
			response.addCookie(cook1);
			response.addCookie(cook2);
			String path = response.encodeRedirectURL(getServletContext().getContextPath())+"/logs.jsp";
			response.sendRedirect(path);
			
		}
		else if (name.equalsIgnoreCase("about")) {
			callDispatcher("/about.jsp", request, response);
		}
	}
		
	protected void managePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext con = getServletContext();
		String action_name = request.getParameter("action");
		if (action_name == null) {
			callDispatcher("/index.jsp", request, response);
		}
		else if (action_name.equalsIgnoreCase("submitlogin")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			Accounts u = new Accounts();
			u.setUsername(username);
			u.setPassword(password);
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			
			//db check
			ManageAccount a = new ManageAccount(conn);
			
			try {
				if (u.validate_login()) {
					//set session
					
					
					if (a.login(username, password)) {
						session.setAttribute("accounts", u);
						//add new
						if (!a.exists("accounts", "username", username))
						a.newAccount("zzzzzz", "zzzzzz", "zzzzzz", "zzzzz", "zzzz");
						else {
							System.out.println("exists already!");
						}
						callDispatcher("/dashboard.jsp", request, response);
					}
					else {
						request.setAttribute("login_message", "username/password not found!");
						callDispatcher("/login.jsp", request, response);
					}
				}
				else {
					request.setAttribute("login_message", u.getMessage());
					callDispatcher("/login.jsp", request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("login_message", "username/password not found!");
				callDispatcher("/login.jsp", request, response);
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
