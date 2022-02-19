import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import javax.swing.SwingUtilities;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class CSVtoExcel {
	
	static String CSV_PATH;
	static String BOX_PATH;

	public static void read() throws IOException, InterruptedException {

		ArrayList<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(new FileInputStream(CSV_PATH))) {

			List<String> header = Arrays.asList(scanner.nextLine().split(";"));
			records.add(Arrays.asList(scanner.nextLine().split(";")));
			int occurrences = 0;
			String customerName = records.get(0).get(0);

			while (scanner.hasNextLine()) {
				String[] values = scanner.nextLine().split(";");

				if (values[0].equals(customerName)) {
					records.add(Arrays.asList(values));
					occurrences++;
				} else {

					write(records, occurrences, customerName, header);
					records = new ArrayList<>();
					customerName = values[0];
					occurrences = 0;
				}
			}

			write(records, occurrences, customerName, header);

		}

	}

	private static void write(ArrayList<List<String>> array, Integer occurrences, String name, List<String> header)
			throws IOException {

		SXSSFWorkbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = workbook.createSheet();
		SXSSFRow headerRow = sheet.createRow(0);
		IntStream.range(0, header.size())
				.forEach(headerIndex -> headerRow.createCell(headerIndex).setCellValue(header.get(headerIndex)));
		IntStream.range(0, occurrences).forEach(rowIndex -> {
			SXSSFRow row = sheet.createRow(rowIndex + 1);
			IntStream.range(0, array.get(rowIndex).size()).forEach(
					columnIndex -> row.createCell(columnIndex).setCellValue(array.get(rowIndex).get(columnIndex)));
		});
		File theDir = new File(BOX_PATH + "/" + name);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		workbook.write(new FileOutputStream(BOX_PATH + "/" + name + "/test_" + name + ".xlsx"));
		array.subList(0, occurrences).clear();
		workbook.close();

	}

}