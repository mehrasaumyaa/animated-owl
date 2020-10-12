/*Name : Saumyaa Mehra
 
 * Description : Program to draw an owl with new features added to it
 -- OWL PROJECT*/

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import acm.util.*;

public class Richie extends GraphicsProgram
{	
	
	private GLine leftBeak;					//left diagonal of beak
	private GLine rightBeak; 				//right diagonal of beak
	private GOval face;								
	private GLine leftEar1;					//left  diagonal of left ear of owl
	private GLine leftEar2;					//right diagonal of left ear of owl
	private GLine rightEar1;				//left diagonal of right ear of the owl
	private GLine rightEar2;				//right diagonal of right ear of the owl
	private Eye rightEye, leftEye;	//right and left eyes of owl respectively

	SwingTimer t;									//timer for extension of ears
	int timerCount=0;							//timerCount for the blinking of eyes

	int numClicks=0; 							//to count the number of clicks
	
	GPolygon cross1 = new GPolygon();
	GPolygon cross2 = new GPolygon();		//parts of the cross that'll be displayed after 10 clicks
	
	
	@Override
	 public void run()
	 {
	
		face = new GOval( 20,20, 60, 40); 
		rightEye= new Eye();
		leftEye= new Eye();
		rightEye.setLocation(60,32);
	  leftEye.setLocation(40,32);
		
		GLine nose1 = new GLine(45,47,50,52);			//left diagonal of nose
		GLine nose2 = new GLine(50,52,55,47);			//right diagonal of nose 
		leftEar1 = new GLine (30,24,33,15);		
	  leftEar2= new GLine (37,21,33,15);
	 	rightEar1= new GLine(64,21,68,15);
	  	rightEar2= new GLine(71,24,68,15);
	  	
	  leftBeak = new GLine( 45,47,50,48);
	  	rightBeak= new GLine(50,48,55,47);	
	  	
	  	cross1.addVertex(60,40);
	  	cross1.addVertex(40,60);
	  cross1.addVertex(120,140);
	  	cross1.addVertex(140,120); 	
	  	cross2.addVertex(120,40);
	  	cross2.addVertex(140,60);
	  	cross2.addVertex(60,140);
	  	cross2.addVertex(40,120);
	  	
	  	Color brown=new Color(100,50,0);
	  	cross1.setFilled(true);
	  	cross1.setFillColor(brown);				//setting color of cross as brown since most owls are brown in color
	  	cross2.setFilled(true);
	  	cross2.setFillColor(brown);
	  	
	  	
		add(face);
		add(rightEye);
		add (leftEye);
		add (nose1);
		add (nose2);
		add (leftEar1);
		add (leftEar2);
		add(rightEar1);
		add(rightEar2);
		
		t= new SwingTimer(100,this);		
		t.start();
		
		addMouseListeners();
		
	 }
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		if(numClicks<=10)							//to stop all activity after 10 clicks
		{
		
			if(face.contains(e.getX(),e.getY()))
				{ 
					add(leftBeak);
					add(rightBeak);				 //beak appears whenever mouse is moved over the face
				}
		
			else 
				
				{  
					this.remove(leftBeak);
					this.remove(rightBeak);		//remove the beak as soon as mouse moves outside of the face
				}	
		}
	}

	@Override
	public void actionPerformed( ActionEvent e)
	{
		timerCount=timerCount+1;			//to increment timerCount by 1 at every timer tick after 100ms
		
		if(numClicks<=10)						//to stop all activity after 10 clicks
		{
			if(timerCount%10==0)				//if timercount is a multiple of 10, that is, at 1000ms(1s) or 2000ms(2s) and so on
			{ rightEye.EyesBlink();			//the eyes will blink
			leftEye.EyesBlink();
			}
			else
			{ 
				rightEye.EyesDontBlink();
				leftEye.EyesDontBlink(); 	
			}
				
			{
				leftEar1.setEndPoint(33,leftEar1.getEndPoint().getY()-2);
				leftEar2.setEndPoint(33,leftEar2.getEndPoint().getY()-2);
				rightEar1.setEndPoint(68,rightEar1.getEndPoint().getY()-2);
				rightEar2.setEndPoint(68,rightEar2.getEndPoint().getY()-2);
			}

		}	
		
	}

	@Override
	public void mousePressed( MouseEvent e)
	{
		
		numClicks=numClicks+1;						//counting the number of clicks
		
		if(numClicks<=10)
		{	
			rightEye.changingEyeColor(); 		//call to changingEyeColor() in 															
			leftEye.changingEyeColor();			//class Eye to smoothly change eye color from black to yellow
		}
		
		else
		{ 
			add(cross1);										//add the cross on the owl's face after 10 clicks to show that owl is tired
			add(cross2);
			return; 
		}
			
	}
	
}

