package workflow;

import workflow.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner       scanner = new Scanner(System.in);
    private static final WorkflowBoard board   = new WorkflowBoard();

    public static void main(String[] args) {
        System.out.println("ManageMyWorkflow System");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choice");

            switch (choice) {
                case 1: handleCreate();
                break;
                case 2: handleMove();
                break;
                case 3: handleAlter();
                break;
                case 4: handleAuditLog();
                break;
                case 5: board.displayBoard();
                break;
                case 6: running = false;
                break;
                default: System.out.println("Invalid choice. Enter 1–6.");
            }
        }
        scanner.close();
    }

    //Menu

    private static void printMenu() {

        System.out.println("1) Create ticket");
        System.out.println("2) Move ticket");
        System.out.println("3) Alter ticket");
        System.out.println("4) Display audit log");
        System.out.println("5) Display board");
        System.out.println("6) Exit");
        System.out.print("Select option: ");
    }

    private static void handleCreate() {
        System.out.print("  Title       : "); String title = scanner.nextLine().trim();
        System.out.print("  Description : "); String desc  = scanner.nextLine().trim();
        System.out.print("  Priority (LOW / MEDIUM / HIGH): ");
        String priority = scanner.nextLine().trim().toUpperCase();

        WorkflowCommand cmd = new CreateCommand(board, title, desc, priority);
        cmd.performCommand();
    }

    private static void handleMove() {
        int id = readInt("  Ticket ID");
        System.out.println("  Columns:  1) TO_DO   2) IN_PROGRESS   3) DONE");
        int col = readInt("  Move to");
        String[] columns = {"TO_DO", "IN_PROGRESS", "DONE"};
        if (col < 1 || col > 3) { System.out.println("Invalid column choice."); return; }

        WorkflowCommand cmd = new MoveCommand(board, id, columns[col - 1]);
        cmd.performCommand();
    }

    private static void handleAlter() {
        int id = readInt("  Ticket ID");
        System.out.println("  Fields:   1) title   2) description   3) priority");
        int fieldChoice = readInt("  Field");
        String[] fields = {"title", "description", "priority"};
        if (fieldChoice < 1 || fieldChoice > 3) { System.out.println("Invalid field choice."); return; }

        System.out.print("  New value : ");
        String value = scanner.nextLine().trim();

        WorkflowCommand cmd = new AlterCommand(board, id, fields[fieldChoice - 1], value);
        cmd.performCommand();
    }

    private static void handleAuditLog() {
        List<String> log = board.getAuditLog();
        System.out.println("AUDIT LOG: " + log.size() + " entries");
        if (log.isEmpty()) {
            System.out.println("  (no commands executed yet)");
        } else {
            for (int i = 0; i < log.size(); i++) {
                System.out.printf("  %2d. %s%n", i + 1, log.get(i));
            }
        }
        System.out.println();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }
}
