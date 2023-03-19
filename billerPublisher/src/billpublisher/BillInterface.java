package billpublisher;

public interface BillInterface {
	
	public void createBill();
	public void searchBill();
	public void getAllBills();
	public void deleteBill();
	public double getPriceUsingItemId(int id);
	public String getEmployeeNameUsingId(int id);

}
