import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try{
		
		
		getConnection();
		
		
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from  Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		
		 // Random random = new Random();
		 // Boolean onsale_bool = random.nextBoolean();
		 // String onsale=Boolean.toString(onsale_bool);

		 //  Random random1 = new Random();
		 // Boolean rebate_bool = random1.nextBoolean();
		 // String rebate=Boolean.toString(rebate_bool);
				
	
		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,onsale,rebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?);";

		// String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
		// "VALUES (?,?,?,?,?,?,?,?);";

		//change the console,game,tablet field here
		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{   
			String name = "accessories";
	        Accessory acc = entry.getValue();

		    Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			 Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,acc.getId());
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			pst.setString(6,acc.getRetailer());
			pst.setString(7,acc.getCondition());
			pst.setDouble(8,acc.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);

			pst.executeUpdate();
			
			
		}
		
		for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
		{   
	        Console con = entry.getValue();
			String name = "consoles";
			
			Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);
						
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,con.getId());
			pst.setString(3,con.getName());
			pst.setDouble(4,con.getPrice());
			pst.setString(5,con.getImage());
			pst.setString(6,con.getRetailer());
			pst.setString(7,con.getCondition());
			pst.setDouble(8,con.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);
			
			pst.executeUpdate();
			try{
			HashMap<String,String> acc = con.getAccessories();
			String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			"VALUES (?,?);";
			for(Map.Entry<String,String> accentry : acc.entrySet())
			{
				PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
				pstacc.setString(1,con.getId());
				pstacc.setString(2,accentry.getValue());
				pstacc.executeUpdate();
			}
			}catch(Exception et){
				et.printStackTrace();
			}
		}
		for(Map.Entry<String,Game> entry : SaxParserDataStore.games.entrySet())
		{   
			String name = "games";
	        Game game = entry.getValue();
	        Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,game.getId());
			pst.setString(3,game.getName());
			pst.setDouble(4,game.getPrice());
			pst.setString(5,game.getImage());
			pst.setString(6,game.getRetailer());
			pst.setString(7,game.getCondition());
			pst.setDouble(8,game.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);
			
			pst.executeUpdate();

		
			
			
		}
		for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
		{   
			String name = "tablets";
	        Tablet tablet = entry.getValue();
	        Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,tablet.getId());
			pst.setString(3,tablet.getName());
			pst.setDouble(4,tablet.getPrice());
			pst.setString(5,tablet.getImage());
			pst.setString(6,tablet.getRetailer());
			pst.setString(7,tablet.getCondition());
			pst.setDouble(8,tablet.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);
			
			pst.executeUpdate();
			
			
		}

		//insert phones

		for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
		{   
			String name = "phones";
	        Phone phone = entry.getValue();
	        Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);

	        System.out.println("MySqlDataStoreUtilities-----> phones insertion"+phone.getId());
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,phone.getId());
			pst.setString(3,phone.getName());
			pst.setDouble(4,phone.getPrice());
			pst.setString(5,phone.getImage());
			pst.setString(6,phone.getRetailer());
			pst.setString(7,phone.getCondition());
			pst.setDouble(8,phone.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);
			
			pst.executeUpdate();
			
			
		}


		//insert Laptop


		for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
		{   
			String name = "laptops";
	        Laptop laptop = entry.getValue();
	        Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);

	        System.out.println("MySqlDataStoreUtilities-----> laptops insertion"+laptop.getId());
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,laptop.getId());
			pst.setString(3,laptop.getName());
			pst.setDouble(4,laptop.getPrice());
			pst.setString(5,laptop.getImage());
			pst.setString(6,laptop.getRetailer());
			pst.setString(7,laptop.getCondition());
			pst.setDouble(8,laptop.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);
			
			pst.executeUpdate();
			
			
		}

		//insert Tv


		for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
		{   
			String name = "tvs";
	        Tv tv = entry.getValue();
	        Random r = new Random();
			int low = 10;
			int high = 100;
			int result = r.nextInt(high-low) + low;

			Random random = new Random();
			 Boolean onsale_bool = random.nextBoolean();
			 String onsale=Boolean.toString(onsale_bool);

			 Random random1 = new Random();
			 Boolean rebate_bool = random1.nextBoolean();
			 String rebate=Boolean.toString(rebate_bool);

	        System.out.println("MySqlDataStoreUtilities-----> tvs insertion"+tv.getId());
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,tv.getId());
			pst.setString(3,tv.getName());
			pst.setDouble(4,tv.getPrice());
			pst.setString(5,tv.getImage());
			pst.setString(6,tv.getRetailer());
			pst.setString(7,tv.getCondition());
			pst.setDouble(8,tv.getDiscount());
			pst.setInt(9,result);
			pst.setString(10,onsale);
			pst.setString(11,rebate);
			
			pst.executeUpdate();
			System.out.println("Tv reflecte");
			
			
		}
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
} 

public static HashMap<String,Console> getConsoles()
{	
	HashMap<String,Console> hm=new HashMap<String,Console>();
	try 
	{
		getConnection();
		
		String selectConsole="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		pst.setString(1,"consoles");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Console con = new Console(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
				String selectaccessory = "Select * from Product_accessories where productName=?";
				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
				pstacc.setString(1,rs.getString("Id"));
				ResultSet rsacc = pstacc.executeQuery();
				
				HashMap<String,String> acchashmap = new HashMap<String,String>();
				while(rsacc.next())
				{    
					if (rsacc.getString("accessoriesName") != null){
					
					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
					 con.setAccessories(acchashmap);
					}
					 
				}					
				}catch(Exception e)
				{
						e.printStackTrace();
				}
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Tablet> getTablets()
{	
	HashMap<String,Tablet> hm=new HashMap<String,Tablet>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"tablets");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tablet tab = new Tablet(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tab);
				tab.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Game> getGames()
{	
	HashMap<String,Game> hm=new HashMap<String,Game>();
	try 
	{
		getConnection();
		
		String selectGame="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectGame);
		pst.setString(1,"games");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Game game = new Game(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), game);
				game.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//getLaptops()

public static HashMap<String,Laptop> getLaptops()
{	
	HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
	try 
	{
		getConnection();
		
		String selectLaptop="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectLaptop);
		pst.setString(1,"laptops");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Laptop laptop = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), laptop);
				laptop.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

//getTvs()

public static HashMap<String,Tv> getTvs()
{	
	HashMap<String,Tv> hm=new HashMap<String,Tv>();
	try 
	{
		getConnection();
		
		String selectTv="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTv);
		pst.setString(1,"tvs");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tv tv = new Tv(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tv);
				tv.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//getPhones()

public static HashMap<String,Phone> getPhones()
{	
	HashMap<String,Phone> hm=new HashMap<String,Phone>();
	try 
	{
		getConnection();
		
		String selectPhone="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectPhone);
		pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Phone phone= new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), phone);
				phone.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		getConnection();
		
		String selectAcc="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
		"VALUES (?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;
		
		   //mnanage dropdown here....
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setString(7,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}

public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		System.out.println("deleteOrder------------------------------->");
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		System.out.println(pst);
		int i = pst.executeUpdate();
		//if number of row affected greater than 0 then increase product quantity by one..
		if(i>0){
			System.out.println("deleteOrder ---------> comes for next query");

		    PreparedStatement ps = conn.prepareStatement(
		      "UPDATE productdetails " +
		                   "SET quantity = quantity + 1 WHERE productName =?");

		    // set the preparedstatement parameters
		    ps.setString(1,orderName);
		    
            System.out.println(ps);
		    // call executeUpdate to execute our sql update statement
		    ps.executeUpdate();
		    ps.close();
		}


		
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, String date_place)
{
	try
	{
	
		getConnection();
		
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo,date_place) "
		+ "VALUES (?,?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.setString(7,date_place);
		System.out.println(pst);
		pst.execute();
		//int i =pst.execute();
		//System.out.println("insert order execute VALUES..........."+i);
		//if(i == false){
			//System.out.println("insert order after query.....");

			PreparedStatement ps = conn.prepareStatement(
		      "UPDATE productdetails " +
		                   "SET quantity = quantity - 1 WHERE productName =?");

		    // set the preparedstatement parameters
		    ps.setString(1,orderName);
		    
            System.out.println(ps);
		    // call executeUpdate to execute our sql update statement
		    int i1 = ps.executeUpdate();
		    System.out.println("after insert and decrease quantity of productdetail table..no of rows affected:"+i1);
		    ps.close();
		//}
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		//String selectOrderQuery ="select * from customerorders order by OrderId";	
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getString("date_place"));
			//OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"));


			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//for inventory Report


public HashMap<String,Product> getProductCatalog()
 {
            Product prod;
            HashMap<String,Product> productList = new HashMap<String,Product>();
            try{
                getConnection();
                
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery("select * from productdetails order by productPrice desc");

                while(rs.next()){
                    prod = new Product(); //create product constructor
                    prod.setName(rs.getString("productName"));
                    prod.setPrice(rs.getDouble("productPrice"));
                    prod.setId(rs.getString("Id"));
					prod.setQuantity(rs.getInt("quantity"));
					prod.setProdOnSale(rs.getString("onsale"));
					prod.setManuRebate(rs.getString("rebate"));
					//put into hashmap productList {productId: Product_Object}
                    productList.put(prod.getId(),prod);  
                }
                rs.close();
                stmt.close();
            }catch(SQLException exception){
                exception.printStackTrace();
            }
            
            return productList;
       }



//get total number of item sold + revenue for particular item

  public ArrayList<Product> getProductSalesStat()
{
						 Product prod;
						 ArrayList<Product> prodSoldList = new ArrayList<Product>();
						 try{
								 getConnection();
								
								 Statement stmt=conn.createStatement();
								 ResultSet rs=stmt.executeQuery("select orderName,orderPrice,count(orderName) as items_sold,(orderPrice * count(orderName)) as TotalSales from customerorders group by orderName");
								 while(rs.next()){
										 prod = new Product();
										 prod.setName(rs.getString("orderName"));
										 prod.setPrice(rs.getDouble("orderPrice"));                                    
										 prod.setItems_Sold(rs.getInt("items_sold"));
										 prod.setTotal_Sales(rs.getDouble("TotalSales"));
										 prodSoldList.add(prod);
								 }
								 rs.close();
								 stmt.close();
						 }
						 catch(SQLException exception){
								 exception.printStackTrace();
						 }
						 return prodSoldList;
}


	 
 public ArrayList<Product> getDailySalesTransaction()
 {
 Product prod;
 ArrayList<Product> salesTransList = new ArrayList<Product>();
 try{
		 getConnection();
		 
		 Statement stmt=conn.createStatement();
		 ResultSet rs=stmt.executeQuery("select date_place,sum(orderPrice) as TotalSales from customerorders group by date_place");
		 while(rs.next()){
				 prod = new Product();
				 prod.setDate_Place(rs.getString("date_place"));
				 prod.setTotal_Sales(rs.getDouble("TotalSales"));
				 salesTransList.add(prod);
		 }
		 rs.close();
		 stmt.close();
 }
 catch(SQLException exception)
 {
		 exception.printStackTrace();
 }
 
return salesTransList;
}


}	