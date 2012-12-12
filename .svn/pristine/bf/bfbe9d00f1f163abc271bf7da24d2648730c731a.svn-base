package model;

import java.io.Serializable;

public class StrategoBoard implements Serializable {

	private static final long serialVersionUID = -7306024649379126462L;

	private StrategoSquare[][] board = new StrategoSquare[10][10];

	
	//TODO make a constructor taking in two 10x4 matrices of pieces (one has to be inverted)
	// public StrategoBoard(StrategoPiece[][] redPieces, StrategoPiece[][] bluePieces) {...}
	
	public StrategoBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if ((i == 2 && j == 4) || (i == 2 && j == 5)
						|| (i == 3 && j == 4) || (i == 3 && j == 5)
						|| (i == 6 && j == 4) || (i == 6 && j == 5)
						|| (i == 7 && j == 4) || (i == 7 && j == 5)) {
					board[i][j] = new StrategoSquare(true);
				} else {
					board[i][j] = new StrategoSquare(false);
				}
			}
		}
	}

	public StrategoPiece getPiece(int x, int y) {
		if (x < 0 || y < 0) {
			return null;
		}
		return board[x][y].piece(); // XXX why remove it? orig:
									// '...'.removePiece();
	}

	public StrategoPiece getPiece(int[] pos) {

		int i = pos[0];
		int j = pos[1];

		return getPiece(i, j);

	}

	public void addPiece(StrategoPiece piece, int i, int j) {

		board[i][j].addPiece(piece);

	}

	public void addPiece(StrategoPiece piece, int[] pos) {
		int i = pos[0];
		int j = pos[1];

		addPiece(piece, i, j);

	}


	public void removePiece(int[] pos) {
		int i = pos[0];
		int j = pos[1];

		board[i][j].removePiece();
	}

	public void removePiece(int i, int j) {
		board[i][j].removePiece();
	}

	public boolean hasWater(int[] pos) {

		int i = pos[0];
		int j = pos[1];

		return board[i][j].isWater();
	}

	public boolean emptyAt(int[] pos) {

		int i = pos[0];
		int j = pos[1];

		try {
			if (board[i][j].piece() == null) {
				return true;

			}
		} catch (NullPointerException npe) {
			return false;
		}
		return false;
	}



}
