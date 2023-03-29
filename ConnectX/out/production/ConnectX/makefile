JFLAGS = --release 11
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	cpsc2150/extendedConnectX/models/AbsGameBoard.java \
	cpsc2150/extendedConnectX/models/BoardPosition.java \
	cpsc2150/extendedConnectX/models/GameBoard.java \
	cpsc2150/extendedConnectX/GameScreen.java \
	cpsc2150/extendedConnectX/models/IGameBoard.java \
	cpsc2150/extendedConnectX/models/GameBoardMem.java

default: classes

classes: $(CLASSES:.java=.class)

run: cpsc2150/extendedConnectX/GameScreen.class
	java cpsc2150.extendedConnectX.GameScreen

clean:
	$(RM) cpsc2150/extendedConnectX/*.class