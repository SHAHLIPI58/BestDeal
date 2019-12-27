import java.util.*;
import java.util.Map;



public class Product {
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private String type;
	private double discount;
	private int quantity;
    private String OnSale;
	private String Rebate;
	private double TotalSales;
	private int items_Sold;
	private String date_place;
	HashMap<String,String> accessories;
	public Product(String id,String name, double price, String image, String retailer,String condition,String type,double discount){
		this.id=id;
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.type=type;
		this.discount = discount;
     		this.accessories=new HashMap<String,String>();
	}

	//add additional constructor

	public Product(String id,String name, double price, String image, String retailer,String condition,int TotalSales,int items_Sold, String date_place){
		super();
		this.id=id;
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.TotalSales=TotalSales;
		this.items_Sold=items_Sold;
		this.date_place=date_place;
     	this.accessories=new HashMap<String,String>();
	}


	public Product(String id,String name, double price, String image, String retailer,String condition,String type,double discount,int quantity,
		String OnSale, String Rebate){
			super();
			this.id=id;
			this.name=name;
			this.price=price;
			this.image=image;
			this.retailer = retailer;
			this.condition=condition;
			this.type=type;
			this.discount = discount;
			this.quantity = quantity;
	        this.OnSale = OnSale;
	        this.Rebate = Rebate;
	     		this.accessories=new HashMap<String,String>();
		}
	
    HashMap<String,String> getAccessories() {
		return accessories;
		}

	public Product(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type =type;
	}


	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}


	//additional getter setter methods
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

  public String getProdOnSale() {
		return OnSale;
	}

	public void setProdOnSale(String OnSale) {
		this.OnSale = OnSale;
	}

  public String getManuRebate() {
    return Rebate;
  }

  public void setManuRebate(String Rebate) {
    this.Rebate = Rebate;
  }

  public int getItems_Sold() {
		return items_Sold;
	}

	public void setItems_Sold(int items_Sold) {
		this.items_Sold = items_Sold;
	}

	public double getTotal_Sales() {
		return TotalSales;
	}

	public void setTotal_Sales(double TotalSales) {
		this.TotalSales = TotalSales;
	}

	public String getDate_Place() {
		return date_place;
	}

	public void setDate_Place(String date_place) {
		this.date_place = date_place;
	}

	
}
