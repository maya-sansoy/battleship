import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    /*

    - declare 2d array - char
    - method should ask for how big the square board should be int board_size
    - should have board_size(eg 5) arrays in board size many (5) rows. (each row of 5 has 5 arrays)
    - int [][] board = new int[board_size][board_size]
    - how to print 2d array
        System.out.println(Arrays.deepToString(array));
    -

    - create function for placing ships (also create function to place ships)
        - ship()
            - checks for a random x/y cord

        - different types of ships that can be placed (different lengths)
    - choose randomly if they should be placed vertically or horizontally
    - check if there is enough space in the given direction
    - if not, randomly place again
    -----
    - ask user how many ships they would like to play with ()
    - create function to randomly choose a place on the board
    - max 5 ships, each ship in increasing size



 */
    private static final char EMPTY = '.';
    private static final char SHIP = '#';
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);



        System.out.println("how big would you like the square board to be? (enter one number)");
        int board_size = keyboard.nextInt();


        System.out.println();


        char[][] gameBoard = generateBoard(board_size);

        System.out.println("how many ships would you like on the board? (max 10)");
        int shipNumber = keyboard.nextInt();

        while (shipNumber > 10){
            System.out.println("sorry, the maximum is 10 ships. please choose again");
            shipNumber = keyboard.nextInt();
        }

        // change the maximum later to depend on board size


        for (int i = 1; i <= shipNumber; i++) {

            boolean found = false;
            for (int j = 1; j < 1000; j++) {
                if (ship(gameBoard, randomCoordinate(board_size), randomCoordinate(board_size), i, SHIP, direction())){
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("i couldn't fit " + shipNumber + " ships on the board");
                System.out.println();
            }


        }


        System.out.println(Arrays.deepToString(gameBoard).replace("], ", "\n").replace("[", "").replace("]", "").replace(",", ""));

    }

    private static Random rnd = new Random();

    public static int randomCoordinate(int board_size) {

        return rnd.nextInt(board_size);

    }


    public static char[][] generateBoard(int board_size) {

        char[][] board = new char[board_size][board_size];

        for (int i = 0; i < board_size; i++) {
            Arrays.fill(board[i], EMPTY);
        }

        return board;

    }

    // returns direction
    public static String direction() {
        double random = Math.random();

        if (random < 0.25) {
            return "up";
        } else if (random < 0.5) {
            return "down";
        } else if (random < 0.75) {
            return "left";
        } else {
            return "right";
        }
    }

    //    public static int xcoord() {
//
//    }
    public static boolean ship(char[][] board, int x, int y, int shiplength, char shipSymbol, String direction) {


        /*
        if direction() {
            choose/find random coordinate -- this should be a function too
            if check(random coords, direction) {

                ship()
            }
        }
        else {
            choose/find random coordinate
            if check(random coords) {
                ship()
            }

        }
         */

        if (check(direction, board, shiplength, x, y)) {

            for (int i = 0; i < shiplength; i++) {
                if (direction.equals("up")) {
                    board[x - i][y] = shipSymbol;
                } else if (direction.equals("down")) {
                    board[x + i][y] = shipSymbol;
                } else if (direction.equals("left")) {
                    board[x][y - i] = shipSymbol;
                } else if (direction.equals("right")) {
                    board[x][y + i] = shipSymbol;
                }
            }

            return true;
        }

        return false;

    }

    // create function to check if the thing will fit vertically/horizontally
    int shiplength = 3;


    public static boolean check(String direction, char[][] board, int shiplength, int x, int y) {
        if (board[x][y] != EMPTY) {
            return false;
        }

        switch (direction) {
            case "up" -> {
                for (int i = 0; i < shiplength; i++) {
                    if (!checkSquare(x - i, y, board)) {
                        return false;
                    }
                }
            }
            case "down" -> {
                for (int i = 0; i < shiplength; i++) {
                    if (!checkSquare(x + i, y, board)) {
                        return false;
                    }
                }
            }
            case "left" -> {
                for (int i = 0; i < shiplength; i++) {
                    if (!checkSquare(x, y - i, board)) {
                        return false;
                    }
                }
            }
            case "right" -> {
                for (int i = 0; i < shiplength; i++) {
                    if (!checkSquare(x, y + i, board)) {
                        return false;
                    }
                }
            }
        }
        return true;


    }

    public static boolean checkSquare(int x, int y, char[][]board) {
        if (x < 0 || x >= board.length || y < 0 || y >=board.length){
            return false;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x+j < 0 || x+j >= board.length || y+i < 0 || y+i >=board.length){
                    continue;
                }
                if (board[x+j][y+i] != EMPTY) {
                    return false;
                }
            }
        }
        return true;

    }
}

