JC = javac
LD = jar

PACKAGES = dijkstra maze ui graphics

DIRS = $(foreach PACKAGE, $(PACKAGES), src/$(PACKAGE)) $(foreach SRC, src, $(SRC))
SRC = $(foreach DIR, $(DIRS), $(wildcard $(DIR)/*.java))
CLASS = $(SRC:%.java=%.class)

MF = META-INF/MANIFEST.MF

EXE = app

$(EXE): $(CLASS)
	$(LD) -c -f $(EXE).jar -m $(MF) $?

$(CLASS):
	@$(JC) $(SRC)

clean:
	find . -type f | grep ".class$$" | xargs $(RM)

clean-doc:
	$(RM) -r doc/

clean-all: clean clean-doc

doc:
	javadoc -d doc $(SRC) 

#all: $(CLASS) $(EXE) clean

.PHONY: clean
