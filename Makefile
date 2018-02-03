all:
	find . -name "*.java" > src.txt
	javac -cp sourcepath @src.txt
	java avaj/weather/Simulator avaj/scenario.txt
	rm src.txt
