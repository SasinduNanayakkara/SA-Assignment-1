package employeemanagerpublisher;

import java.util.Date;

public class Employee {
	
	private int id;
	private String name;
	private int mobile;
	private int age;
	private String nic;
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, int mobile, int age, String nic) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.age = age;
		this.nic = nic;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}
}
