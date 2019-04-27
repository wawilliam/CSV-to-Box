import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 
 * CSV_BOX
 *
 * @author William Andersson
 * @version 18 apr. 2018
 *
 */
public class Write {



	void write() throws FileNotFoundException, IOException {

		int rowss = 0;
		int rows = 0;
		int kol = 0;

		System.out.println(Read.antalIndelningar);


		for(int indelning = 0; indelning < Read.antalIndelningar; indelning++) {

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();

			CellStyle cellStyle = workbook.createCellStyle();

			Font font = workbook.createFont();
			font.setBold(true);
			cellStyle.setFont(font);




			sheet.createFreezePane(0, 1);


			for(int r = 0; r < Read.antalRaderPerIndelning[indelning];	r++) {

				XSSFRow row;
				row = sheet.createRow(rows);


				if(!(indelning == 0)) {


					XSSFRow row0 = sheet.createRow(0);


					for(int i = 0; i < Read.antalKolummner - Read.startKolummn; i++ ) {


						Cell cell = row0.createCell(i);
						cell.setCellValue(Read.array[0] [Read.startKolummn+i] == null ? " " : Read.array[0] [Read.startKolummn+i]);
						cell.setCellStyle(cellStyle);

					}

				}




				for(int k = Read.startKolummn; k < Read.antalKolummner; k++ ) {




					Cell cell =	row.createCell(kol, CellType.STRING);

					if(r == 0 && indelning == 0) {
						cell.setCellStyle(cellStyle);
					}



					cell.setCellValue(Read.array[rowss] [k] == null ? " " : Read.array[rowss] [k]);


					kol++;
				}


				kol = 0;
				rows++;
				rowss++;

			}




			CellRangeAddress address = new CellRangeAddress(0, Read.antalRader, 0, Read.antalKolummner - Read.startKolummn);
			sheet.setAutoFilter(address);

			for(int i = 0; i < Read.startKolummn; i++) {

				sheet.autoSizeColumn(i);


			}



			System.out.println(Window.boxFileURL + "/" + Read.dirSorted[indelning] +  "/CLO_Appendix_1_" 
					+ ".xlsx");


			workbook.write(new FileOutputStream(new File(Window.boxFileURL + "/" + Read.dirSorted[indelning] +  "/CLO_Appendix_1_" 
					+ ".xlsx")));



			workbook.close();


			rows = 1;


		}



	}




}