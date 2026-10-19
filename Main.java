import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("--- Sales Data Stream Analyzer ---\n\n");

		List<Sale> sales = new ArrayList<Sale>();

		// Read the CSV file
		String filePath = "sales.csv";
		File file = new File(filePath);
		Boolean firstLine = true;

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {

				// Skip header row
				if (firstLine) {
					firstLine = false;
					scanner.nextLine();
					continue;
				}

				// Read the line and parse values
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				int id = Integer.parseInt(parts[0]);
				double amount = Double.parseDouble(parts[3]);
				
				// Create a Sale object and add to list
				Sale sale = new Sale(id, parts[1], parts[2], amount);
				sales.add(sale);
			}

		}
		catch (Exception e) {
			System.out.println("An error occured while reading the file.");
			e.printStackTrace();
		}

		// Analyze the data using streams
		DecimalFormat moneyFormat = new DecimalFormat("##.00");

		System.out.println("All Sales: ");
		sales.stream().forEach(s -> System.out.println(s.toString()));

		System.out.println("\nSales Over $500: ");
		sales.stream().filter(s -> s.getAmount() > 500)
		.forEach(s -> System.out.println(s.toString()));

		System.out.print("\nNumber of sales over $1,000: ");
		long salesOver1000 = sales.stream().filter(s -> s.getAmount() > 1000)
		.count();
		System.out.println(salesOver1000);

		System.out.print("\nTotal Sales: ");
		double totalSales =  sales.stream().mapToDouble(s -> s.getAmount())
		.sum();
		System.out.println("$" + moneyFormat.format(totalSales));

		System.out.print("\nAverage Sale: ");
		double averageSale = sales.stream().mapToDouble(s -> s.getAmount())
		.average().getAsDouble();
		System.out.println("$" + moneyFormat.format(averageSale));

		System.out.println("\nHighest Sale: ");
		Sale highestSale = sales.stream()
		.max(Comparator.comparingDouble(Sale::getAmount)).orElseThrow();
		System.out.println(highestSale.toString());

		System.out.println("\nLowest Sale: ");
		Sale lowestSale = sales.stream()
		.min(Comparator.comparingDouble(Sale::getAmount)).orElseThrow();
		System.out.println(lowestSale.toString());

		System.out.println("\nSales from North Region: ");
		sales.stream().filter(s -> s.getRegion().equals("North"))
		.forEach(s -> System.out.println(s.toString()));

		System.out.println("\nEmployee Names: ");
		sales.stream().forEach(s -> System.out.println(s.getEmployeeName()));
	}

}