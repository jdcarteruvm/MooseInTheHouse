# MooseInTheHouse
CS205 Group Project 2015


3/19: Initial files: Everything in the parent directory is stuff from the first deliverable. Handy for reference, I guess. Some of the graphics will likely be useful as well.

3/19: I added image files for all the cards in the resources folder. They're huge - 300dpi! But that allows us to resize them as we wish when we figure out how big the cards will be in the GUI.

We should probably consider keeping this file up to date so we have good notes of development progress when it comes time to write up our different deliverables.

3/26: (Joey) Added class files for MITH_Card and MITH_Deck, each with initial methods and constructors working properly. 

3/28: (Joey) Added class file for MITH_Hand, with constructor and initial methods addCard() and removeCard() working properly. Class MITH_Deck needs reset().

3/30: (Jon) changed the card types and room types to public static final values so that other classes can use them for comparison purposes. (ie: otherCard.getType() == MITH_Card.MITH or otherCard.getRoom() == MITH_Card.LIVING)

3/30: (Jon) I added a class to our heirarchy called MITH_House because designing player/game interactions without something like this was a pain in the neck 
			Note the main method with test cases (ongoing at this point) as a model of the kind of documented testing we can reference in our deliverable due Wednesday. We should have something similar for each class in our program - excluding GUI classes.
 
4/15: (Jon) I added an updated GUI class that shows some rudimentary card objects - more of a hack than anything - also added a GUI_test outline to help guide GUI development

4/19: (Jon) I updated the GameBoardTest class. It's now linked to a deck so it is essentially a prototype for our draw pile. It's a little wonky if you don't drag the card to the right place, but that's because the default action I envision in the situation, adding it to the hand, doesn't exist yet. Also updated Card and House and some of the pictures for consistent so the GUI can grab the pictures.