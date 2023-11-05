build:
	javac Main.java

run: build
	java Main

clean:
	rm -r *.class
