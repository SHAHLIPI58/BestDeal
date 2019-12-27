import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		
		utility.storeProduct(name, type, maker, access);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
                //get price 
                double price_item = oi.getPrice();
				//discount rate
				String discount = request.getParameter("discount");
				//System.out.println("dicount: null ckecking...-->"+discount.equals(null));
				System.out.println("Cart.java------> discount--->:"+discount);

				try{
				if(!discount.equals(null) || !discount.equals(""))
				{
                    System.out.println("inside if"+ discount);
				    double d = Double.parseDouble(discount);
				    double discount1 =(100 - d)/100;
				    price_item = price_item *discount1;
			   } 
			   else{
			   		price_item = price_item;
			   }
			}catch(Exception e){
				System.out.println("Null Pointer Exception");
			}
			

			    float after_price = (float)price_item;
				pw.print("<tr>");
				//pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>");
				pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+after_price+"</td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+after_price+"'>");
				pw.print("</tr>");
				total = total + after_price;
				i++;
			}//for (OrderItem oi : utility.getCustomerOrders())  ending

			float total1 = (float) total; 
			pw.print("<input type='hidden' name='orderTotal' value='"+total1+"'>");	
			pw.print("<tr><th></th><th>Total</th><th>"+total1+"</th>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}
