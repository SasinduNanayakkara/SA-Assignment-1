package itemspublisher;

public interface ItemInterface {
	
	public void createItem();
	public void displayAllItems();
	public void getItemDetailsById();
	public void getItemDetailsByName();
	public void deleteItem();
	public String getCompanyNameByID(int id);

}
