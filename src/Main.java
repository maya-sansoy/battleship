import java.util.Arrays;
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

 */
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("how big would you like the square board to be? (enter one number)");
        int board_size = keyboard.nextInt();

        System.out.println();

        //System.out.println(Arrays.deepToString(generateBoard(board_size)).replace("], ", "\n").replace("[", "").replace("]", "").replace(",", ""));

        char[][] gameBoard = generateBoard(board_size);
        ship(gameBoard, 3, 3, 2);

        System.out.println(Arrays.deepToString(gameBoard).replace("], ", "\n").replace("[", "").replace("]", "").replace(",", ""));

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
    public static void ship(char[][] board, int x, int y, int shiplength) {


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

        String checkDirection;

        do {
            checkDirection = direction();

            board[x][y] = '+';

            for (int i = 1; i < shiplength; i++) {
                if (checkDirection.equals("up")) {
                    board[x][y+i] = '+';
                }
                else if (checkDirection.equals("down")) {
                    board[x][y+i] = '+';
                }
                else if (checkDirection.equals("left")) {
                    board[x-i][y] = '+';
                }
                else if (checkDirection.equals("right")) {
                    board[x+i][y] = '+';
                }
            }

        }
        // check if there is space for a ship of length 2 at place 3,5
        while (check(checkDirection, board, shiplength,x,y));


    }

    // create function to check if the thing will fit vertically/horizontally
    int shiplength = 3;



    public static boolean check(String direction, char[][] board, int shiplength, int x, int y) {



        if (board[x][y] != '0') {
            return false;
        }

        if (direction.equals("up")) {
            for (int i = 1; i < shiplength; i++) {
                if (board[x+i][y] != '0') {
                    return false;
                }
            }
        }
        else if (direction.equals("down")) {
            for (int i = 1; i < shiplength; i++) {
                if (board[x-i][y] != '0') {
                    return false;
                }
            }
        }
        else if (direction.equals("left")) {
            for (int i = 1; i < shiplength; i++) {
                if (board[x][y-i] != '0') {
                    return false;
                }
            }
        }
        else if (direction.equals("right")) {
            for (int i = 1; i < shiplength; i++) {
                if (board[x][y+i] != '0') {
                    return false;
                }
            }
        }
        return true;

        }
    }
