import java.util.*;

public class game {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', ' ', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', ' ', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        String temp;
        int playerPos;
        int cpuPos;
        int count = 0;
        String player;
        int[] filledArr = new int[9];

        printBoard(gameBoard);


        while (true) {

            if (count % 2 == 0) {
                System.out.println("Where would you like to place your piece? (1-9)\n1 top left; 2 top middle; 3 top right; etc...");
                temp = scan.next();

                while (!isValidInput(temp, filledArr)) {
                    System.out.println("Invalid input. Try again!");
                    temp = scan.next();
                }

                playerPos = Integer.parseInt(temp);
                player = "player";
                filledArr[playerPos - 1] = playerPos;
                placePiece(gameBoard, playerPos, player);
                System.out.println ("You placed your piece in position " + playerPos);
                count++;

            } else {
                Random rand = new Random();
                cpuPos = (rand.nextInt(9) + 1);
                player = "cpu";
                while (filledArr[cpuPos - 1] > 0) {
                    cpuPos = (rand.nextInt(9) + 1);
                }

                filledArr[cpuPos - 1] = cpuPos;
                placePiece(gameBoard, cpuPos, player);
                System.out.println ("Cpu placed their piece in position " + cpuPos);
                count++;
            }

            System.out.println("----------------------------\n");
            printBoard(gameBoard);
            if (whoWon(gameBoard))
                break;
            if (boardFull(filledArr)) {
                System.out.println("Tie game. Play again!");
                break;

            }
        }
        scan.close();
    }


    private static void printBoard(char[][] board) {

        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    private static void placePiece(char[][] board, int i, String s) {
        char tempC;
        if (s.equals("player"))
            tempC = 'X';
        else
            tempC = 'O';

        switch (i) {
            case 1:
                board[0][0] = tempC;
                break;
            case 2:
                board[0][2] = tempC;
                break;
            case 3:
                board[0][4] = tempC;
                break;
            case 4:
                board[2][0] = tempC;
                break;
            case 5:
                board[2][2] = tempC;
                break;
            case 6:
                board[2][4] = tempC;
                break;
            case 7:
                board[4][0] = tempC;
                break;
            case 8:
                board[4][2] = tempC;
                break;
            case 9:
                board[4][4] = tempC;
                break;

        }
    }

    private static boolean whoWon(char[][] newBoard) {


        if ((newBoard[0][0] == 'X') && (newBoard[0][2] == 'X') && (newBoard[0][4] == 'X') ||
                (newBoard[2][0] == 'X') && (newBoard[2][2] == 'X') && (newBoard[2][4] == 'X') ||
                (newBoard[4][0] == 'X') && (newBoard[4][2] == 'X') && (newBoard[4][4] == 'X') ||
                (newBoard[0][0] == 'X') && (newBoard[2][0] == 'X') && (newBoard[4][0] == 'X') ||
                (newBoard[0][2] == 'X') && (newBoard[2][2] == 'X') && (newBoard[4][2] == 'X') ||
                (newBoard[0][4] == 'X') && (newBoard[2][4] == 'X') && (newBoard[4][4] == 'X') ||
                (newBoard[0][0] == 'X') && (newBoard[2][2] == 'X') && (newBoard[4][4] == 'X') ||
                (newBoard[0][4] == 'X') && (newBoard[2][2] == 'X') && (newBoard[4][0] == 'X')) {

            System.out.println("Congrats you won!");
            return true;

        } else if ((newBoard[0][0] == 'O') && (newBoard[0][2] == 'O') && (newBoard[0][4] == 'O') ||
                (newBoard[2][0] == 'O') && (newBoard[2][2] == 'O') && (newBoard[2][4] == 'O') ||
                (newBoard[4][0] == 'O') && (newBoard[4][2] == 'O') && (newBoard[4][4] == 'O') ||
                (newBoard[0][0] == 'O') && (newBoard[2][0] == 'O') && (newBoard[4][0] == 'O') ||
                (newBoard[0][2] == 'O') && (newBoard[2][2] == 'O') && (newBoard[4][2] == 'O') ||
                (newBoard[0][4] == 'O') && (newBoard[2][4] == 'O') && (newBoard[4][4] == 'O') ||
                (newBoard[0][0] == 'O') && (newBoard[2][2] == 'O') && (newBoard[4][4] == 'O') ||
                (newBoard[0][4] == 'O') && (newBoard[2][2] == 'O') && (newBoard[4][0] == 'O')) {

            System.out.println("CPU won :(");
            return true;
        }

        return false;

    }

    private static boolean boardFull(int[] arr) {
        for (int i : arr) {
            if (i == 0)
                return false;
        }
        return true;

    }

    private static boolean isValidInput(String s, int[] tempArr) {
        int tempVar;
        try {
            tempVar = Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            return false;
        }

        if ((tempArr[tempVar - 1] > 0) || tempVar > 9 || tempVar < 1)
            return false;
        return true;



    }
}


