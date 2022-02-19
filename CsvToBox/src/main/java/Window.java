import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Window();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 449, 330);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


					btnNewButton.setEnabled(false);

					progressBar.setValue(0);
					try {
						CSVtoExcel.read();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
					progressBar.setValue(100);


			}

		});
		btnNewButton.setBounds(146, 244, 151, 36);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("CSV to BOX");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(146, 32, 113, 80);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(146, 143, 210, 20);
		panel.add(textField);
		textField.setColumns(10);

		// CSV
		JButton btnNewButton_1 = new JButton("Browse...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".CSV", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					CSVtoExcel.CSV_PATH = chooser.getSelectedFile().getAbsolutePath();
					textField.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnNewButton_1.setBounds(47, 142, 89, 23);
		panel.add(btnNewButton_1);

		// BOX
		JButton btnNewButton_2 = new JButton("Browse...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					CSVtoExcel.BOX_PATH = chooser.getSelectedFile().getAbsolutePath();
					textField_1.setText(chooser.getSelectedFile().getAbsolutePath());
				}

			}
		});
		btnNewButton_2.setBounds(47, 173, 89, 23);
		panel.add(btnNewButton_2);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(146, 174, 210, 20);
		panel.add(textField_1);

		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setBounds(151, 219, 146, 14);
		panel.add(progressBar);

	}

}
