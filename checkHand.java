class checkHand {
	static Card[] hand = new Card[5];
	static boolean Joker = false;
	static boolean Joker2 = false;
	// *************************************************************done
	static String checkWinner(Card[] newhand) {
		
		for (int i =0; i<=4; ++i)
			hand[i] = newhand[i];
		sortCardsByValue();
		String winType;
		
		if (isRoyalFlush())
			winType = "RoyalFlush";
		else if (isStraightFlush())
			winType = "StraightFlush";
		else if (isFourOfaKind())
			winType = "FourOfaKind";
		else if (isFullHouse())
			winType = "FullHouse";
		else if (isFlush())
			winType = "Flush";
		else if (isStraight())
			winType = "Straight";
		else if (isThreeOfaKind())
			winType = "ThreeOfaKind";
		else if (isTwoPair())
			winType = "TwoPair";
		else
			winType = "";
		
		return winType;
	}
	
	static boolean isRoyalFlush(){
		if ((hand[0].getCardValue() == 10) && isStraightFlush())
			return true;
		return false;
	}
	// *************************************************************done
	// Checks if hand is a straight flush
	static boolean isStraightFlush(){
		if (isFlush() && isStraight()) {
			System.out.println("StraightFlush");return true;
		}
		return false;
	}
	// *************************************************************
	// Check if hand contains 4 of a kind
	static boolean isFourOfaKind(){
		if (isThreeOfaKind() && Joker)
			return true;
		if (hand[0].getCardValue() != hand[1].getCardValue()) {
			for (int i = 1; i<4; ++i) {
				if (hand[i].getCardValue() != hand[i+1].getCardValue())
					return false;
			}
			return true;
		}
		else {			
			for (int i = 0; i<3; ++i) {
				if (hand[i].getCardValue() != hand[i+1].getCardValue())
					return false;
			}
			return true;
		}
	}
	// *************************************************************done
	// Checks if hand is a Full House
	static boolean isFullHouse(){
		if (isThreeOfaKind() && isTwoPair()) {
			System.out.println("FullHouse");return true;
		}
		return false ;
	}
	// *************************************************************done
	// checks if hand is a flush
	static boolean isFlush(){
		String temp = hand[0].getCardFace();
		for (int i = 1; i<=4; ++i) {
			if (!hand[i].getCardFace().equals(temp))
				return false;
		}
		System.out.println("Flush");
		return true;
	}
	// *************************************************************add joker shit
	// checks hand for a straight
	static boolean isStraight(){
		sortCardsByValue();
		for (int i=0; i<4; ++i) {
			if (!hand[i].isAce()) {
				if (hand[i].getCardValue() != hand[i+1].getCardValue()-1)
					return false;
			}
			else if (hand[4].isAce())
				if (hand[3].getCardValue() != 13)
					return false;
				
		}
		System.out.println("Straight");
		return true;
	}
	// *************************************************************
	// checks hand for 3 of a kind
	static boolean isThreeOfaKind(){
		
		return isOnePair();//isOnePair();
	}
	// *************************************************************
	// checks hand for 2 pair
	static boolean isTwoPair(){
		return isOnePair();//isOnePair();
	}
	// *************************************************************
	// checks for a pair
	static boolean isOnePair(){
		if (Joker)
			return true;
		for (int i=0; i<=3; ++i)
			for (int j=i+1; j<=4;++j)
				if (hand[i].getCardValue()==hand[j].getCardValue()) {
					System.out.println("pair"); 
					return true;
				}
		return false;
	}
	// *************************************************************
	// Sorts the cards by the face value
	static void sortCardsByValue() {
		for (int i=0; i<5; ++i)
			if (hand[i].getCardValue() == 99) {
				if (!Joker)
					Joker = true;
				else 
					Joker2 = true;
			}
		 // One by one move boundary of unsorted subarray 
        for (int i = 0; i < 4; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_index = i; 
            for (int j = i+1; j < 5; j++) 
                if (hand[j].getCardValue() < hand[min_index].getCardValue()) 
                    min_index = j; 
  
            // Swap the found minimum element with the first 
            // element 
            Card temp = hand[min_index]; 
            hand[min_index] = hand[i]; 
            hand[i] = temp; 
        } 
		
	}
}