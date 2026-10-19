import java.text.DecimalFormat;

public class Sale {
	
	int id;
	String employeeName;
	String region;
	double amount;


	public Sale(int id, String employeeName, String region, double amount) {
		this.id = id;
		this.employeeName = employeeName;
		this.region = region;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getRegion() {
		return region;
	}

	public double getAmount() {
		return amount;
	}

	public String toString() {
		DecimalFormat f = new DecimalFormat("##.00");
		return id + " | " + employeeName + " | " + region + " | $" + f.format(amount);
	}
}
