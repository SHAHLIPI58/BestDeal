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


@WebServlet("/InventoryReport")
public class InventoryReport extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
       
       Utilities utility = new Utilities(request,pw);
        HttpSession session = request.getSession(true);
        MySqlDataStoreUtilities sqlUtil = new MySqlDataStoreUtilities();

        //getting productList
        HashMap<String,Product> productList = sqlUtil.getProductCatalog();

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        //link for report

        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Inventory Report</a></h2>");
        pw.print("<div class='entry'>");

        pw.print("<table  class='gridtable' style='width:100%'>");
        pw.print("<tr><td><a href='#section1'>Product List with available quantity</a></td></tr>");
        pw.print("<tr><td><a href='#section2'>Barchart with product name and total number of item available</a></td></tr>");
        pw.print("<tr><td><a href='#section3'>productList with currently onSale</a></td></tr>");
        pw.print("<tr><td><a href='#section4'>productList with currently Rebate</a></td></tr>");
        pw.print("</table>");
        //pw.println("<div id='chart_div'></div>");
        pw.println("</div>");
        pw.println("</div></div>");


        pw.print("<div><div id='content'><div class='post' id ='section1'>");
        //pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Inventory Report</a></h2>");
        pw.print("<br><b>&nbsp; &nbsp; &nbsp; Product List with available quantity </b><br><br>");
        pw.print("<div class='entry'>");

        pw.print("<table  class='gridtable' style='width:100%'>");
                    
                   
                    pw.print("<td>productName:</td>");
                    pw.print("<td>productPrice:</td>");
                    pw.print("<td>productQuantity:</td></tr>");
                    for (String prodID: productList.keySet()) 
                    {
                        Product prod = (Product)productList.get(prodID);
                        pw.print("<tr>");           
                        pw.print("<td> &nbsp;"+prod.getName()+"&nbsp;</td><td> &nbsp; &nbsp; &nbsp; $"+prod.getPrice()+"&nbsp;&nbsp; &nbsp;</td><td>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;"+prod.getQuantity()+"&nbsp;&nbsp; &nbsp;</td>");
                        pw.print("</tr>");
                        
                    }
                    pw.print("</table>");
        //pw.println("<div id='chart_div'></div>");
        pw.println("</div>");
        pw.println("</div></div></div>");

        //copy the same

       pw.print("<div id='content'><div class='post' id='section2'>");

        //pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
        pw.print("<div class='entry' >");

    
        pw.println("<div id='chart_div' style='width:700px; height:1000px'></div>");
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
                    pw.println("['Product Name', 'Total Items'],");

                Product pr = new Product();
                for(String prodID: productList.keySet()){
                   pr = (Product)productList.get(prodID);
                   String name = pr.getName();
                   int quantity = pr.getQuantity();
                   pw.println("[' " +name+ " ', "+quantity+ "],");
                }
                pw.println("]);");
                pw.println("var options = {");
                    pw.println("title: 'Product names and the total number of items available',");
                    pw.println("chartArea: {width: '65%', height: 950},");
                    pw.println("hAxis: {");
                    pw.println("title: 'Total number of products',");
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
                //pw.println("<div id='chart_div11' style='width:900px; height:1500px'></div>");
         //bar chart end

        // pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        // pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
       
       //onsale = "true"
        pw.print("<div ><div id='content'><div class='post' id ='section3'>");
        pw.print("<br><b>&nbsp; &nbsp; &nbsp; ProductList with currently onSale Products </b><br><br>");
        pw.print("<div class='entry'>");

        pw.print("<table  class='gridtable' style='width:100%'>");
                    
                   
                    pw.print("<td>productName:</td>");
                    pw.print("<td>productPrice:</td>");
                    pw.print("<td>productQuantity:</td></tr>");
                    for (String prodID: productList.keySet()) 
                    {
                        Product prod = (Product)productList.get(prodID);

                        if(prod.getProdOnSale().equalsIgnoreCase("true")){
                        pw.print("<tr>");           
                        pw.print("<td> &nbsp;"+prod.getName()+"&nbsp;</td><td> &nbsp; &nbsp; &nbsp; $"+prod.getPrice()+"&nbsp;&nbsp; &nbsp;</td><td>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;"+prod.getQuantity()+"&nbsp;&nbsp; &nbsp;</td>");
                        pw.print("</tr>");
                    }
                        
                    }
                    pw.print("</table>");
        //pw.println("<div id='chart_div'></div>");
        pw.println("</div>");
        pw.println("</div></div></div>");

        // rebate = "true"
        pw.print("<div><div id='content'><div class='post' id ='section4'>");
        pw.print("<br><b>&nbsp; &nbsp; &nbsp; ProductList with manufactured Rebate Products </b><br><br>");
        pw.print("<div class='entry'>");

        pw.print("<table  class='gridtable' style='width:100%'>");
                    
                   
                    pw.print("<td>productName:</td>");
                    pw.print("<td>productPrice:</td>");
                    pw.print("<td>productQuantity:</td></tr>");
                    for (String prodID: productList.keySet()) 
                    {
                        Product prod = (Product)productList.get(prodID);

                        if(prod.getManuRebate().equalsIgnoreCase("true")){
                        pw.print("<tr>");           
                        pw.print("<td> &nbsp;"+prod.getName()+"&nbsp;</td><td> &nbsp; &nbsp; &nbsp; $"+prod.getPrice()+"&nbsp;&nbsp; &nbsp;</td><td>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;"+prod.getQuantity()+"&nbsp;&nbsp; &nbsp;</td>");
                        pw.print("</tr>");
                    }
                        
                    }
                    pw.print("</table>");
        //pw.println("<div id='chart_div'></div>");
        pw.println("</div>");
        pw.println("</div></div></div>");


        utility.printHtml("Footer.html");

    }
    

}
