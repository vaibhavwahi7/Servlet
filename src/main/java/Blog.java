import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Blog extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String content = request.getParameter("content");

        HttpSession session = request.getSession();

        String user = (String) session.getAttribute("user");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ServletTest", "root", "ttn");
            PreparedStatement ps = con
                    .prepareStatement("update Vaibhav set blog_id=?, blog_content=? WHERE username='" + user + "'");

            ps.setString(1, id);
            ps.setString(2, content);
            int i = ps.executeUpdate();



            if (i != 0) {
                out.println("Your data has been stored in the database");
            } else {
                out.println("Your data could not be stored in the database");
            }

        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }
}
