

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
	//once the user clicks writereview button from products page he will be directed
 	//to write review page where he can provide reqview for item rating reviewtext	
	
public class WriteReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
                   
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Write a Review");
			response.sendRedirect("Login");
			return;
		}
		//actual product key
        String productname=request.getParameter("name");
        //product name	
        String productname1="";
        productname1=request.getParameter("name1");
        String producttype=request.getParameter("type");
        String producttype1 = producttype;
        String productmaker=request.getParameter("maker");
		String productprice=request.getParameter("price");
		String productdiscount = "";
		productdiscount =request.getParameter("discount");
			      
      // on filling the form and clicking submit button user will be directed to submit review page
        utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
                pw.print("<table class='gridtable'>");
		pw.print("<tr><td> Product Key: </td><td>");
		pw.print(productname);
		pw.print("<input type='hidden' name='productname' value='"+productname+"'>");
		pw.print("</td></tr>");
        pw.print("<tr><td> Product Name: </td><td>");
		pw.print(productname1);
		pw.print("<input type='hidden' name='productname1' value='"+productname1+"'>");
		pw.print("</td></tr>");
	    pw.print("<tr><td> Product Type:</td><td>");
        // pw.print(producttype);
        System.out.println ("WriteReviews.java---------->"+ producttype);
        if(producttype.equals("games"))
        {
            producttype1 = "watches";
        }else if(producttype.equals("tablets")){
        	 producttype1 = "headphones";
        }else if(producttype.equals("consoles")){
        	 producttype1 = "Voiceassistant";
        }
        pw.print(producttype1);
	    pw.print("<input type='hidden' name='producttype' value='"+producttype+"'>");
		pw.print("</td></tr>");
		pw.print("<tr><td> Product Price:</td><td>");
        pw.print(productprice);
	    pw.print("<input type='hidden' name='productprice' value='"+productprice+"'>");
		pw.print("</td></tr>");		
        pw.print("<tr><td> Product Maker: </td><td>");
        pw.print(productmaker);
		pw.print("<input type='hidden' name='productmaker' value='"+productmaker+"'>");
        pw.print("</td></tr>");
        pw.print("<tr><td> Product discount:</td><td>");
        pw.print(productdiscount);
        pw.print("<input type='hidden' name='discount' value='"+productdiscount+"'>");
		pw.print("</td></tr>");
        pw.write("<table>");

		pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
		pw.print("<td>");
		pw.print("<select name='reviewrating'>");
		pw.print("<option value='1' selected>1</option>");
		pw.print("<option value='2'>2</option>");
		pw.print("<option value='3'>3</option>");
		pw.print("<option value='4'>4</option>");
		pw.print("<option value='5'>5</option>");  
		pw.print("</td></tr>");
	
		pw.print("<tr>");
		pw.print("<td> Retailer Zip Code: </td>");
		pw.print("<td> <input type='text' name='zipcode'> </td>");
        pw.print("</tr>");		


		pw.print("<tr>");
		pw.print("<td> Retailer City: </td>");
		pw.print("<td> <input type='text' name='retailercity'> </td>");
        pw.print("</tr>");	


        //added new
        //state
        pw.print("<tr>");
		pw.print("<td> Retailer State: </td>");
		pw.print("<td> <input type='text' name='state'> </td>");
        pw.print("</tr>");	
        
        //age
        pw.print("<tr>");
		pw.print("<td> Retailer Age: </td>");
		pw.print("<td> <input type='text' name='age'> </td>");
        pw.print("</tr>");	
        
        //gender
        pw.print("<tr>");
		pw.print("<td> Retailer Gender: </td>");
		pw.print("<td>");
		 pw.print("<select name='gender'>");
		 pw.print("<option value='male' selected>Male</option>");
		 pw.print("<option value='female'>Female</option>");
		 pw.print("</select>"); 
		pw.print("</td>");
        pw.print("</tr>");	
        //Occupancy
        pw.print("<tr>");
		pw.print("<td> Retailer Occupancy: </td>");
		pw.print("<td> <input type='text' name='occupancy'> </td>");
        pw.print("</tr>");	
        //fetch the rebate from xxlist.java						

		pw.print("<tr>");
		pw.print("<td> Review Date: </td>");
		pw.print("<td> <input type='date' name='reviewdate'> </td>");
       pw.print("</tr>");				

		pw.print("<tr>");
		pw.print("<td> Review Text: </td>");
		pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
		pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");

   	    pw.print("</h2></div></div></div>");		
		utility.printHtml("Footer.html");
	                     	
                    }
              	catch(Exception e)
		{
                 System.out.println(e.getMessage());
		}  			
       
	 	
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
            }
}
