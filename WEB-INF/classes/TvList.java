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
		HashMap<String, Tv> alltvs = new HashMap<String, Tv>();


		try{
		     alltvs = MySqlDataStoreUtilities.getTvs();
		}
		catch(Exception e)
		{
			
		}
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Tv> hm = new HashMap<String, Tv>();
		if(CategoryName==null){
			hm.putAll(alltvs);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("panasonic"))
		   {
			 for(Map.Entry<String,Tv> entry : alltvs.entrySet())
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
			for(Map.Entry<String,Tv> entry : alltvs.entrySet())
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
			for(Map.Entry<String,Tv> entry : alltvs.entrySet())
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
				for(Map.Entry<String,Tv> entry : alltvs.entrySet())
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



		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		//pw.print("<p>"+hm+"</p>"); //value in hashmap
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Tvs</a>");
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
					"<input type='hidden' name='name1' value='"+tv.getName()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					//tv.getDiscount()
					"<input type='hidden' name='discount' value='"+tv.getDiscount()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='name1' value='"+tv.getName()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					//tv.getDiscount()
					"<input type='hidden' name='discount' value='"+tv.getDiscount()+"'>"+
					"<input type='hidden' name='price' value='"+tv.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='name1' value='"+tv.getName()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					//tv.getDiscount()
					"<input type='hidden' name='discount' value='"+tv.getDiscount()+"'>"+
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
