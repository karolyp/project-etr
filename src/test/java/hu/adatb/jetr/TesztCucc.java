package hu.adatb.jetr;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TesztCucc {

	private JFrame frmJetr;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TesztCucc window = new TesztCucc();
				window.frmJetr.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 *
	 * @wbp.parser.entryPoint
	 */
	public TesztCucc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJetr = new JFrame();
		frmJetr.setTitle("JETR");
		frmJetr.setBounds(100, 100, 800, 600);
		frmJetr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJetr.getContentPane().setLayout(new BorderLayout(0, 0));

		Object[][] data = { { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true },
				{ "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }, { "valami", true }

		};
		Object[] columnNames = { "oszlop1", "oszlop2" };
		TableModel tm = new AbstractTableModel() {
			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				return data.length;
			}

			@Override
			public String getColumnName(int col) {
				return (String) columnNames[col];
			}

			@Override
			public Object getValueAt(int row, int col) {
				return data[row][col];
			}

			@Override
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

		};
		table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);

		JScrollPane jsp = new JScrollPane(table);
		frmJetr.getContentPane().add(jsp, BorderLayout.NORTH);

	}
}
