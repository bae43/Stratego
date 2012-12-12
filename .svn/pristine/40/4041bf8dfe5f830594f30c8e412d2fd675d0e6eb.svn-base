package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInit extends JPanel {

	private static final long serialVersionUID = 6420152327486110667L;

	SetUp su;
	JButton clear, rand, start;

	/**
	 * side control panel that contains buttons and controls for initiating the
	 * board position
	 */
	public PanelInit(final SetUp su) {

		this.su = su;

		Box b = new Box(BoxLayout.Y_AXIS);
		add(b);

		b.add(Box.createRigidArea(new Dimension(0, 50)));

		setPreferredSize(new Dimension(300, 300));

		JLabel title = new JLabel("Controls");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		title.setForeground(new Color(.8f, .8f, .8f));
		b.add(title);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel bPane = new JPanel();
		bPane.setOpaque(false);
		GridLayout gl = new GridLayout(3, 6);
		gl.setVgap(10);
		bPane.setLayout(gl);

		clear = new JButton("Clear All");

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				su.bi.clearBoard();

			}
		});
		bPane.add(clear);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		rand = new JButton("Randomize Remaining");

		rand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				su.bi.randomizeRemaining();
				start.setEnabled(true);
			}
		});
		bPane.add(rand);

		start = new JButton("Start");
		start.setEnabled(false);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				su.sc.startGame();
			}
		});
		bPane.add(start);

		b.add(bPane);

		setBackground(new Color(.2f, 0f, .4f));
		setVisible(true);
	}

}
