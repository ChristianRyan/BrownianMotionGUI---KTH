import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;

public class Manipulation {

	public Model myModel;
	public View myView;
	public JButton trackButton;
	public JScrollBar scrollBarL, scrollBarT;
	public JFrame trackingFrame  = new JFrame();
	public JTable table;
	public JPanel panel;
	public TrackingManipulation trackMan;


	Manipulation(Model myModel, View myView) {
		this.myModel = myModel;
		this.myView = myView;

		scrollBarL = new JScrollBar();
		scrollBarT = new JScrollBar();

		scrollBarT.setPreferredSize(new Dimension(15, 200));
		scrollBarT.setValues(10, 100, 0, 1000);

		scrollBarL.setPreferredSize(new Dimension(15, 200));
		scrollBarL.setValues(3, 3, 0, 15);

		trackButton = new JButton("Track");
		trackButton.setPreferredSize(new Dimension(50, 20));
		trackButton.setFont(new Font("Times New Roman", Font.PLAIN, 7));

		scrollBarL.addAdjustmentListener(new MyAdjustmentListener());
		scrollBarT.addAdjustmentListener(new MyAdjustmentListener1());
		trackButton.addActionListener(new MyListener());
		
		TrackingFrame(this.myModel);
	}

	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent ae) {
			double newL = ae.getValue();
			for (int i = 0; i < myModel.nrOfParticles; i++) {
				myModel.particle[i].L = newL;
			}
		}
	}

	class MyAdjustmentListener1 implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent ae) {
			int newT = ae.getValue();
			myView.changeTime(newT);

		}
	}

	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			trackingFrame.setVisible(true);
		}

	}
	
	public void TrackingFrame(Model m) {
		trackingFrame.setTitle("Tracking Frame");	
		trackingFrame.setLocationRelativeTo(null);	//puts frame on middle of screen
		trackingFrame.setDefaultCloseOperation(trackingFrame.DISPOSE_ON_CLOSE);
		
		trackMan = new TrackingManipulation(m);
		
		trackingFrame.setAlwaysOnTop(true);
		trackingFrame.add(trackMan);
		trackingFrame.pack();
	}
}