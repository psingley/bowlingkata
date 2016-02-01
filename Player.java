import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Arrays.*;

public class Player {

  private int rolls[] = new int[21];
  private int currentRoll = 0;
  private String[][] frames;
  boolean addBonusFrame = false;
  boolean bonusFrameStrike = false;
  boolean bonusFrameSpare = false;
  private int i=0;
  private int j=0;
  int prevScore;
    
  public Player(){
	  
  }
  
  public Player(String[][] frames){
	  this.setFrames(frames);

	  
	  while(i<10){
		  try{
			  while(j<2){
				  try{
					  int parsedRoll;
					  parsedRoll = Integer.parseInt(frames[i][j]);
					  if(parsedRoll<10&&parsedRoll>=0&&j==0){
						  roll(parsedRoll);
						  j++;
					  } else if(currentRoll!=0){
						  if((parsedRoll+rolls[currentRoll-1]<=9)&&j==1){
							  roll(parsedRoll);
							  j++;  
						  } else {
							  roll(0);
							  System.out.println(parsedRoll + " at line " + getFrame() + " and bowl " + getBowl() +  " is not a possible pin value. Calculated as gutter ball.");
							  j++;
						  }
					  } else {
						  roll(0);
						  System.out.println(parsedRoll + " at line " + getFrame() + " and bowl " + getBowl() +  " is not a possible pin value. Calculated as gutter ball.");
						  j++;
					  }
				  } catch (Exception e){
					  if(frames[i][j]=="X"||frames[i][j]=="/"&&i<9){
						  if(frames[i][j]=="X"&&j==0){
							  roll(10);
							  j++;
						  } else if(frames[i][j]=="/"&&j==1&&i<9&&frames[i][0]!="X"){
							  int frameIsASpareMath;
							  frameIsASpareMath = 10 - rolls[currentRoll-1];
							  roll(frameIsASpareMath);
							  j++;
						  } else {
							  if(i==9){
								  if(frames[i][0]=="X"&&frames[i][1]!="/"){
									  roll(10);
									  addBonusFrame=true;
									  j++;
								  } else if(frames[i][0]!="X"&&frames[i][j]=="/"&&j==1) {
									  int frameIsASpareMath;
									  frameIsASpareMath = 10 - rolls[currentRoll-1];
									  roll(frameIsASpareMath);
									  addBonusFrame=true;
									  j++;
								  } else {
									  System.out.println( frames[i][j] + " at line " + getFrame() + " , bowl " + getBowl() +  " is not a possible pin value. Calculated as gutter ball.");
									  roll(0);
									  j++;  
								  }
							  } else {
								  System.out.println( frames[i][j] + " at line " + getFrame() + " , bowl " + getBowl() +  " is not a possible pin value. Calculated as gutter ball.");
								  roll(0);
								  j++;  
							  }
						  }
					  } else if(frames[i][0]!="X"&&frames[i][j]=="/"&&j==1&&i==9) {
						  int frameIsASpareMath;
						  frameIsASpareMath = 10 - rolls[currentRoll-1];
						  roll(frameIsASpareMath);
						  addBonusFrame=true;
						  j++;
					  } else {
						  System.out.println( frames[i][j] + " at line " + getFrame() + " , bowl " + getBowl() +  " is not a possible pin value. Calculated as gutter ball.");
						  roll(0);
						  j++;  						  
					  }
				  }
			  }
		  } catch (Exception e){
			  j++;
		  }
		  bonusBowl();
		  j=0;
		  i++;
	  }
  }
  
  
  public void roll(int pins) {
    rolls[currentRoll++] = pins;
  }

  public int score() {
    int score = 0;
    int frameIndex = 0;
    for (int frame = 0; frame < 10; frame++) {
      if (isStrike(frameIndex)) {
        prevScore = score;
    	score += 10 + strikeBonus(frameIndex);
        if(score!=prevScore){
        	System.out.println("Strike!, score is: " + score);	
        } else {
        	System.out.println("Booooo. Gutterball!");
        }
        frameIndex++;
      } else if (isSpare(frameIndex)) {
        prevScore = score;
    	score += 10 + spareBonus(frameIndex);
    	if(score!=prevScore){
        	System.out.println("Spare!, score is: " + score);	
        } else {
        	System.out.println("Booooo. Gutterball!");
        }
        frameIndex += 2;
      } else {
        prevScore = score;
    	score += sumOfBallsInFrame(frameIndex);
    	if(score!=prevScore){
        	System.out.println("score is: " + score);	
        } else {
        	System.out.println("Booooo. Gutterball!");
        }
        frameIndex += 2;
      }
    }
    return score;
  }

  private boolean isStrike(int frameIndex) {
    return rolls[frameIndex] == 10;
  }

  private int sumOfBallsInFrame(int frameIndex) {
    return rolls[frameIndex] + rolls[frameIndex+1];
  }

  private int spareBonus(int frameIndex) {
    return rolls[frameIndex+2];
  }

  private int strikeBonus(int frameIndex) {
    return rolls[frameIndex+1] + rolls[frameIndex+2];
  }

  private boolean isSpare(int frameIndex) {
    return rolls[frameIndex]+rolls[frameIndex+1] == 10;
  }

  public String[][] getFrames() {
	return frames;
  }

  public void setFrames(String[][] frames) {
	this.frames = frames;
  }

  private int getFrame(){
	return i+1; 
  }
  
  private int getBowl(){
		return j+1; 
  }
  
  private void bonusBowl(){
	  try{
		  if(addBonusFrame){
			  try{
				  int parsedRoll;
				  parsedRoll = Integer.parseInt(frames[9][2]);
				  if(parsedRoll<10&&parsedRoll>=0){
					  roll(parsedRoll);
					  j++;
				  } else {
					  roll(0);
					  System.out.println(parsedRoll + " at line " + getFrame() + " , bowl " + getBowl() +  " is not a possible pin value. Calculated as gutter ball.");
					  j++;
				  }
			  } catch (Exception e){
				  if(frames[9][2]=="X"){
					  roll(10);
					  bonusFrameStrike = true;
				  } else if(frames[9][2]=="/"&&(Integer.parseInt(frames[9][1])<=9)){
					  int spareMath = 10-Integer.parseInt((frames[9][1]));
					  roll(spareMath);
					  bonusFrameSpare = true;
				  }
			  }
		  } 
	  } catch(Exception e){
		  System.out.println("Bonus roll triggered but did not contain valid pin content");
	  }	  
  }
  
}