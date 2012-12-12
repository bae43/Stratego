package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PieceAdder extends JPanel {

	private static final long serialVersionUID = -5566049489973542977L;

	SetUp su;

	Images imgs;
	// panel of buttons
	JPanel bPane;

	JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
	JButton[] buttons = new JButton[12];

	int curPiece;

	// TODO fix to correct counts
	int[] piecesStarting = new int[] { 1, 1,6, 5, 4, 4, 4, 4, 3, 1, 1, 6 };
	int[] piecesRemaining = piecesStarting.clone();

	/**
	 * creates a panel displaying types of pieces and the number remaining of
	 * each type
	 * 
	 * @param su
	 * @param imgs
	 */
	public PieceAdder(final SetUp su, Images imgs) {

		this.su = su;
		this.imgs = imgs;
		setOpaque(false);

		// Comic Sans gets no love :(
		JLabel title = new JLabel("Place your starting setup");
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		title.setForeground(Color.white);
		add(title);

		bPane = new JPanel();
		bPane.setOpaque(false);
		GridLayout gl2 = new GridLayout(3, 6);
		gl2.setHgap(5);
		gl2.setVgap(5);
		bPane.setLayout(gl2);

		// bPane.setMinimumSi8e(new Dimension(getWidth(), getHeight()));

		b0 = new JButton("Flag (x" + piecesRemaining[0] + ")");
		b0.setMinimumSize(new Dimension(150, 150));

		b0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 0;
			}

		});
		buttons[0] = b0;
		bPane.add(b0);

		b1 = new JButton("Spy (x" + piecesRemaining[1] + ")");
		b1.setMinimumSize(new Dimension(150, 150));

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 1;
			}
		});

		buttons[1] = b1;
		bPane.add(b1);

		b11 = new JButton("Bomb (x" + piecesRemaining[11] + ")");
		b11.setMinimumSize(new Dimension(150, 150));

		b11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 11;
			}
		});

		buttons[11] = b11;
		bPane.add(b11);

		bPane.add(new JLabel());
		bPane.add(new JLabel());

		b2 = new JButton("2 (x" + piecesRemaining[2] + ")");
		b2.setMinimumSize(new Dimension(150, 150));

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 2;
			}
		});

		buttons[2] = b2;
		bPane.add(b2);

		b3 = new JButton("3 (x" + piecesRemaining[3] + ")");
		b3.setMinimumSize(new Dimension(150, 150));

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 3;
			}
		});

		buttons[3] = b3;
		bPane.add(b3);

		b4 = new JButton("4 (x" + piecesRemaining[4] + ")");
		b4.setMinimumSize(new Dimension(150, 150));

		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 4;
			}
		});

		buttons[4] = b4;
		bPane.add(b4);

		b5 = new JButton("5 (x" + piecesRemaining[5] + ")");
		b5.setMinimumSize(new Dimension(150, 150));

		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 5;
			}
		});
		buttons[5] = b5;
		bPane.add(b5);

		b6 = new JButton("6 (x" + piecesRemaining[6] + ")");
		b6.setMinimumSize(new Dimension(150, 150));

		b6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 6;
			}
		});
		buttons[6] = b6;
		bPane.add(b6);

		b7 = new JButton("7 (x" + piecesRemaining[7] + ")");
		b7.setMinimumSize(new Dimension(150, 150));

		b7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 7;
			}
		});
		buttons[7] = b7;
		bPane.add(b7);

		b8 = new JButton("8 (x" + piecesRemaining[8] + ")");
		b8.setMinimumSize(new Dimension(150, 150));

		b8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 8;
			}
		});
		buttons[8] = b8;
		bPane.add(b8);

		b9 = new JButton("9 (x" + piecesRemaining[9] + ")");
		b9.setMinimumSize(new Dimension(150, 150));

		b9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 9;
			}
		});
		buttons[9] = b9;
		bPane.add(b9);

		b10 = new JButton("10 (x" + piecesRemaining[10] + ")");
		b10.setMinimumSize(new Dimension(150, 150));

		b10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPiece = 10;
			}
		});
		buttons[10] = b10;
		bPane.add(b10);

		add(bPane);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g.setColor(new Color(.1f, .4f, .6f, .5f));

		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0,
				getWidth(), getHeight(), 25, 25);
		g2.fill(roundedRectangle);
	}

	/** takes a piece out of the conatiner and places it on the board */
	public boolean takeOut(int pieceRank) {
		if (piecesRemaining[pieceRank] == 0) {
			return false;
		} else {
			piecesRemaining[pieceRank] = piecesRemaining[pieceRank] - 1;
			updateButtonText(pieceRank);
			return true;
		}
	}

	/** returns a piece from the board back to the container */
	public void putBack(int rank) {
		piecesRemaining[rank] = piecesRemaining[rank] + 1;
		updateButtonText(rank);
	}

	/**
	 * updates button text and enability
	 * 
	 * @param p
	 *            - piece currently being added or taken away
	 * */
	public void updateButtonText(int p) {

		//name of piece
		String n;
		if(p == 0){
			n = "Flag";
		}else if(p == 1){
			n = "Spy";
		} else if (p == 11){
			n = "Bomb";
		}else n = new String("" + p); //XXX better way?
		
		buttons[p].setText(n + " (x" + piecesRemaining[p] + ")");
		if (piecesRemaining[p] == 0) {
			buttons[p].setEnabled(false);
	
		} else {
			buttons[p].setEnabled(true);
		}

	}
}
