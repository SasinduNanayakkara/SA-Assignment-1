package itemspublisher;

public class Item {
	
	private int id;
	private String itemName;
	private String company;
	private Double price;
	
	public Item() {
		super();
	}

	public Item(int id, String itemName, String company, Double price) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.company = company;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
