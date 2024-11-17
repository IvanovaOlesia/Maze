
install:
	@echo "Maze install	..."
	@mvn clean javafx:jlink
	@echo "Maze install	OK"

run:
	@echo "Maze run ..."
	@./target/myapp/bin/maze
	@echo "Maze run	OK"