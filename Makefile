JC = javac
LD = jar

PACKAGES = graph

DIRS = $(foreach PACKAGE, $(PACKAGES), src/$(PACKAGE)) $(foreach SRC, src, $(SRC))
SRC = $(foreach DIR, $(DIRS), $(wildcard $(DIR)/*.java))
CLASS = $(foreach DIR, $(DIRS), $(wildcard $(DIR)/*.class))

MF = META-INF/MANIFEST.MF

EXE = app

$(EXE): $(CLASS)
	$(LD) -c -f $(EXE).jar -m $(MF) $?

$(CLASS):
	@$(JC) $(SRC)

clean:
	$(RM) $(CLASS)

#all: $(CLASS) $(EXE) clean

.PHONY: clean
