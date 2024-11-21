# CS3343-Group-Project_Movie-Ticketing-and-Management-System
CS3343 Group Project 24-25

## Run from JAR
1. Clone the repository
    ```bash
    git clone https://github.com/KHC-FTW/CS3343-Group-Project_Movie-Ticketing-and-Management-System.git
    ```
2. `cd` into the project directory
    ```bash
    cd CS3343-Group-Project_Movie-Ticketing-and-Management-System
    ```
3. Run the JAR file
    ```bash
    java -jar ./release/CityUCinemaSystem_V2.jar
    ```

## Build from source
1. Clone the repository
    ```bash
    git clone https://github.com/KHC-FTW/CS3343-Group-Project_Movie-Ticketing-and-Management-System.git
    ```
2. `cd` into the project directory
    ```bash
    cd CS3343-Group-Project_Movie-Ticketing-and-Management-System
    ```
3. Compile the source code.
    The following commands will compile the source code and place the compiled `.class` files in the `bin` directory.
    ```bash
    javac -classpath ./src ./src/release/Main.java -d ./bin
    ```
4. Run the program
    ```bash
    java -classpath ./bin release.Main
    ```

