import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class
 * 
 * 
 */

public class View extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer clock = new Timer(0, this);
	Model m;
	private static int size = 3;
	private static int X_SIZE = 750;
	private static int Y_SIZE = 700;
	private int MAX_TRACK = 10;
	TrackingManipulation trackMan;
	public Color[] colorList = { Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.WHITE, Color.RED,
			Color.MAGENTA, Color.YELLOW, Color.GRAY, Color.PINK };

	View(Model m) {
		this.m = m;
		setSize(750, 650);
		setBackground(Color.black);
		setLayout(null);
	}

	public void changeTime(int delay) {
		clock.setDelay(delay);
	}

	public void actionPerformed(ActionEvent e) {
		removeAll();
		m.updateMap();
		m.checkMap();
		m.moveAll();
		trackMan.updateTable();
		revalidate();
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g1 = (Graphics2D) g;
		for (int i = 0; i < m.nrOfParticles; i++) {
			if (m.particle[i].isMoving != true) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.YELLOW);
			}
			g.fillRect((int) Math.round(m.particle[i].x), (int) Math.round(m.particle[i].y), size, size);
		}
		for (int i = 0; i < MAX_TRACK; i++) {
			if (m.particle[i].isTracking) {
				// Paint the particle's past path.
				for (int j = 1; j < m.particle[i].historyX.size(); ++j) {
					g.setColor(colorList[i]);
					g.drawLine(m.particle[i].historyX.get(j), m.particle[i].historyY.get(j),
							m.particle[i].historyX.get(j - 1), m.particle[i].historyY.get(j - 1));
				}
				if (m.particle[i].isMoving && m.particle[i].isTracking) {
					// Haircross
					g.setColor(colorList[i]);
					g.drawLine((int) Math.round(m.particle[i].x), 0, (int) Math.round(m.particle[i].x), Y_SIZE);
					g.drawLine(0, (int) Math.round(m.particle[i].y), X_SIZE, (int) Math.round(m.particle[i].y));

					// Gets size of text font so the rectangle can be
					// accomodated to fit it.
					FontMetrics fm = g1.getFontMetrics();

					// White rectangle is drawn.
					g.drawRect((int) Math.round(m.particle[i].x), (int) Math.round(m.particle[i].y) - fm.getAscent(),
							30, 15);
					g.setColor(colorList[4]);
					g.fillRect((int) Math.round(m.particle[i].x), (int) Math.round(m.particle[i].y) - fm.getAscent(),
							30, 15);
					// Add text to white rectangle.
					g.setColor(Color.black);
					g.drawString("" + (i + 1), (int) Math.round(m.particle[i].x), (int) Math.round(m.particle[i].y));

				}
			}
		}

	}

	public View drawModifiers() {
		View modifiersPanel = new View(m);

		modifiersPanel.setSize(50, 700);
		modifiersPanel.setBackground(null);
		modifiersPanel.setLayout(new FlowLayout());
		modifiersPanel.setLocation(750, 200);

		Manipulation manipulator = new Manipulation(m, this);
		trackMan = manipulator.trackMan;
		modifiersPanel.add(manipulator.trackButton);
		modifiersPanel.add(manipulator.scrollBarL);
		modifiersPanel.add(manipulator.scrollBarT);
		modifiersPanel.add(new JLabel("L  "));
		modifiersPanel.add(new JLabel("T"));

		return modifiersPanel;
	}

}