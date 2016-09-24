/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
 
/**
 *
 * @author rajkumar
 */
public class check extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
         PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehical_info","root","rajkumar");
            String v_no=request.getParameter("vno");
            
            PreparedStatement pstm=con.prepareStatement("select Vech_NO,Emp_ID,Type from empolyee where Vech_No like '"+v_no+"'");
            ResultSet rs=pstm.executeQuery();
            System.out.println("test");
                if (!rs.next() ) {
                        session.setAttribute("vno",v_no);
                    response.sendRedirect("visitor.jsp");
                }
                
                if(rs.getString("Vech_NO").equals(v_no))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
       
                    Calendar calobj = Calendar.getInstance();
                    String e_id=rs.getString("Emp_ID");
                    int type_i=rs.getInt("Type");
                    pstm=con.prepareStatement("insert into entry values('"+v_no+"','"+e_id+"','"+df.format(calobj.getTime())+"',"+type_i+")");
                    pstm.executeUpdate();
                    session.setAttribute("vno",v_no);
                    response.sendRedirect("counter");
                   
                }
                
            
       }
       catch(ClassNotFoundException | SQLException | IOException e){
           System.out.println(e);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
