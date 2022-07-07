package cinema;

import java.util.Scanner;

public class Cinema {
    static int rows;
    static int seats;
    static int totalNumberOfSeats = 0;
    static double percentageOfTicketSold = 0.0;
    static String cinemaMatrix[][];
    static boolean ticketBought = false;

    static int numberOfPurchasedTicket;
    static int ticketCost = 0;
    static int currentIncome = 0;

    static int totalIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each rows: ");
        seats = scanner.nextInt();
        System.out.println();
        getCinemaLayout(rows, seats);
        totalNumberOfSeats = rows * seats;

        while (true) {
            System.out.println("1: Show the seats");
            System.out.println("2: Buy a ticket");
            System.out.println("3: Statistics");
            System.out.println("0. Exit");

            int optionSelected = scanner.nextInt();
            if (optionSelected == 0) {
                break;
            }

            switch (optionSelected) {
                case 1:
                    showSeats();
                    System.out.println();
                    break;
                case 2:
                    buyATicket();
                    System.out.printf("Ticket price: %c%d%n", '$', ticketCost);
                    System.out.println();
                    break;
                case 3:
                    statistics();
                    break;
                default:
                    System.out.println("Please select a right option");
            }
        }
    }

    public static void getCinemaLayout(int rowsInput, int seatsInput) {
        rows = rowsInput;
        seats = seatsInput;
        cinemaMatrix = new String[rows + 1][seats + 1];

        for (int i = 0; i < cinemaMatrix.length; i++) {
            for (int j = 0; j < cinemaMatrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    cinemaMatrix[0][0] = "  ";
                } else if (j > 0 && i == 0) {
                    cinemaMatrix[i][j] = j + " ";
                } else if (i > 0 && j == 0) {
                    cinemaMatrix[i][j] = i + " ";
                } else {
                    cinemaMatrix[i][j] = "S ";
                }
            }
        }
    }

    public static void showSeats() {
        System.out.println("Cinema: ");
        for (int i = 0; i < cinemaMatrix.length; i++) {
            for (int j = 0; j < cinemaMatrix[0].length; j++) {
                System.out.print(cinemaMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyATicket() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a row number:");
            int rowRequest = scanner.nextInt();
            if (rowRequest < 1 || rowRequest > rows) {
                System.out.println("Wrong input!");
                System.out.println();
                continue;
            }

            System.out.println("Enter a seat number in that row:");
            int seatRequest = scanner.nextInt();
            if (seatRequest < 1 || seatRequest > rows) {
                System.out.println("Wrong input!");
                System.out.println();
                continue;
            }

            System.out.println();
            if (!"B ".equals(cinemaMatrix[rowRequest][seatRequest])) {
                cinemaMatrix[rowRequest][seatRequest] = "B ";

                getTicketPrice(rows, seats, rowRequest);
                ticketBought = true;
                numberOfPurchasedTicket++;
                currentIncome += ticketCost;
                break;
            } else {
                System.out.println("That ticket has already been purchased");
            }

        }
    }

    private static int getTicketPrice(int rows, int seats, int rowRequest) {
        if ((totalNumberOfSeats) <= 60) {
            ticketCost = 10;
        } else if ((totalNumberOfSeats) > 60) {
            if (rows % 2 == 0 && rowRequest <= rows / 2) {
                ticketCost = 10;
            } else if (rows % 2 == 0 && rowRequest > rows / 2) {
                ticketCost = 8;
            } else if (rows % 2 == 1 && rowRequest <= (int) (rows / 2)) {
                ticketCost = 10;
            } else if (rows % 2 == 1 && rowRequest >= (int) (rows / 2) + 1) {
                ticketCost = 8;
            }

        }
        return ticketCost;
    }


    private static void statistics() {
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTicket);
        percentageOfTicketSold = (double) numberOfPurchasedTicket * 100 / totalNumberOfSeats;


        System.out.printf("Percentage: %.2f%s%n", percentageOfTicketSold, "%");
        System.out.printf("Current income: %c%d%n", '$', currentIncome);
        System.out.printf("Total income: %c%d%n", '$', getTotalIncome());
        System.out.println();


    }

    private static int getTotalIncome() {
        totalIncome = 0;
        for (int i = 1; i < cinemaMatrix.length; i++) {
            totalIncome += seats * getTicketPrice(rows, seats, i);
        }
        return totalIncome;

    }
}

