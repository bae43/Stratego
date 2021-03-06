package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JLayeredPane;

import server.DragonHobbit;

public class StrategoClient extends JApplet {

	private static final long serialVersionUID = 1L;

	DragonHobbit s;

	Images imgs;
	JLayeredPane lp;
	StartMenu sm;
	SetUp su;
	Board b;

	/** border size surrounding main board */
	public static final int BORDER_SIZE = 20;

	/** main board size */
	public static final int BOARD_SIZE = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight()
			- (2 * BORDER_SIZE + 50);

	/** Space between squares */
	public static final int SPACE_SIZE = 2;

	/** side length of squares */
	public static final int SQUARE_SIZE = (int) (BOARD_SIZE / 10 - SPACE_SIZE / 2);

	/** size total cell including spacing */
	public static final int CELL_DIMS = SQUARE_SIZE + SPACE_SIZE;

	public void init() {

		setMinimumSize(new Dimension((SQUARE_SIZE + SPACE_SIZE) * 10,
				(SQUARE_SIZE + SPACE_SIZE) * 10));
		setSize(new Dimension(700, 500));

		imgs = new Images();

		setBackground(Color.black);

		// initBoard();

		setVisible(true);
	}

	public void start() {
		sm = new StartMenu(this);
		add(sm);

	}

	protected void initBoard() {

		remove(sm);
		su = new SetUp(this);
		setSize(new Dimension(1100, 900));
		add(su);

	}

	protected void startGame() {// StrategoBoard sb) {

		remove(su);
		b = new Board(s, imgs);
		add(b);

	}

	public void setServer(DragonHobbit s2) {
		s = s2;
	}

}
