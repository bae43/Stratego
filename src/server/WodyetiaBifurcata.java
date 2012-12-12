package server;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.StrategoBoard;
import model.StrategoPiece;
import util.Move;

public class WodyetiaBifurcata implements DragonHobbit {

	public String hostName;

	/** The last move registered by the server */
	private Move last;

	private StrategoBoard myBoard = new StrategoBoard();
	
	private static boolean gameReady = false;
	
	private static int numPlayers = 0;
	
	private static boolean isPlayerOneTurn = false;

	public static void main(String[] args) {

		if (args.length < 1) {
			return;
		}

		try {
			Runtime.getRuntime().exec("rmiregistry");
			System.setProperty("java.rmi.server.codebase",
					WodyetiaBifurcata.class.getProtectionDomain()
							.getCodeSource().getLocation().toString());
		} catch (IOException e) {
			System.err.println("rmiregistry command error " + e.toString());
			e.printStackTrace();
		}

		final WodyetiaBifurcata test = new WodyetiaBifurcata(args[0]);

		try {

			test.myBoard.addPiece(
					new StrategoPiece(StrategoPiece.Team.blue, 2), 1, 1);
			test.myBoard.addPiece(
					new StrategoPiece(StrategoPiece.Team.blue, 7), 2, 1);
			test.myBoard.addPiece(new StrategoPiece(StrategoPiece.Team.red, 0),
					3, 6);
			test.myBoard.addPiece(
					new StrategoPiece(StrategoPiece.Team.blue, 11), 9, 2);
			test.myBoard.addPiece(new StrategoPiece(StrategoPiece.Team.red, 6),
					5, 1);
			test.myBoard.addPiece(new StrategoPiece(StrategoPiece.Team.red, 1),
					5, 2);

			DragonHobbit obj = test;

			DragonHobbit stub = (DragonHobbit) UnicastRemoteObject
					.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind(test.hostName, stub);

		} catch (RemoteException re) {
			System.err.println("Remote Server Exception: " + re.toString());
		} catch (AlreadyBoundException e) {
			System.err.println("Already Bound. Close pre-existing servers.");
			System.exit(0);
		}

		// /// New Server Frame?

		JFrame frame = new JFrame(test.hostName);
		frame.setSize(new Dimension(200, 100));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		JButton unbind = new JButton("Disconnect");
		unbind.setFont(new Font("Consolas", Font.BOLD, 24));

		unbind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				test.disconnect();
			}
		});

		System.out.println(test.hostName + " ready");

		frame.add(unbind);
		frame.setVisible(true);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// increment as players join the game
				while(numPlayers != 2) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
				// print game ready message to clients
				gameReady = true;
				isPlayerOneTurn = true;
			}
			
		}).start();
	}
	
	/*
	 * public submitBoardSetup() {
	 * 		add one players pieces to the board
	 * 		numPlayers++;
	 * 		notifyAll();
	 * }
	 */

	public void disconnect() {

		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
			if (registry != null) {
				registry.unbind(hostName);
			} else {
				throw new RemoteException();
			}
		} catch (AccessException e) {
			System.err.println("Server Access Exception: " + e.toString());
		} catch (RemoteException e) {
			System.err.println("Remote Server Exception: " + e.toString());
		} catch (NotBoundException e) {
			System.err.println("Server Not Bound: " + e.toString());
		}
		System.out.println("Closed " + hostName);
		System.exit(0);
	}

	public WodyetiaBifurcata(final String hn) {

		hostName = hn;
	}

	@Override
	public boolean isMoveValid(Move move) throws RemoteException {

		// TODO check range is +/- 1 (except 2s)
		// check only moving in 4 neighbor

		int p = myBoard.getPiece(move.start()).rank();

		// bombs & flags can't move
		if (p == 0 || p == 11) {
			return false;
		}

		boolean ret = true;

		// out of bounds
		if (move.end()[0] > 9 || move.end()[1] > 9) {
			ret = false;

		} else if (false) { // TODO CHECK LAKES

			// start is empty
		} else if (!this.hasPieceAt(move.start())) {
			ret = false;

			// end has piece
		} else if (this.hasPieceAt(move.end())) {

			// attacking same team
			if (this.getPieceAt(move.start()).team() == (this.getPieceAt(move
					.end()).team())) {
				ret = false;
			}
		}
		return ret;
	}

	@Override
	public boolean takeMove(Move move) throws RemoteException {
		isPlayerOneTurn = !isPlayerOneTurn;
		if (isMoveValid(move)) {
			// pieces at start and end location of move
			StrategoPiece s = myBoard.getPiece(move.start());

			if (myBoard.emptyAt(move.end())) {
				myBoard.addPiece(s, move.end());
			} else {
				StrategoPiece e = myBoard.getPiece(move.end());

				if (s.rank() > e.rank()) {
					myBoard.addPiece(s, move.end());
				}
			}

			myBoard.removePiece(move.start());
			return true;

		} else
			return false;
	}

	@Override
	public StrategoBoard getBoard() throws RemoteException {
		return myBoard;
	}

	@Override
	public StrategoPiece getPieceAt(int x, int y) throws RemoteException {
		return myBoard.getPiece(x, y);
	}

	@Override
	public StrategoPiece getPieceAt(int[] a) throws RemoteException {
		return myBoard.getPiece(a[0], a[1]);
	}

	@Override
	public Move getLastMove() throws RemoteException {
		return last;
	}

	@Override
	public boolean hasPieceAt(int x, int y) throws RemoteException {
		return myBoard.emptyAt(new int[] { x, y });
	}

	@Override
	public boolean hasPieceAt(int[] a) throws RemoteException {
		return !myBoard.emptyAt(a);
	}

}
