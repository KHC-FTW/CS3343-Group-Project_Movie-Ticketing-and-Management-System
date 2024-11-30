
# Movie Ticketing and Management System
![image](https://github.com/user-attachments/assets/fd4165cb-36f4-44e0-9e3b-bc2323b09b23)

## Description
A console-based input/output Java program that supports movie ticketing and management functions. Depending on the role, such as guest, member, and admin, users can enjoy different functions such as movie listing, seat booking, user management, movie scheduling, etc.

## Installation

### 1. Build from source

1. Clone the repository:
   ```sh
   git clone https://github.com/KHC-FTW/CS3343-Group-Project_Movie-Ticketing-and-Management-System.git
   ```

2. cd into the project directory:
   ```sh
   cd CS3343-Group-Project_Movie-Ticketing-and-Management-System
   ```

3. Compile the source code. The following commands will compile the source code and place the compiled `.class` files in the `bin` directory:
   ```sh
   javac -classpath ./src ./src/release/Main.java -d ./bin
   ```

4. Run the program:
   ```sh
   java -classpath ./bin release.Main
   ```

### 2. Run from JAR

1. Clone the repository (omit if already followed step 1 above):
   ```sh
   git clone https://github.com/KHC-FTW/CS3343-Group-Project_Movie-Ticketing-and-Management-System.git
   ```

2. cd into the project release directory:
   ```sh
   cd CS3343-Group-Project_Movie-Ticketing-and-Management-System/release
   ```

3. Run the JAR file:
   ```sh
   java -jar "CityUCinemaSystem_V2.jar"
   ```
   or double click the `Autorun.bat` file (Windows operating system only)

## Usage guide

### 1. Important notes:
- In the option selection menu, user can enter the option value e.g. `1` or the name of the command (case-insensitive) e.g. `login` to execute the option
- Some commands allow user to enter `q` or `quit` to terminate the process early
- If the option menu is hidden, entering `l` or `list` can show the menu again

### 2. Suggested usage flow:
- Login as admin (username: `admin`; password: `password`)
- (At start, it is not suggested to use as guest or log in/register as member since admin has to first add and schedule movies)
- After logged in as admin, select `add movies` or `add default movies` (for quick test purposes)
- After successfully adding at least 5 valid movies, select `schedule movies` and choose a scheduling strategy
- And boom! The system has been successfully set up and you can now freely use as admin, log out as guest or log in/register as member to enjoy the full functionalities available in each user's option menu.
