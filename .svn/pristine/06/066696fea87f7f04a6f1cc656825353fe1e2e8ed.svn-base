package client;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	Image bg = null;
	Image title = null;
	Image[] pieceImgsRed = new Image[12];
	Image[] pieceImgsBlue = new Image[12];

	public Images() {

		loadImgs();
		
	}

	/** loads images from file for use in game */
	private void loadImgs() {
		try {

			bg = ImageIO.read(new File("rsc/imgs/boards/board.png")).getScaledInstance(
					(int) StrategoClient.BOARD_SIZE,
					(int) StrategoClient.BOARD_SIZE, Image.SCALE_SMOOTH);
			title = ImageIO.read(new File("rsc/imgs/title.png"));

			for (int i = 0; i != 12; i++) {
				pieceImgsRed[i] = ImageIO.read(new File("rsc/imgs/pieces/basic/" + i + "R.png"))
						.getScaledInstance((int) StrategoClient.SQUARE_SIZE,
								(int) StrategoClient.SQUARE_SIZE,
								Image.SCALE_SMOOTH);
				pieceImgsBlue[i] = ImageIO.read(new File("rsc/imgs/pieces/basic/" + i + "B.png"))
						.getScaledInstance((int) StrategoClient.SQUARE_SIZE,
								(int) StrategoClient.SQUARE_SIZE,
								Image.SCALE_SMOOTH);

			}

		} catch (IOException e) {
			System.out.println("Error loading images");
			e.printStackTrace();

		}
	}
	
	public Image bg(){
		return bg;
	}
	
	public Image title(){
		return title;
	}
	
	public Image pieceRed(int rank){
		return pieceImgsRed[rank];
				
	}
	
	public Image pieceBlue(int rank){
		return pieceImgsBlue[rank];
				
	}
}
