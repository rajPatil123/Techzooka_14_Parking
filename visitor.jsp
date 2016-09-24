<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
    String v_no=null;
    if(request.getParameter("enter")!=null)
    {
        v_no=request.getParameter("enter");
        String name_i=request.getParameter("name");
        int contact_i=Integer.parseInt(request.getParameter("contact"));
        int type_i=Integer.parseInt(request.getParameter("type")); 
        String city_i=request.getParameter("city");
        String reason_i=request.getParameter("r");
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehical_info","root","rajkumar");
        String query="insert into Visitors values('"+v_no+"',"+type_i+",'"+name_i+"',"+contact_i+",'"+city_i+"','"+reason_i+"')";
        PreparedStatement pstm=con.prepareStatement(query);
        pstm.executeUpdate();
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
       
        pstm=con.prepareStatement("insert into entry(vech_no,name,phone_no,time_date,type) values('"+v_no+"','"+name_i+"',"+contact_i+",'"+df.format(calobj.getTime())+"',"+type_i+")");
        pstm.executeUpdate();
        v_no="visitor";
        session.setAttribute("enter",v_no);
        response.sendRedirect("counter");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
                    
    }
        else
        {
            session=request.getSession();
            v_no=session.getAttribute("vno").toString();
        }
%>
<html>
<head>
<meta charset="utf-8">
<title>Desktop</title>
<meta name="generator" content="WYSIWYG Web Builder 11 Trial Version - http://www.wysiwygwebbuilder.com">
<link href="font-awesome.min.css" rel="stylesheet">
<link href="visitor.css" rel="stylesheet">
</head>
<body>
   <div id="space"><br></div>
   <div id="container">
      <div id="wb_Image1" style="position:absolute;left:0px;top:69px;width:970px;height:635px;z-index:11;">
         <img src="images/car2.jpg" id="Image1" alt="">
      </div>
      <div id="Layer1" style="position:absolute;text-align:left;left:0px;top:2px;width:968px;height:77px;z-index:12;">
      </div>
      <div id="wb_FontAwesomeIcon1" style="position:absolute;left:0px;top:8px;width:55px;height:31px;text-align:center;z-index:13;">
         <a href="./wb11_desktop#the_end"><div id="FontAwesomeIcon1"><i class="fa fa-globe">&nbsp;</i></div></a>
      </div>
      <div id="wb_PanelMenu1" style="position:absolute;left:910px;top:8px;width:60px;height:70px;z-index:14;">
         <a href="#PanelMenu1_markup" id="PanelMenu1">&nbsp;</a>
         <div id="PanelMenu1_markup">
            <ul>
               <li><a href="#"><i class="fa fa-home fa-fw">&nbsp;</i><span>Home</span></a></li>
               <li><a href="#"><i class="fa fa-user fa-fw">&nbsp;</i><span>About Me</span></a></li>
               <li><a href="#"><i class="fa fa-camera fa-fw">&nbsp;</i><span>Gallery</span></a></li>
               <li><a href="#"><i class="fa fa-pencil fa-fw">&nbsp;</i><span>Blog</span></a></li>
               <li><a href="#"><i class="fa fa-link fa-fw">&nbsp;</i><span>Links</span></a></li>
            </ul>
         </div>
      </div>
      <a href="http://www.wysiwygwebbuilder.com" target="_blank"><img src="images/builtwithwwb11.png" alt="WYSIWYG Web Builder" style="position:absolute;left:441px;top:2027px;border-width:0;z-index:250"></a>
      <div id="wb_Heading1" style="position:absolute;left:162px;top:17px;width:646px;height:98px;text-align:center;z-index:16;">
         <h1 id="Heading1">Parking Management System
         </h1>
      </div>
      <div id="w
           b_Form2" style="position:absolute;left:329px;top:209px;width:310px;height:399px;z-index:17;">
         <form name="visitorreg" action="visitor.jsp" id="Form2" onsubmit="return Validatevisitorreg(this)">
            <label for="" id="Label2" style="position:absolute;left:9px;top:14px;width:52px;height:18px;line-height:18px;z-index:0;">Name:</label>
            <input type="text" id="namebox" style="position:absolute;left:79px;top:14px;width:190px;height:18px;line-height:18px;z-index:1;" name="name" value="">
            <label for="" id="Label3" style="position:absolute;left:9px;top:136px;width:52px;height:18px;line-height:18px;z-index:2;">Contact</label>
            <input type="text" id="contbox" style="position:absolute;left:79px;top:135px;width:190px;height:18px;line-height:18px;z-index:3;" name="contact" value="">
            <input type="text" id="reasonbox" style="position:absolute;left:79px;top:54px;width:190px;height:59px;line-height:59px;z-index:4;" name="r" value="">
            <label for="" id="Label5" style="position:absolute;left:9px;top:54px;width:52px;height:18px;line-height:18px;z-index:5;">Reason:</label>
            <label for="Label5" id="Label6" style="position:absolute;left:19px;top:286px;width:52px;height:18px;line-height:18px;z-index:6;">Type</label>
            <select name="type" size="1" id="Combobox1" style="position:absolute;left:79px;top:286px;width:200px;height:28px;z-index:7;">
               <option value="2">Two Wheeler</option>
               <option selected="" value="4">Four Wheeler</option>
               <option value="3">Three Wheeler</option>
            </select>
            <input type="submit" id="Button2" name="enter" value= <%=v_no%> style="position:absolute;left:107px;top:345px;width:96px;height:25px;z-index:8;">
            <input type="text" id="Editbox1" style="position:absolute;left:79px;top:184px;width:190px;height:59px;line-height:59px;z-index:9;" name="city" value="">
            <label for="" id="Label1" style="position:absolute;left:9px;top:186px;width:52px;height:18px;line-height:18px;z-index:10;">Address</label>
         </form>
      </div>
   </div>
   <script src="jquery-1.11.3.min.js"></script>
   <script src="wb.panel.min.js"></script>
      <script src="visitor.js"></script>
</body>
</html>