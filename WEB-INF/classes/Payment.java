import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String msg = "good";
		String Customername= "";
		HttpSession session = request.getSession(true);

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		if(session.getAttribute("usertype").equals("retailer")){
			Customername =request.getParameter("customername");
			try{
				HashMap<String,User> hm=new HashMap<String,User>();
				hm=MySqlDataStoreUtilities.selectUser();
				if(hm.containsKey(Customername)){
					if(hm.get(Customername).getUsertype().equals("customer")){
						msg ="good";
					}else {msg ="bad";}
						
				}else {msg ="bad";}
				
			}catch(Exception e)
			{
				
			}
		}


		String message=MySqlDataStoreUtilities.getConnection();
		if(message.equals("Successfull"))
		{
			if (msg.equals("good"))
			{
				//order date with simple format
                 Date date1 = new Date();  
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    String strDate= formatter.format(date1);  
			    String date_place = strDate;
			    System.out.println("payment.java------>"+strDate); 

				int orderId=utility.getOrderPaymentSize()+1;
				//iterate through each order

				for (OrderItem oi : utility.getCustomerOrders())
				{

					//set the parameter for each column and execute the prepared statement

					utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo,date_place,Customername);
				
				}

				//remove the order details from cart after processing
					
				OrdersHashMap.orders.remove(utility.username());
				System.out.println("payment.java : OrdersHashMap size"+ OrdersHashMap.orders.size());

				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Order</a>");
				pw.print("</h2><div class='entry'>");

		
				//order date
				long date = System.currentTimeMillis() + 14 * 24 * 3600 * 1000;
                Date newDate = new Date(date);

                
					
						pw.print("<h2>Your Order");
					pw.print("&nbsp&nbsp");  
						pw.print("is stored ");
					pw.print("<br>Your Order No is "+(orderId));
					pw.print("<br>Your Order Date is "+(newDate)+"</h2>");
			}else {pw.print("<h2>Customer does not exits</h2>");}		
		}
		else
		{   utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<h2>My Sql server is not up and running</h2>");
		
		}
		pw.print("</div></div></div>");		
		//utility.printHtml("Header.html");
		utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
