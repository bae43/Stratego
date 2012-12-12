package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import server.DragonHobbit;
import server.WodyetiaBifurcata;

@SuppressWarnings("serial")
public class Login extends JPanel {

	StrategoClient sc;
	boolean startup = true;

	JLabel nameTitle, serverTitle;
	TextField name, serverName;
	JRadioButton newG, existG;
	JButton quit, go;

	public Login(final StrategoClient sc) {

		this.sc = sc;
		setOpaque(false);

		Box b = new Box(BoxLayout.Y_AXIS);
		add(b);

		b.add(Box.createRigidArea(new Dimension(0, 250)));

		JLabel logTitle = new JLabel("Join Game:");
		logTitle.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		logTitle.setForeground(new Color(.6f, .6f, .8f));
		logTitle.setAlignmentX(RIGHT_ALIGNMENT);
		b.add(logTitle);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		nameTitle = new JLabel("Name:   ");
		nameTitle.setAlignmentX(LEFT_ALIGNMENT);
		nameTitle.setForeground(new Color(.6f, .6f, .8f));
		b.add(nameTitle);

		name = new TextField();
		name.setText("Player 1");
		name.setFont(new Font("Baskerville Old Face", Font.BOLD, 14));
		b.add(name);

		b.add(Box.createRigidArea(new Dimension(0, 30)));

		Box gameB = new Box(BoxLayout.X_AXIS);
		ButtonGroup gameType = new ButtonGroup();
		newG = new JRadioButton("Host");
		
		existG = new JRadioButton("Find Existing");
		existG.setSelected(true);
		
		// newG.setBackground(new Color(0,0,0,0));
		// existG.setBackground(new Color(0,0,0,0));

		gameType.add(newG);
		gameType.add(existG);

		gameB.add(newG);
		gameB.add(existG);

		b.add(gameB);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		serverTitle = new JLabel("Game Name:            ");
		serverTitle.setAlignmentX(LEFT_ALIGNMENT);
		serverTitle.setForeground(new Color(.6f, .6f, .8f));
		b.add(serverTitle);

		serverName = new TextField();
		serverName.setFont(new Font("Baskerville Old Face", Font.BOLD, 14));
		b.add(serverName);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		Box buttBox = new Box(BoxLayout.X_AXIS);
		buttBox.setAlignmentX(LEFT_ALIGNMENT);
		b.add(buttBox);

		quit = new JButton("Return");
		buttBox.add(quit);

		buttBox.add(Box.createRigidArea(new Dimension(30, 0)));

		go = new JButton("Start");
		buttBox.add(go);

		setVisible(true);
		repaint();

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				reset();

			}
		});

		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();
				login();

			}
		});

	}

	// adds a black gradient as a background
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0, 0,
				0, 0), 0, getHeight(), new Color(0, 0.03f, .08f, 1f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	private void login() {
		
		if(newG.isSelected()){
			WodyetiaBifurcata.main(new String[]{serverName.getText()});
		}

		try {

			long t = System.currentTimeMillis();

			Registry registry = LocateRegistry.getRegistry();
			DragonHobbit s = (DragonHobbit) registry
					.lookup(serverName.getText());

			if (s != null) {
				sc.setServer(s);

				// failed login, do nothing
			} else {
				return;
			}

			System.out.println("Connected to Server " + serverName.getText()
					+ " (" + ((double) (System.currentTimeMillis() - t) / 1000)
					+ " s)");

			if (startup) {
				setVisible(false);
				sc.initBoard();

			}

			setVisible(false);

			name.setText("Player 1");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		name.setText("Player 1");
		setVisible(false);

	}
}
