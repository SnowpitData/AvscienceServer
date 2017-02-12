import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import avscience.server.*;
import java.io.*;
import avscience.wba.*;

public class PitListServlet extends HttpServlet
{
     DAO dao = new DAO();
     
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException 
     {
     	String action=request.getParameter("action");
     	if (action==null)action="";
     }
    
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException 
     {
        doGet(request, response);
     }
  
}