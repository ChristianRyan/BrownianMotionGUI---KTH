import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class TrackingManipulation extends JPanel implements TableModelListener {

	private static final long serialVersionUID = 1L;
	private int MAX_TRACK = 10;
	public Object[][] labels;
	public Model m;
	public JTable table;
	public JScrollPane scroller;
	public DefaultTableModel model;
	public JCheckBox[] trackingButtons;
	public Object[] columnNames = { "Particle", "Coordinates", "State", "Tracking" };

	TrackingManipulation(Model m) {
		this.m = m;
		this.labels = new Object[MAX_TRACK][columnNames.length];
		setTable();
		scroller = new JScrollPane(table);
		add(scroller);
	}

	/**
	 * Creates a table with information about the 10 first particles in the
	 * model particle vector.
	 */
	public void setTable() {

		tableData(m);
		model = new DefaultTableModel(labels, columnNames);
		model.addTableModelListener(this);

		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			// Fill the fourth column with check boxes.
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 3) {
					return Boolean.class;
				} else {
					return Object.class;
				}
			}
		};

	}

	/**
	 * Shows the coordinates of the particles followed in the table.
	 */
	public void updateTable() {
		Object[] newCoordinates = updateCoordinate();
		for (int i = 0; i < newCoordinates.length; ++i) {
			table.setValueAt(newCoordinates[i], i, 1);
			table.setValueAt(m.particle[i].movingToString(), i, 2);
		}
	}

	/**
	 * Fills the table with data.
	 * 
	 * @param m
	 */
	public void tableData(Model m) {
		for (int row = 0; row < MAX_TRACK; ++row) {
			for (int j = 0; j < columnNames.length; ++j) {
				if (j == 0) {
					labels[row][j] = row + 1;

				} else if (j == 1) {
					labels[row][j] = Math.round(m.particle[row].x) + ", " + Math.round(m.particle[row].y);

				} else if (j == 2) {
					labels[row][j] = m.particle[row].movingToString();
				} else if (j == 3) {
					labels[row][j] = new Boolean(false);
				}

			}

		}
	}

	/**
	 * Gets the current coordinates of the particles showed in the table.
	 * 
	 * @return
	 */
	public Object[] updateCoordinate() {

		Object[] newCoordinates = new String[MAX_TRACK];
		for (int row = 0; row < MAX_TRACK; ++row) {

			newCoordinates[row] = Math.round(m.particle[row].x)  + ", " + Math.round(m.particle[row].y);

		}
		return newCoordinates;
	}

	/**
	 * Listen for user interaction with the check boxes.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int startRow = e.getFirstRow();
		int column = e.getColumn();

		if (column == 3) {
			// If check box is clicked, the particle state is changed to
			// tracked.
			if (((boolean) model.getValueAt(startRow, column))) {

				m.particle[startRow].isTracking = true;

			} else {
				m.particle[startRow].isTracking = false;
			}

		}

	}
}
