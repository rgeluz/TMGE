# INF122-TMGE

**How To Run**
- Clone or Download the "TMGE" project from Github.
- In your Terminal/CommandPrompt, navigate to the "OfficialJarToRun" directory and run this:
-java --module-path [path to javafx]/lib --add-modules=javafx.controls,javafx.fxml -jar TMGE_WithoutModules.jar
- //Need to replace [path] with path to your locally installed javafx library.
- //If you dont have javafx library. Go to https://gluonhq.com/products/javafx/ and look version 13 or 14.
- //In order for the TMGE application to run properly, the "resources" folder inside "OfficialJarToRun" folder needs to be present.

## Description 
- Design and implement an extensible Tile-Matching Game Environment (TMGE). We will adhere to the definition of a tile-matching game as used in this Wikipedia article (Links to an external site.).
- With a team of 8 or 9

**Requirements**
- The TMGE should accommodate any tile-matching game that involves a grid layout and game elements on this layout, including games such as Tetris, Klax, Bejeweled, Bust-a-Move, Puzzle Bobble, Candy Crush, Dr. Mario, Puzzle Fighter, etc.
- The TMGE should make it as easy as possible to create implementations of new games.
- The TMGE should provide a defined interface that all games built on top of the environment must follow.
- The TMGE should support two players running on the same local machine.
  -	If your game supports networked multiplayer, you can get up to 5% extra credit for this project.
- The TMGE should support personal player profiles (the specifics of which are up to you). Login can be very simple and does not have to be secure.
- The TMGE need only support 2-player games (but you can support more players if you want to).
- The TMGE should work by providing a player with a list of games they can play and allow them to choose which one to start.
- The TMGE  should be written in Java with modules specified and implemented using the Java Platform Module System.
  - Up to 5% extra credit for this assignment if you use Java modules that consume or provide services, by leveraging the uses and provides directives, in a sensible and effective manner
- The TMGE should only expose parts of itself necessary to build a game, hiding the internals of the TMGE that need not be used or extended by a game directly.
- The TMGE  only needs to support running one game at a time.

**Deliverables**
- The TMGE itself
- Two or more games from the list above that are "built on top of" the TMGE.
- Documentation
- Instructions for running the game
- Code and runnable jar (via a CM repository like GitHub)
- Peer evaluations (will be made available to you)

**Reuse**
- Cannot pick up an existing game environment implementation
- You can reuse other components, but first, double-check with the professor
