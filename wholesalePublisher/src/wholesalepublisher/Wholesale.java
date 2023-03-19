package wholesalepublisher;

public class Wholesale {
	
	private int id;
	private String name;
	private int mobile;
	
	public Wholesale() {
		super();
	}

	public Wholesale(int id, String name, int mobile) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

}
