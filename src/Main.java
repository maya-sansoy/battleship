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
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);



        System.out.println("how big would you like the square board to be? (enter one number)");
        int board_size = keyboard.nextInt();



        System.out.println();



        char[][] gameBoard = generateBoard(board_size);

        System.out.println("how many ships would you like on the board? (max 5)");
        int shipNumber = keyboard.nextInt();

        if (shipNumber > 5){
            System.out.println("sorry, the maximum is 5 ships. please choose again");
            shipNumber = keyboard.nextInt();
        }

            // change the maximum later to depend on board size



        String drction;

        for (int i = 1; i <= shipNumber; i++) {

            ship(gameBoard, randomCoordinate(board_size), randomCoordinate(board_size), i, '-', direction());
        }


        System.out.println(Arrays.deepToString(gameBoard).replace("], ", "\n").replace("[", "").replace("]", "").replace(",", ""));

    }

    private static Random rnd = new Random();
    public static int randomCoordinate(int board_size) {

        return rnd.nextInt(board_size);

    }



    public static char[][] generateBoard(int board_size){

        char[][] board = new char[board_size][board_size];

        for (int i = 0; i <board_size; i++) {
            Arrays.fill(board[i], '0');
        }

        return board;

    }

    // returns direction
    public static String direction() {
        double random = Math.random();

        if (random < 0.25) {
            return "up";
        }
        else if (random < 0.5) {
            return "down";
        }
        else if (random < 0.75) {
            return "left";
        }
        else {
            return "right";
        }
    }

//    public static int xcoord() {
//
//    }
    public static void ship(char[][] board, int x, int y, int shiplength, char shipSymbol, String direction) {


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
            board[x][y] = '+';


            for (int i = 1; i < shiplength; i++) {
                if (direction.equals("up")) {
                    board[x-i][y] = shipSymbol;
                }
                else if (direction.equals("down")) {
                    board[x+i][y] = shipSymbol;
                }
                else if (direction.equals("left")) {
                    board[x][y-i] = shipSymbol;
                }
                else if (direction.equals("right")) {
                    board[x][y+i] = shipSymbol;
                }
            }

        }





        // check if there is space for a ship of length 2 at place x,y
        // why is it breaking??? ok so its breaking because the check() function throws an exception when the ship is on the edge, because it cant check a coordinate that doesnt exist.how can i catch the exception and return false when it is thrown???

    }

    // create function to check if the thing will fit vertically/horizontally
    int shiplength = 3;



    public static boolean check(String direction, char[][] board, int shiplength, int x, int y) {



        if (board[x][y] != '0') {
            return false;
        }

        if (direction.equals("up")) {
            for (int i = 1; i < shiplength; i++) {
                for (int n = 1; n < shiplength-1; n++) {
                    if (x - n == 0) {
                        return false;
                    }
                }
                if (board[x-i][y] != '0') {
                    return false;
                }
            }
        }
        else if (direction.equals("down")) {
            for (int i = 1; i < shiplength; i++) {

                for (int n = 1; n < shiplength-1; n++) {
                    if (x + n == board[0].length) {
                        return false;
                    }
                }
                if (board[x+i][y] != '0') {
                    return false;
                }
            }
        }
        else if (direction.equals("left")) {
            for (int i = 1; i < shiplength; i++) {
                for (int n = 1; n < shiplength-1; n++) {
                    if (y - n == 0) {
                        return false;
                    }
                }
                if (board[x][y-i] != '0') {
                    return false;
                }

            }
        }
        else if (direction.equals("right")) {
            for (int i = 1; i < shiplength; i++) {
                for (int n = 1; n < shiplength-1; n++) {
                    if (y + n == board.length) {
                        return false;
                    }
                }
                if (board[x][y+i] != '0') {
                    return false;
                }
            }
        }
        return true;


        }
    }
