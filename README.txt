=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Appropriately modeling state using collections.
  	 	After careful consideration, I decide to choose collections to store my 
  	 objects in the game, which includes: the bullets (shot by the player), 
	 the enemies (Fast, Weak, Strong, Boss), the enemy bullets¡¡(shot by the
	 enemies), and the rewards (used to upgrade the plane). It is appropriate
	 to use LinkedLists to store these objects because:
	 
	 	(1) I need to iterate through the data structure to perform actions on
	 	each object. For example, when the life of an enemy is 0, remove it 
	 	from the list; when an enemy bullet hits the player, call the function
	 	plane.Injured(); when there is no collision, call .move() function to
	 	move every object.
	 	
	 	(2) The objects does not implement the Comparable interface, which means
	 	I cannot store them in a tree structure, so TreeSet is not a good choice.
	 	I could store them in other forms of Set structure, but LinkedList is 
	 	perfectly fine if I carefully add them to the list and avoid duplication.
  
  2. File I/O.
  		In my {Ranking.java} class, I use File I/O to implement a high score ranking.
  	 The input and output file is the 'record.txt' file. If people want to modify
  	 this file to clear the ranking, please remember to modify it in the form of:
  			User1 Score1
  			User2 Score2
  			User3 Score3
  		The "\n" and the " " are essential to avoid potential bugs, no extra spaces
  	 after the scores.
  	 	For your information, the scores are displayed in images, which uses the 
  	 class {Score.java}.
  	 	

  3. Inheritance/Subtyping for dynamic dispatch
  		Before I try to create my own JPanels, almost all the classes in my game
  	 extends the abstract class {AbleToFly.java}, it is similar to the provided 
  	 {GameObj.java}, but different in the following ways:
  	 	(1) Every instance of the class has a life attribute, and hence has some
  	 methods to get/modify its life.
  	 	(2) Except for the method {draw(Graphics g)}, every subclass of {AbleToFly.java}
  	 has to implement the method{shoot()}, which means they have different ways of
  	 attacking. Some subclasses cannot fire bullets, for example, {Bullects.java},
  	 some shoot in many ways, for example, {Boss.java}. I try to implement different
  	 ways of shooting in order to make the game trickier to play.
  	 	

  4. Using JUnit on a testable component of your game.
  		I use JUnit tests to check whether everything goes as I imagined. The tests
  	 are helpful to find bugs in my program.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  	Alphabetically,
	(1){AbleToFly.java} The base abstract class of the whole game. Provides basic 
	   functions to handle movement, collision and requires its subclasses to
	   override two functions. {draw(Graphics g)} and {shoot()}
	(2){Boss.java} A subclass of {Enemies.java}, has many ways of attacking 
	   and different kinds of bullets. Very strong and almost invincible.
	(3){Bullet.java} A subclass of {AbleToFly.java}, it cannot attack so the 
	   {shoot()} function returns null. There are many kinds of bullets,  which are
	   specified in the enum {BulletKind.java}
	(4){Direction.java} An enum class used to specify directions in the game.
	(5){Enemies.java} An abstract extension of the class {AbleToFly.java}, which
		overrides the method {draw(Graphics g)}. There are many kinds of Enemies.
	(6){EnemyKind.java} An enum class used to keep record of enemy type.
	(7){FastEnemies.java} A subclass of {Enemies.java}. The fast enemies move
		very fast, but cannnot attack. Therefore, there {shoot()} function returns
		an empty list.
	(8){Game.java} The main control of the game, it creates frames, panels, buttons,
	 	and link them together by specifying the action listeners.
	(9){GameCourt.java} A subclass of JPanel and has many helper functions to
		implement the court of the game, i.e. It has many timers to handle 
		enemy creation, shooting, scene updating, etc.
	(10){GamveOver.java} A subclass of {AbleToFly.java}, it has a score component
		used to show the user's final score after the game.
	(11){Map.java} A subclass of {AbleToFly.java}, it has a very different way of
		drawing itself because I need the background to be moving to create the 
		illusion that the player's plane is moving/
	(12){MyButton.java} A subclass of {JButton.java}, it overrides the 
		{paintComponent(Graphics g)} method to paint itself with pictures.
	(13){MyPanel.java} A subclass of {JPanel.java}, it overrides the 
		{paintComponent(Graphics g)} method to paint itself with pictures.
	(14){Plane.java} A subclass of {AbleToFly.java}. The player's plane has many
		kinds of bullets, is really strong and can update itself by catching
		rewards.
	(15){PlayerStatus.java} A subclass of {AbleToFly.java}. It shows the plane's 
		number of lives and its number of "Blasts".
	(16){Ranking.java} A subclass of {AbleToFly.java}. It is used to display the
		high score ranking using File I/O.
	(17){RecordPanel.java} A subclass of {JPanel.java}. It is a special kind of
		panels which has a Ranking object inside.
	(18){Reward.java} A subclass of {AbleToFly.java}, there are many kinds of rewards
		and they are used to update the player's plane.
	(19){RewardKind.java} An enum class used to specify the different kinds of rewards.
	(20){Score.java} A subclass of {AbleToFly.java}. It is used to display the running
		score, the final score, and the rankings using pictures.
	(21){StrongEnemies.java} A subclass of {Enemies.java}. The strong enemies move very
		slowly, but are very strong and shoot many bullets at one time. 
	(22){User_Interfaces.java} An enum class used to specify which panel is currently 
		displayed on the screen.
	(23){WeakEnemies.java} A subclass of {Enemies.java}. The weak enemies move slowly.
		and they shoot very few bullets.
	
- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
	The implementation is not very challenging. Every time I have a question, 
  StackOverFlow will help me to find the answer. I guess the most difficult one
  is how to make a running map as the background. I consulted a few TAs, but no 
  one knows about this.
  	After talking to Prof. Weirich, we find the answer to be using an overloaded 
  version of Graphics.drawImage. I guess this is the most difficult part.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
	
	I believe my program has a good separation of functionality and I properly 
  encapsulated many private states for each class. However, there are still many places 
  to improve. 
  
  	First of all, the whole game is built based on the provided game
  "Mushroom of Doom", which is a perfectly good game, but some of its classes has
  public static final variables. It is perfectly fine to do so since the variable are similar
  to static global variables in c++, but I have never accessed these variables outside the
  class. I did not notice this before I tried to summarize my work. I may change every variable
  into private ones if I have the chance to do it again. Or I should utilize the global 
  variables to avoid to repetitions.
  
 	Also, I did not have a thorough plan of how to make the game user interface before I started.
  The different panels and the buttons were added after I finished the main part of the game. If
  I were given another chance, I would change the classes {Ranking.java}, {GameOver.java} into 
  subclasses of JPanel instead of AbleToFly for consistency. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
		Some of the images, for example, the map, the buttons, the numbers, and
	the design of the name "RAIDEN", are "created" by myself, which means I did a lot of 
	PhotoShop work based on some simple resources.
    
		Most of the object images, for example, the planes, the bullets and the rewards, are 
	from a game called "QuanMinFeiJi". It's a Chinese game and I bought the images online
	from a studio.