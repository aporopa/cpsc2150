JFLAGS = --release 11
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	cpsc2150/extendedConnectX/models/AbsGameBoard.java \
	cpsc2150/extendedConnectX/models/GameBoard.java \
	cpsc2150/extendedConnectX/models/GameBoardMem.java \
	cpsc2150/extendedConnectX/models/IGameBoard.java \
	cpsc2150/extendedConnectX/GameScreen.java \
	cpsc2150/extendedConnectX/models/BoardPosition.java

default: classes

classes: $(CLASSES:.java=.class)

run: cpsc2150/extendedConnectX/GameScreen.class cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class
	java cpsc2150/extendedConnectX/GameScreen cpsc2150/extendedConnectX/models/IGameBoard cpsc2150/extendedConnectX/models/AbsGameBoard cpsc2150/extendedConnectX/models/GameBoard cpsc2150/extendedConnectX/models/GameBoardMem cpsc2150/extendedConnectX/models/BoardPosition
clean:
	$(RM) cpsc2150/extendedConnectX/*.class
	$(RM) cpsc2150/extendedConnectX/models/*.class

test: cpsc2150/extendedConnectX/models/TestGameBoard.java  cpsc2150/extendedConnectX/models/TestGameBoardMem.java 
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/models/TestGameBoard.java cpsc2150/extendedConnectX/models/TestGameBoardMem.java

testGB: cpsc2150/extendedConnectX/models/TestGameBoard.java
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoard

testGBmem: cpsc2150/extendedConnectX/models/TestGameBoardMem.java
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoardMem