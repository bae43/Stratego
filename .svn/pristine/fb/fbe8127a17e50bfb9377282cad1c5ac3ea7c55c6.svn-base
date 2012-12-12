package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.Random;

import model.StrategoPiece;
import server.DragonHobbit;

public class BoardInit extends Board {

	private static final long serialVersionUID = -4563223263299854517L;

	DragonHobbit s;

	StrategoPiece[][] board = new StrategoPiece[10][4];
	int totalPieces = 0;

	int[] hovered = new int[2];
	int[] selected = NO_SEL.clone();

	Polygon fog;
	PieceAdder pa;

	public BoardInit(DragonHobbit s, Images imgs) {
		super(s, imgs);
	}

	public int totalPieces() {
		return totalPieces;
	}

	public BoardInit(DragonHobbit s, Images imgs, PieceAdder pa) {
		super(s, imgs);

		this.s = s;

		setBackground(Color.black);

		this.pa = pa;

		setPreferredSize(new Dimension(StrategoClient.BOARD_SIZE,
				StrategoClient.BOARD_SIZE));

		setListeners();
		fogOfWar();
		repaint();
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// background image
		g.drawImage(imgs.bg(), 0, 0, null);

		// fog of war
		g.setColor(new Color(0f, 0f, 0f, .7f));
		g.fillPolygon(fog);

		GradientPaint gradientPaint = new GradientPaint(0,
				0 + StrategoClient.CELL_DIMS * 6,

				new Color(0, 0, 0, 0), 0, 0 + StrategoClient.CELL_DIMS * 4,
				new Color(0, 0, 0, .7f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0 + StrategoClient.CELL_DIMS * 4,
					StrategoClient.BOARD_SIZE, StrategoClient.CELL_DIMS * 2);
		}

		Polygon sq;

		// highlights hovered square
		if (hovered[0] < 10 && hovered[1] > 5 && hovered[1] < 10) {
			sq = square(hovered[0], hovered[1]);
			g.setColor(new Color(.7f, .7f, .4f, .6f));
			g.fillPolygon(sq);
		}

		// highlights selected square
		// checks that one is selected and its in bounds
		if (!Arrays.equals(selected, NO_SEL) && selected[1] > 5
				&& selected[0] < 10 && selected[1] < 10) {

			sq = square(selected[0], selected[1]);
			g.setColor(new Color(1f, .5f, 0f, .4f));
			g.fillPolygon(sq);
			g.setColor(new Color(1f, 1f, 0f));
			g.drawPolygon(sq);
		}

		g.setColor(new Color(0, 0, 0, .1f));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {

				// tiled overlay (very light black) may remove later
				g.drawPolygon(square(i, j + 6));

				if (board[i][j] != null) {
					drawPiece(g,
							new StrategoPiece(myTeam, (board[i][j].rank())), i,
							j + 6);
				}

			}
		}
	}

	private void fogOfWar() {
		fog = new Polygon();
		fog.addPoint(0, 0);
		fog.addPoint(10 * StrategoClient.CELL_DIMS + 0, 0);
		fog.addPoint(10 * StrategoClient.CELL_DIMS + 0,
				4 * StrategoClient.CELL_DIMS + 0);
		fog.addPoint(0, 4 * StrategoClient.CELL_DIMS + 0);
	}

	/** draws the image for the piece at location x,y */
	private void drawPiece(Graphics g, StrategoPiece piece, int x, int y) {

		if (piece == null) {
			return;
		}

		if (piece.team().equals(StrategoPiece.Team.red)) {
			g.drawImage(imgs.pieceRed(piece.rank()), 0 + x
					* StrategoClient.CELL_DIMS, 0 + y
					* StrategoClient.CELL_DIMS, null);
		} else {
			g.drawImage(imgs.pieceBlue(piece.rank()), 0 + x
					* StrategoClient.CELL_DIMS, 0 + y
					* StrategoClient.CELL_DIMS, null);

		}

	}

	/** adds main mouse listeners to the game board */
	private void setListeners() {

		// sq hovered over
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {

				int xLoc = e.getX();
				int yLoc = e.getY();

				hovered = new int[] {
						(int) ((xLoc - 0) / StrategoClient.CELL_DIMS),
						(int) ((yLoc - 0) / StrategoClient.CELL_DIMS) };

				repaint();

			}
		});

		// sq selection
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				// check bounds of click
				if (hovered[0] < 10 && hovered[1] < 10 && hovered[1] > 5) {

					// add piece to board
					if (board[hovered[0]][hovered[1] - 6] == null) {
						
						//increment counter if no pieces left of currently selected
						int i = 0;
						while (pa.piecesRemaining[pa.curPiece] == 0){
							i++;
							pa.curPiece = (pa.curPiece + 1) % 12;
							
							//prevent infinite loop when no peices are left
							if(i == 12){
								break;
							}
							}
						
						if (pa.takeOut(pa.curPiece)) {
							totalPieces++;
							board[hovered[0]][hovered[1] - 6] = new StrategoPiece(
									myTeam, pa.curPiece);

						}
						


						// remove piece from board
					} else {
						totalPieces--;
						int removalRank = board[hovered[0]][hovered[1] - 6]
								.rank();
						pa.putBack(removalRank);
						board[hovered[0]][hovered[1] - 6] = null;

					}

					repaint();

				}
			}

		});

	}

	public void clearBoard() {
		board = new StrategoPiece[10][4];
		pa.piecesRemaining = pa.piecesStarting.clone();
		totalPieces = 0;
		for (int i = 0; i < 12; i++) {
			pa.updateButtonText(i);
		}
		repaint();
	}

	/**
	 * fills in board psuedo-randomly Not very random
	 * */
	public void randomizeRemaining() {

		Random r = new Random();

		// loop through all spaces on board
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {

				if (board[i][j] == null) {

					// choose piece
					int randI = r.nextInt(12);
	
					while (pa.piecesRemaining[randI] == 0) {
						randI = (randI + 1) % 12;
					}

					// add piece
					if (pa.takeOut(randI)) {
						totalPieces++;
						board[i][j] = new StrategoPiece(myTeam, randI);

					} else {
						pa.curPiece = (pa.curPiece + 1) % 12;
					}

				}

			}
		}
		repaint();

	}

}
