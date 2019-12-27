import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TvList")

public class TvList extends HttpServlet {

	/* Tv Page Displays all the tvs and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Tv> hm = new HashMap<String, Tv>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.tvs);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("panasonic"))
		   {
			 for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("panasonic"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Panasonic";
		   }
		   else if(CategoryName.equals("lg"))
		    {
			for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("lg"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "LG";
			}
		   else if(CategoryName.equals("sony"))
		    {
			for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("sony"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Sony";
			}
			else if(CategoryName.equals("samsung"))
			{
				for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Samsung";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the Tv and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

// 		Utilities utility = new Utilities(request,pw);
// 		utility.printHtml("Header.html");


// 		pw.print("<div id='body'>");
// 		pw.print("<div style='display: inline'>");
//         pw.print("<section id='content'>");
//         pw.print("<div><a style='font-size: 24px;'>"+name+" Tvs</a></div>");
// 		 int i = 1; int size= hm.size();
// 		for(Map.Entry<String, Tv> entry : hm.entrySet())
// 		{
// 			Tv tv = entry.getValue();
// 			if(i%3==1) pw.print("<table style='width:50%; display: inline; float: auto;'>");
// 		    pw.print("<tr><td><div style='width:300px; text-align:center;'><h3>"+tv.getName()+
// 		    	     "</h3><strong>$"+tv.getPrice()+" </strong></div></td></tr>");
// 		    pw.print("<tr><td><div style='width:300px'><ul style='list-style-type:none;'>"+
// 	                 "<li id='item'>"+
// 	                 "<img style='width:220px; height:150px;' src='images/tvs/"+tv.getImage()+"' alt='' /></li>"+
//                             		"<li>"+
//                             		 "</li>"+
//                             		 "</br>"+
                 
//                             		    "<li>"+
//                             		    "<div style='text-align:center'>"+
//                             		    "warranty: </br> "+
//                             		         "<select>"+
//                             		         "<option value='no'>No</option>"+
// 											  "<option value='yes'>Yes</option>"+
// 										"</div>"+
// 											"</select>"+
//                             		    "</li>"+

//                             		    "<li><form method='post' action='Cart'>"+
// 	                            			"<input type='hidden' name='name' value="+entry.getKey()+">"+
// 	                            			"<input type='hidden' name='type' value='tvs'>"+
// 	                            			"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
// 	                            			"<input type='hidden' name='access' value=''>"+
// 	                            			"<div style='text-align:center'>"+
// 	                            			"<input type='submit' class='btnbuy' value='Buy Now'>"+
// 	                            			// "<input  style='background-color: #779AC0;'' class='btn btn-primary' type='button' value='Input'>"+
// 	                            			"</div>"+
//                             		     "</form>"+
//                             		 "</li>"+

 

//                             		    // "<li><form method='post' action='WriteReview'><input type='hidden' name='name' value='xbox360'>"+
//                             		    // 	"<input type='hidden' name='type' value='tvs'>"+
//                             		    // 	"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
//                             		    // 	"<input type='hidden' name='access' value=''>"+
//                             		    // 	"<div style='text-align:center'>"+
//                             		    // 	"<input type='submit' value='WriteReview' class='btnreview'>"+
//                             		    // 	"</div>"+
//                             		    //     "</form>"+
//                             		    // "</li>"+
//                             		"</ul>"+
//                             	"</div>"+
//                             "</td>"+
//                             "</div>"+
//                         "</td>"+
//                     "</tr>");
// 		    pw.print("</table>");
// 		    if(i%3==0 || i == size) 
// 			i++;
		    
//         }
// 		    pw.print("</section>");

                    
//         utility.printHtml("LeftNavigationBar.html");
// 		utility.printHtml("Footer.html");
		
// 	}
// }



		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		//pw.print("<p>"+hm+"</p>"); //value in hashmap
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Laptops</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Tv> entry : hm.entrySet())
		{
			Tv tv = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+tv.getName()+"</h3>");
			pw.print("<strong>$"+tv.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/tvs/"+tv.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
