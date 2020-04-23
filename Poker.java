// Dalton Avery, Josh Mallari, Nick Wade
// Group Project
// Poker Game -- Poker.java
// 4.13.2020


import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Poker extends Application {
	static ArrayList <Card> cardList = new ArrayList<Card>();
	static Card[] currentHand = new Card[5];
	static ImageView[] imagesDisplayed = new ImageView[5];
	static Boolean[] discard = new Boolean[5];
	static double bankAmount = 200;
	static double betAmount = 0;
	
	@Override
	public void start(Stage primStage) throws Exception {
		
		// main pane
		Pane root = new VBox();
		
			
		// ***********************************************************
		HBox bankNdBetting = new HBox();
		Text bankText = new Text("Bank: ");
		bankText.setFill(Color.NAVAJOWHITE);
		Text bankAmountText = new Text("$"+bankAmount);
		bankAmountText.setFill(Color.NAVAJOWHITE);
		bankText.setFont(Font.font(20));
		bankAmountText.setFont(Font.font(20));
		
		// Setting up radio buttons for placing bets
		HBox radioBettingButs = new HBox();
		radioBettingButs.setAlignment(Pos.CENTER_RIGHT);
		radioBettingButs.setPadding(new Insets(3,0,0,600));
		VBox bet = new VBox();
		TextField betAmtField = new TextField();
		betAmtField.setStyle("-fx-background-color: navajowhite");
		RadioButton bet1 = new RadioButton("$1");
		RadioButton betAll = new RadioButton("MAX");
		bet1.setStyle("-fx-selected-color: navajowhite");
		bet1.setStyle("-fx-text-fill: navajowhite");
		betAll.setStyle("-fx-text-fill: navajowhite");
		Text betAmtText = new Text("Enter bet:");
		betAmtText.setFill(Color.NAVAJOWHITE);
		ToggleGroup betButs = new ToggleGroup();
		
		bet1.setOnMouseClicked(e -> {betAmtField.setText("1");});
		betAll.setOnMouseClicked(e -> {String temp = String.format("%.2f", bankAmount);
				betAmtField.setText(temp);});
		
		bet1.setToggleGroup(betButs);
		betAll.setToggleGroup(betButs);
		bet.getChildren().addAll(betAmtText,betAmtField,betAll,bet1);
		bet.setMaxWidth(75);
		radioBettingButs.getChildren().addAll(bet);
				
		bankNdBetting.getChildren().addAll(bankText , bankAmountText, radioBettingButs);
		
		// ***********************************************************
		// initializes beginning hands
		for (int i = 0; i<5; ++i) {
			currentHand[i] = cardList.remove(i);
			imagesDisplayed[i] = currentHand[i].getCardImageView();
		}
		//Hbox for cards
		HBox cardHBox = new HBox();
		
		cardHBox.getChildren().addAll(imagesDisplayed[0],
				imagesDisplayed[1],
				imagesDisplayed[2],
				imagesDisplayed[3],
				imagesDisplayed[4]
				);
		// ***********************************************************
		
		Line phantomOutLine = new Line(0,0,300,300);
		PathTransition phantom = new PathTransition();
		phantom.setDuration(Duration.millis(2000));
		phantom.setPath(phantomOutLine);
		phantom.setNode(new Label ());
		
		// ***********************************************************
		// Adding draw and deal buttons to change the cards
		
		
		HBox DrwDlBtns = new HBox();
		Button dealBtn = new Button("Deal");
		dealBtn.setOnAction(e -> {
			for (int i=0; i<5; ++i)
				discard[i] = false;
			System.out.println(cardList.size());
			for (int i = 0; i<5; ++i) {
				
				imagesDisplayed[i].setImage(new Image("file:./card/b2fv.png"));
				
				PathTransition out = new PathTransition();
				out.setNode(imagesDisplayed[i]);out.setPath(new Line(85,150,85,1000));
				out.setDuration(Duration.millis(1000)); out.play();
				//phantom.play();
				currentHand[i] = cardList.remove(i);
				System.out.println(currentHand[i].getCardValue() + " " + currentHand[i].getCardFace());
				
				
				//imagesDisplayed[i].setImage(currentHand[i].getCardImage());
				final int temp = i;
				out.setOnFinished(f -> {imagesDisplayed[temp].setImage(currentHand[temp].getCardImage());
				PathTransition in = new PathTransition();
				in.setNode(imagesDisplayed[temp]);in.setPath(new Line(85,0,85,150));
				in.setDuration(Duration.millis(1000)); in.play();
				});
				
			}
			if (cardList.size()<8) {
				setDeck();
			}
		});
		
		
		Button drawBtn = new Button("Draw");
		drawBtn.setOnAction(e -> {
			phantom.play();
			
			try {
			betAmount = Double.parseDouble(betAmtField.getText());
			}
			catch (Exception asdf) {
				System.out.println("Make a bet");
			}
			bankAmount -= betAmount;
			bankAmountText.setText("$"+(bankAmount));
			
			
			System.out.println(cardList.size());
			for (int i = 0; i<5; ++i) {
				if (discard[i]) {
				discard[i] = false;
				imagesDisplayed[i].setImage(new Image("file:./card/b2fv.png"));
				
				PathTransition out = new PathTransition();
				out.setNode(imagesDisplayed[i]);out.setPath(new Line(85,150,85,1000));
				out.setDuration(Duration.millis(1000)); out.play();
				phantom.play();
				currentHand[i] = cardList.remove(i);
				System.out.println(currentHand[i].getCardValue() + " " + currentHand[i].getCardFace());
				
				
				//imagesDisplayed[i].setImage(currentHand[i].getCardImage());
				final int temp = i;
				out.setOnFinished(f -> {imagesDisplayed[temp].setImage(currentHand[temp].getCardImage());
				PathTransition in = new PathTransition();
				in.setNode(imagesDisplayed[temp]);in.setPath(new Line(85,0,85,150));
				in.setDuration(Duration.millis(1000)); in.play();
				
					});
				}
				if (cardList.size()<8) {
					setDeck();
				}
			}
			phantom.setOnFinished(q -> {isWinningHand(); bankAmountText.setText("$"+bankAmount);});
		});
		
		drawBtn.setMinHeight(50);drawBtn.setMinWidth(125);
		dealBtn.setMinHeight(50);dealBtn.setMinWidth(125);
		drawBtn.setStyle("-fx-background-color: navajowhite");
		dealBtn.setStyle("-fx-background-color: navajowhite");
		DrwDlBtns.setAlignment(Pos.CENTER);
		DrwDlBtns.getChildren().addAll(drawBtn, dealBtn);
		DrwDlBtns.setSpacing(50);
		
		// ***********************************************************
		
		for (int i =0; i< 5; ++i) {
			discard[i] = false;
		}
		
		// ***********************************************************
		imagesDisplayed[0].setOnMouseClicked(e -> {
			
			if (discard[0] == false) {
				imagesDisplayed[0].setImage(new Image("file:./card/b2fv.png"));
				discard[0] = true;
			}
			else {
				discard[0] = false;
				imagesDisplayed[0].setImage(currentHand[0].getCardImage());
			}
		});
		imagesDisplayed[1].setOnMouseClicked(e -> {
			if (discard[1] == false) {
				imagesDisplayed[1].setImage(new Image("file:./card/b2fv.png"));
				discard[1] = true;
			}
			else {
				discard[1] = false;
				imagesDisplayed[1].setImage(currentHand[1].getCardImage());
			}
		});
		imagesDisplayed[2].setOnMouseClicked(e -> {
			if (discard[2] == false) {
				imagesDisplayed[2].setImage(new Image("file:./card/b2fv.png"));
				discard[2] = true;
			}
			else {
				discard[2] = false;
				imagesDisplayed[2].setImage(currentHand[2].getCardImage());
			}
		});
		imagesDisplayed[3].setOnMouseClicked(e -> {
			if (discard[3] == false) {
				imagesDisplayed[3].setImage(new Image("file:./card/b2fv.png"));
				discard[3] = true;
			}
			else {
				discard[3] = false;
				imagesDisplayed[3].setImage(currentHand[3].getCardImage());
			}
		});
		imagesDisplayed[4].setOnMouseClicked(e -> {
			if (discard[4] == false) {
				imagesDisplayed[4].setImage(new Image("file:./card/b2fv.png"));
				discard[4] = true;
			}
			else {
				discard[4] = false;
				imagesDisplayed[4].setImage(currentHand[4].getCardImage());
			}
		});
		// ***********************************************************
		for (int i=0; i<5; ++i) {
			imagesDisplayed[i].fitHeightProperty().bind(root.heightProperty().subtract(200));
			imagesDisplayed[i].fitWidthProperty().bind(root.widthProperty().divide(5));
		}
		
		// ***********************************************************
				// Text telling user to click cards to discard
		Text clickCardsToDiscardText = new Text("Click Cards to Discard");
		VBox discardTextBox = new VBox();
		clickCardsToDiscardText.setFill(Color.NAVAJOWHITE);
		discardTextBox.setAlignment(Pos.CENTER);
		discardTextBox.getChildren().add(clickCardsToDiscardText);
		
		
		
		// ***********************************************************
		root.getChildren().addAll(cardHBox,discardTextBox,DrwDlBtns,bankNdBetting );
		Scene scene = new Scene(root, 850,500);
		root.setStyle("-fx-background-color: green");
		primStage.setScene(scene);
		imagesDisplayed[0].getX();
        
        primStage.show();
	}
	
	public static void isWinningHand() {
		String hand = checkHand.checkWinner(currentHand);
		switch(hand) {
			case "RoyalFlush" : bankAmount += (betAmount * 800); break;
			case "StraightFlush": bankAmount += (betAmount * 200);break;
			case "FourOfaKind" : bankAmount += (betAmount * 100); break;
			case "FullHouse" : bankAmount += (betAmount * 50); break;
			case "Flush" : bankAmount += (betAmount * 20); break;
			case "Straight" : bankAmount += (betAmount * 7); break;
			case "ThreeOfaKind" : bankAmount += (betAmount * 5); break;
			case "TwoPair" : bankAmount += (betAmount * 3); break;
		}
	}
	
	public static void main(String args[]) {
		setDeck();
		launch(args);
	}
	
	// Initializes the Deck
	private static void setDeck() {
		cardList.clear();
		for (int i = 1; i<=54; ++i)
			cardList.add(new Card(i));
		shuffleDeck();
		System.out.println("Deck Set");
	}
	
	// This method simply shuffles the deck -- Dalton Avery
	private static void shuffleDeck() {
		java.util.Collections.shuffle(cardList);
	}
}
