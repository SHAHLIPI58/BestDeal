//SalesReport

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
       
       Utilities utility = new Utilities(request,pw);
        HttpSession session = request.getSession(true);
        MySqlDataStoreUtilities sqlUtil = new MySqlDataStoreUtilities();

        //getting productList
        ArrayList<Product> prodSoldList = sqlUtil.getProductSalesStat();
        ArrayList<Product> dailySalesTran = sqlUtil.getDailySalesTransaction();

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        // sales report link
        pw.print("<div id='content'><div class='post'>");
          pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales Report</a></h2>");
        pw.print("<div class='entry'>");

        pw.print("<table  class='gridtable' style='width:100%'>");
                    
                   
                    pw.print("<tr><td><a href='#section5'>Product List With Sold Quantity And Total Sales</a></td></tr>");
                    pw.print("<tr><td><a href='#section6'>BarChart with Product names and total sales</a></td></tr>");
                    pw.print("<tr><td><a href='#section7'>Total daily sales transactions based on Sales date</a></td></tr>");
                   
                   
                   
                    pw.print("</table>");
        //pw.println("<div id='chart_div'></div>");
        pw.println("</div>");
        pw.println("</div></div>");

        pw.print("<div id='content'><div class='post' id='section5'>");
       // pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales Report</a></h2>");
             pw.print("<div class='entry'>");
        pw.print("<br><b> Product List With Sold Quantity And Total Sales </b><br><br>");
        pw.print("<table  class='gridtable' style='width:100%'>");
                    
                   
        pw.print("<td>productName:</td>");
        pw.print("<td>productPrice:</td>");
        pw.print("<td># Items sold:</td>");
        pw.print("<td>Total Sales:</td></tr>");
        for (Product prod : prodSoldList) 
        {
            pw.print("<tr>");           
            pw.print("<td> &nbsp;"+prod.getName()+"&nbsp;</td>");
            pw.print("<td> &nbsp; &nbsp; &nbsp; $"+prod.getPrice()+"&nbsp;&nbsp; &nbsp;</td>");
            pw.print("<td>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;"+prod.getItems_Sold()+"&nbsp;&nbsp; &nbsp;</td>");
            pw.print("<td> &nbsp; &nbsp; &nbsp; $"+prod.getTotal_Sales()+"&nbsp;&nbsp; &nbsp;</td>");
            pw.print("</tr>");
            
        }
        pw.print("</table>");
        //pw.println("<div id='chart_div'></div>");
        pw.println("</div>");
        pw.println("</div></div>");


        pw.print("<div id='content'><div class='post' id='section6'>");
        //pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
        pw.print("<div class='entry' >");

    
        pw.println("<div id='chart_div' style='width:1500px; height:1000px'></div>");
        pw.println("</div></div></div>");

        //end the table div

       //bar chart creation
       // pw.print("<article id='report2'><hr style='width: 95%'><h2 style='font-size: 25px;'> Bar Chart with the product names and the total number of items available </h2><hr style='width: 95%'>");
                pw.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
                pw.println("<script type='text/javascript'>");
                    pw.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
                    pw.println("google.charts.setOnLoadCallback(drawBasic);");
                    pw.println("function drawBasic() {");
                    pw.println("var data = google.visualization.arrayToDataTable([");
                    pw.println("['Product Name', 'Total Sales'],");

                //Product pr = new Product();
                for(Product pr : prodSoldList){
                   //pr = (Product)productList.get(prodID);
                   String name = pr.getName();
                   double sales = (pr.getTotal_Sales());
                   //int quantity = pr.getQuantity();
                   pw.println("[' " +name+ " ', "+sales+ "],");
                }
                pw.println("]);");
                pw.println("var options = {");
                    pw.println("title: 'product names and the total Sales',");
                    pw.println("chartArea: {width: '65%', height: 950},");
                    pw.println("hAxis: {");
                    pw.println("title: 'Total Sales',");
                    pw.println("minValue: 0");
                    pw.println("},");
                    pw.println("vAxis: {");
                    pw.println("title: 'Product Name'");
                    pw.println("}");
                    pw.println("};");
                    pw.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
                    pw.println("chart.draw(data, options);");
                    pw.println("}");
                    pw.println("</script>");



                    //3rd report:
                   
                    pw.print("<div id='content'><div class='post' id='section7'>");
                    pw.print("<br><b>&nbsp; &nbsp; &nbsp; Sales Date wise Revenue(sales Price) </b><br><br>"
                         +"<div class='entry'>");
                    pw.print("<table  class='gridtable' style='width:100%'>");
                    pw.print("<td>Sale Date</td>");
                    pw.print("<td>Total Sales</td></tr>");
                    for (Product prod : dailySalesTran) 
                    {
                        pw.print("<tr>");           
                        pw.print("<td> &nbsp;"+prod.getDate_Place()+"&nbsp;</td>");
                        pw.print("<td> &nbsp; &nbsp; &nbsp; $"+prod.getTotal_Sales()+"&nbsp;&nbsp; &nbsp;</td>");
                        pw.print("</tr>");
                        
                    }
                    pw.print("</table>");
                    //pw.println("<div id='chart_div'></div>");
                    pw.println("</div>");
                    pw.println("</div></div>");
                
       
 
}





}
