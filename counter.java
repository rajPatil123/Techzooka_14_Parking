
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Counter", urlPatterns = {"/counter"})
public class counter extends HttpServlet {

        String str;
            int emptotal=0;
           int frstfloor[]=new int[2];  ///
           int empcounter[]=new int[3]; //3 floors
           int twowheeler=200;
           
        public counter()
                   {
                       frstfloor[0]=frstfloor[1]=50;
                       for(int i=0;i<3;i++)
                       {
                           empcounter[i]=100;
                       }
                       
                         
                   }
        
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           HttpSession session=request.getSession();
           String v_no=session.getAttribute("enter").toString();
           try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehical_info","root","rajkumar");
               String query="select * from visitor where Vech_NO is like '"+v_no+"'";
               PreparedStatement pstm=con.prepareStatement(query);
               ResultSet rs=pstm.executeQuery();
                pstm=con.prepareStatement("select * from visitor where name is like '*' and Vech_NO is like '"+v_no+"'");
                ResultSet rs1=pstm.executeQuery();
                if (!rs1.next())
            {
              if(rs.getInt("type")==2)//int value of type
                {
                    twowheeler-=1;
                }
                else 
                    if(empcounter[0]!=0)
                {
                    empcounter[0]-=1;
                }
                
                else if(empcounter[1]!=0)
                {
                    empcounter[1]-=1;
                }
                
                else if(empcounter[2]!=0)
                {
                    empcounter[0]-=1;
                }
                else
                {
                    //message empfull
                    if(frstfloor[0]!=0)
                    {
                        frstfloor[0]-=1;
                           //emptovisitors
                    }
                    else
                    {
                        //ghari ja
                    }
                    
                }
                emptotal=empcounter[0]+empcounter[1]+empcounter[2];
                if(emptotal<30)
                {
                    //message emp 10%
                }
            }
            else if(str.equals("visitor"))
            {
                if(frstfloor[0]!=0)
                {
                    frstfloor[0]-=1;
                }
                else
                {
                    //message visitor full
                }
                        
                
            }
            else if(str.equals("specials"))
            {
                if(frstfloor[1]!=0)
                {
                    frstfloor[1]-=1;
                }
                else
                {
                    //message special full
                }
                
            }
               out.println("Remaing solt");
           out.println("visitor"+frstfloor[0]);
           out.println("employe"+emptotal);
           
           }catch(Exception e){
           
           }
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