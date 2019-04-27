import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
/**
 * 
 * CSV_BOX
 *
 * @author William Andersson
 * @version 18 apr. 2018
 *
 */
public class Window {


	private JTextField textField;

	public static String csvFileURL = "";
	public static String boxFileURL = "";
	private JLabel text1;
	private JLabel text2;
	File csvFile;
	private JTextField textField_1;
	static File[] directories;

	/**
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @wbp.parser.entryPoint
	 */

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, FileNotFoundException, IOException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Window().drawWindow();

	}


	void drawWindow() {

		JFrame frmCsvTillBox = new JFrame();

		frmCsvTillBox.setSize(400, 400);
		frmCsvTillBox.setLocationRelativeTo(null);
		frmCsvTillBox.setResizable(false);
		frmCsvTillBox.setTitle("CSV till Box");
		frmCsvTillBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmCsvTillBox.getContentPane().setLayout(springLayout);

		text1 = new JLabel("CSV till Box");
		springLayout.putConstraint(SpringLayout.WEST, text1, 100, SpringLayout.WEST, frmCsvTillBox.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, text1, -258, SpringLayout.SOUTH, frmCsvTillBox.getContentPane());
		text1.setFont(new Font("Arial", Font.BOLD, 27));
		frmCsvTillBox.getContentPane().add(text1);

		text2 = new JLabel("D\u00E5 var det klart! Alla filerna \u00E4r uppladdade till Box");
		text2.setVisible(false);
		springLayout.putConstraint(SpringLayout.WEST, text2, 73, SpringLayout.WEST, frmCsvTillBox.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, text2, -10, SpringLayout.SOUTH, frmCsvTillBox.getContentPane());
		frmCsvTillBox.getContentPane().add(text2);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 59, SpringLayout.SOUTH, text1);
		springLayout.putConstraint(SpringLayout.WEST, textField, 190, SpringLayout.WEST, frmCsvTillBox.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -47, SpringLayout.EAST, frmCsvTillBox.getContentPane());
		frmCsvTillBox.getContentPane().add(textField);
		textField.setColumns(10);

		JButton button2 = new JButton("Sortera");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					new Read(csvFileURL);
				} catch (IOException e1) {
					e1.printStackTrace();
				}


			}
		});
		springLayout.putConstraint(SpringLayout.WEST, button2, 127, SpringLayout.WEST, frmCsvTillBox.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, button2, -7, SpringLayout.NORTH, text2);
		springLayout.putConstraint(SpringLayout.EAST, button2, -142, SpringLayout.EAST, frmCsvTillBox.getContentPane());
		button2.setEnabled(false);
		frmCsvTillBox.getContentPane().add(button2);

		JButton button1 = new JButton("V\u00E4lj CSV-filen");
		springLayout.putConstraint(SpringLayout.NORTH, button1, -1, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, button1, 89, SpringLayout.WEST, frmCsvTillBox.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button1, -6, SpringLayout.WEST, textField);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");



				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					File file = chooser.getSelectedFile();
					csvFileURL = file.getAbsolutePath();
					textField.setText(csvFileURL);

					if(!csvFileURL.equals("") && !(boxFileURL.equals("")) ) {

						button2.setEnabled(true);

					}

				}

				else {


				}






			}
		});
		frmCsvTillBox.getContentPane().add(button1);

		JButton btnVljBoxmappen = new JButton("V\u00E4lj Box-mappen");
		btnVljBoxmappen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				JFileChooser chooser = new JFileChooser("C:/Users/" + System.getProperty("user.name") + "/Box Sync/");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					File file = chooser.getSelectedFile();
					boxFileURL = file.getAbsolutePath();
					textField_1.setText(boxFileURL);


					directories = new File(boxFileURL).listFiles(File::isDirectory);



				}

				if(!csvFileURL.equals("") && !(boxFileURL.equals("")) ) {

					button2.setEnabled(true);

				}



			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnVljBoxmappen, 6, SpringLayout.SOUTH, button1);
		springLayout.putConstraint(SpringLayout.WEST, btnVljBoxmappen, 0, SpringLayout.WEST, text2);
		frmCsvTillBox.getContentPane().add(btnVljBoxmappen);

		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 11, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		textField_1.setColumns(10);
		frmCsvTillBox.getContentPane().add(textField_1);
		frmCsvTillBox.setVisible(true);



	}
}