
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet(urlPatterns = {"/login_page"})
public class login_page extends HttpServlet {

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String Username = request.getParameter("Username");
        // We are retrieving the data from the input type with the name "Username"
        String Password = request.getParameter("Password");
        // We are retrieving the data from the input type with the name "Password"
        
        //Whenever making use of databases, we must have a try and catch whereby the processing
        // happens under the try, errors of the processing are caught in the catch.
        try{
             Class.forName("com.mysql.jdbc.Driver"); //
             Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails","root","");
 
             String sql= "SELECT * from logindetails where Username='"+Username+"' and Password='"+Password+"'"; 
             PreparedStatement pst = connection.prepareStatement(sql);
 
              ResultSet rs=pst.executeQuery(); 
           
            if(rs.next()){
              out.println("Correct Credentials")  ;            
            }else{
            out.println("Invalid Credentials");
            } 
            } 
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            }   
    }
}
