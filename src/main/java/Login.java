
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        request.setAttribute("user", user);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServletTest", "root", "ttn");
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Vaibhav where username='"+user+"'and password='"+pass+"'");
if(rs.next()) {


    HttpSession session=request.getSession();
    session.setAttribute("user",user);

    response.sendRedirect("blog.jsp");


}
     else {
        out.println("The login credentials are invalid");

    }
            rs.close();

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}