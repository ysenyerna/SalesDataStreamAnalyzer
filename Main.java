import java.io.File;
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


	}

}