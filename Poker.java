// Dalton Avery, Josh Mallari, Nick Wade
// Group Project
// Poker Game -- Poker.java
// 4.13.2020

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Poker extends Application {
	static ArrayList <Card> cardList = new ArrayList<Card>();
	static Card[] currentHand = new Card[5];
	static ImageView[] imagesDisplayed = new ImageView[5];
	
	@Override
	public void start(Stage primStage) throws Exception {
		
		Pane root = new VBox();
				
		// ***********************************************************
		// TO BE REMOVED (maybe) creates an initial hand-- Dalton
		for (int i = 0; i<5; ++i) {
			currentHand[i] = cardList.remove(i);
			imagesDisplayed[i] = currentHand[i].getCardImageView();
		}
		
		HBox cardHBox = new HBox();
		
		cardHBox.getChildren().addAll(imagesDisplayed[0],
				imagesDisplayed[1],
				imagesDisplayed[2],
				imagesDisplayed[3],
				imagesDisplayed[4]
				);
		// ***********************************************************
		
		// ***********************************************************
		// Adding draw and deal buttons to change the cards
		HBox DrwDlBtns = new HBox();
		Button dealBtn = new Button("Deal");
		dealBtn.setOnAction(e -> {
			
			System.out.println(cardList.size());
			for (int i = 0; i<5; ++i) {
				currentHand[i] = cardList.remove(i);
				imagesDisplayed[i].setImage(currentHand[i].getCardImage());
			}
			if (cardList.size()<8) {
				setDeck();
			}
		});
		
		Button drawBtn = new Button("Draw");
		drawBtn.setOnAction(e -> {
			
			System.out.println(cardList.size());
			for (int i = 0; i<5; ++i) {
				currentHand[i] = cardList.remove(i);
				imagesDisplayed[i].setImage(currentHand[i].getCardImage());
			}
			if (cardList.size()<8) {
				setDeck();
			}
		});
		DrwDlBtns.getChildren().addAll(drawBtn, dealBtn);
		DrwDlBtns.setSpacing(50);
		// ***********************************************************
		
				
		root.getChildren().addAll(cardHBox,DrwDlBtns);
		
		Scene scene = new Scene(root);
		primStage.setScene(scene);
		
        
        primStage.show();
	}
	
	public static void main(String args[]) {
		setDeck();
		launch(args);
	}
	
	
	// Initializes the Deck
	private static void setDeck() {
		cardList.clear();
		for (int i = 0; i<52; ++i)
			cardList.add(new Card(i+1));
		shuffleDeck();
		System.out.println("Deck Set");
	}
	
	
	// This method simply shuffles the deck -- Dalton Avery
	private static void shuffleDeck() {
		java.util.Collections.shuffle(cardList);
	}
}
