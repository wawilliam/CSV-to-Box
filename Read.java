import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * CSV_BOX
 *
 * @author William Andersson
 * @version 18 apr. 2018
 *
 */
public class Read {

	static int antalRader;
	static int antalKolummner;
	static int startKolummn;

	static int antalIndelningar;
	static int antalRaderPerIndelning[];
	static String[] [] array;
	static String [] dirSorted;


	Read(String CSVFileURL) throws IOException {


		getTrimmedArray(array = getColummsWithRows(getRows(CSVFileURL)));
		getSortedDirectories();
		new Write().write();;
		System.out.println("Klar!");

	}

	String[] getRows(String CSVFileURL) throws IOException {


		BufferedReader reader = new BufferedReader(new FileReader(new File(CSVFileURL)));

		int radNummer = 0;
		String[] rader = new String[200000];

		while(reader.ready()) {

			String line = reader.readLine();
			rader[radNummer] = line;
			radNummer++;

		}

		//Antal rader
		antalRader = radNummer;
		reader.close();


		return rader;

	}


	String[] [] getColummsWithRows(String[] rows) {


		array = new String[200000] [100];



		for(int i = 0; i < antalRader; i++) {


			String [] columms = rows[i].split(";");


			for(int n = 0; n < columms.length; n++) {

				array[i] [n] = columms[n];

			}


		}

		//Antal kolummner
		antalKolummner = rows[0].split(";").length;

		return array;


	}


	void getTrimmedArray(String[] [] array) {


		int i = 1;

		while(!array[0] [i].equals(array[0] [0])) {

			i++;


		}

		System.out.println(antalKolummner);
		startKolummn = i;
		System.out.println(startKolummn);


		int w = 1;
		int rad = 1;
		int start = 1;

		antalIndelningar = 0;
		antalRaderPerIndelning = new int[10000];

		String text1 = array [start] [0];



		while(rad < antalRader) {

			text1 = array[start] [0];

			while(text1.equals(array[rad] [0])) {


				w++;
				rad++;

			}

			antalRaderPerIndelning[antalIndelningar] = w;
			antalIndelningar++;
			start = rad;
			w = 0;


		}




	}

	void getSortedDirectories() {

		dirSorted = new String[Window.directories.length];
		String[] benamning = new String[Window.directories.length];
		int summa = 1;


		for(int nummer = 0; nummer < dirSorted.length; nummer++) {

			benamning[nummer] = array[summa] [0].trim();
			summa = summa + antalRaderPerIndelning[nummer] +1;
			System.out.println(benamning[nummer]);

		}


		for(int i = 0; i < dirSorted.length; i++) {



			for(int w = 0; w < dirSorted.length; w++) {


				if(benamning[i].equals(Window.directories[w].getName())) {


					dirSorted[i] = Window.directories[w].getName();

				}


			}






		}



	}

}