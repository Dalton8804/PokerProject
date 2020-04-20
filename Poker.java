// Dalton Avery, Josh Mallari, Nick Wade
// Group Project
// Poker Game -- Poker.java
// 4.13.2020

import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Poker extends Application {
	static ArrayList <Card> cardList = new ArrayList<Card>();
	static Card[] currentHand = new Card[5];
	static ImageView[] imagesDisplayed = new ImageView[5];
	static Boolean[] discard = new Boolean[5];
	
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
		
		Line phantomOutLine = new Line(100,100,100,1000);
		
		PathTransition phantom = new PathTransition();
		phantom.setDuration(Duration.millis(1301));
		phantom.setPath(phantomOutLine);
		phantom.setNode(new Label ());
		
		// ***********************************************************
		// Adding draw and deal buttons to change the cards
		
		// THIS IS SHIT FIX IT
		
		HBox DrwDlBtns = new HBox();
		Button dealBtn = new Button("Deal");
		dealBtn.setOnAction(e -> {
			
			System.out.println(cardList.size());
			for (int i = 0; i<5; ++i) {
				exitAnim(currentHand[i].getCardImageView(), cardHBox);
				phantom.play();
				currentHand[i] = cardList.remove(i);
				imagesDisplayed[i].setImage(currentHand[i].getCardImage());
				phantom.setOnFinished(e -> {dealAnim(currentHand[i].getCardImageView(), cardHBox);});
			}
			if (cardList.size()<8) {
				setDeck();
			}
		});
		
		Button drawBtn = new Button("Draw");
		drawBtn.setOnAction(e -> {
			
				System.out.println(cardList.size());
				for (int i = 0; i<5; ++i) {
					if (discard[i]) {
						currentHand[i] = cardList.remove(i);
						imagesDisplayed[i].setImage(currentHand[i].getCardImage());
					}
				}
				if (cardList.size()<8) {
					setDeck();
				}
			
		});
		DrwDlBtns.getChildren().addAll(drawBtn, dealBtn);
		DrwDlBtns.setSpacing(50);
		// ***********************************************************
		
		for (int i =0; i< 5; ++i) {
			discard[i] = false;
		}
		
		// ***********************************************************
		imagesDisplayed[0].setOnMouseClicked(e -> {
			if (discard[0] == false)
				discard[0] = true;
			else
				discard[0] = false;
		});
		imagesDisplayed[1].setOnMouseClicked(e -> {
			if (discard[1] == false)
				discard[1] = true;
			else
				discard[1] = false;
		});
		imagesDisplayed[2].setOnMouseClicked(e -> {
			if (discard[2] == false)
				discard[2] = true;
			else
				discard[2] = false;
		});
		imagesDisplayed[3].setOnMouseClicked(e -> {
			if (discard[3] == false)
				discard[3] = true;
			else
				discard[3] = false;
		});
		imagesDisplayed[4].setOnMouseClicked(e -> {
			if (discard[4] == false)
				discard[4] = true;
			else
				discard[4] = false;
		});
		// ***********************************************************

		
				
		root.getChildren().addAll(cardHBox,DrwDlBtns);
		Scene scene = new Scene(root);
		
		primStage.setScene(scene);
		
        
        primStage.show();
	}
	
	public void dealAnim(ImageView a, Pane p) { //Executes the dealing entry animation of the cards
		PathTransition pt = new PathTransition(Duration.millis((int)(Math.random()*1200+2000)),new Line(100,p.getHeight()-1000,p.getWidth()-900,p.getHeight()-150),a);
		pt.play();
		
	}
	
	public void exitAnim(ImageView a, Pane p) { //Executes the exit animation of the cards
		a.setImage(new Image("file:./card/b2fv.png"));
		PathTransition pt1 = new PathTransition(Duration.millis((int)(Math.random()*800+650)),new Line(a.getX()+100,a.getY()+150,a.getX()+100,p.getHeight()+1000),a);
		pt1.play();
		
		pt1.setOnFinished(e -> {
			a.setX(p.getWidth());
			a.setY(p.getHeight());
			p.getChildren().remove(a);
		});
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
