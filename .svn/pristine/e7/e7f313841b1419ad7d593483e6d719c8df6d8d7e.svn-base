package model;

import java.io.Serializable;

public class StrategoSquare  implements Serializable  {

	private static final long serialVersionUID = 3305379565818759902L;

	private boolean isWater;

	private StrategoPiece myPiece;

	public StrategoSquare(boolean water) {
		isWater = water;
	}

	public void addPiece(StrategoPiece piece) {
		myPiece = piece;
	}

	public boolean isWater() {
		return isWater;
	}

	public StrategoPiece removePiece() {
		StrategoPiece tempPiece = myPiece;
		myPiece = null;
		return tempPiece;
	}

	public StrategoPiece piece() {
		return myPiece;
	}
	
	public boolean isEmpty() {
		if (myPiece == null) {
			return true;
		} else {
			return false;
		}
	}

}
