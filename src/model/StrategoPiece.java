package model;

import java.io.Serializable;

public class StrategoPiece  implements Serializable {

	private static final long serialVersionUID = -8984725848146147291L;

	/** The team for this piece */
	private Team myTeam;

	// 0 = Flag
	// 1 = Spy
	// 2-10 = Corresponding Rank
	// 11 = Bomb
	
	private int myRank;

	public StrategoPiece(Team team, int rank) {
		myTeam = team;
		myRank = rank;
	}


	public Team team() {
		return myTeam;
	}

	public int rank() {
		return myRank;
	}

	public enum Team {
		blue, red;
	}

}
