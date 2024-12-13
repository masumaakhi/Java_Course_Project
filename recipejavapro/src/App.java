// App.java
import ui.ViewerPanel;
import ui.AdminPanel;
import ui.ContributorPanel;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("===== Welcome to the Food Recipe Site =====");
            System.out.println("1. Viewer");
            System.out.println("2. Contributor");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Enter your role: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (roleChoice) {
                case 1:
                    ViewerPanel.show(scanner);
                    break;
                case 2:
                    ContributorPanel.show(scanner);
                    break;
                case 3:
                    AdminPanel.show(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


