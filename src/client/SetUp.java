package client;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SetUp extends JPanel {

	private static final long serialVersionUID = -2126081765821325322L;

	StrategoClient sc;

	JLayeredPane lp;
	PieceAdder pa;
	BoardInit bi;
	PanelInit pane;

	/**
	 * Pane containing a board and side panel needed for setting up the intial
	 * pieces and starting the game
	 */
	public SetUp(StrategoClient sc) {

		this.sc = sc;

		setBackground(Color.black);

		lp = new JLayeredPane();
		lp.setPreferredSize(new Dimension(1000, 700));

		pa = new PieceAdder(this, sc.imgs);
		pa.setBounds(StrategoClient.BORDER_SIZE + StrategoClient.SQUARE_SIZE,
				StrategoClient.BORDER_SIZE + StrategoClient.BOARD_SIZE / 16,
				StrategoClient.BOARD_SIZE - 2 * StrategoClient.SQUARE_SIZE,
				StrategoClient.BOARD_SIZE / 4);

		lp.add(pa, new Integer(1));

		bi = new BoardInit(sc.s, sc.imgs, pa);
		bi.setBounds(0, 0, StrategoClient.BOARD_SIZE, StrategoClient.BOARD_SIZE);
		lp.add(bi, new Integer(0));

		pane = new PanelInit(this);
		pane.setBounds(StrategoClient.BOARD_SIZE, 0, 300,
				StrategoClient.BOARD_SIZE);
		lp.add(pane, new Integer(2));

		lp.setVisible(true);
		add(lp);
	}

}
