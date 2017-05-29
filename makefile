JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java .class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Brute.java \
	Fast.java \
	Point.java \
	StdDraw.java

all: 
	$(classes)

classes: 
	$(CLASSES:.java=.class)	

clean:
	$(RM) *.class