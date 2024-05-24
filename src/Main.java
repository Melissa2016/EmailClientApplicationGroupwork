import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmailClient emailClient = new EmailClient();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    loginUser(scanner, emailClient);
                    break;
                case 2:
                    emailClient.logout();
                    break;
                case 3:
                    emailClient.viewInbox();
                    break;
                case 4:
                    emailClient.viewSent();
                    break;
                case 5:
                    composeEmail(scanner, emailClient);
                    break;
                case 6:
                    readEmail(scanner, emailClient, true);
                    break;
                case 7:
                    readEmail(scanner, emailClient, false);
                    break;
                case 8:
                    replyToEmail(scanner, emailClient);
                    break;
                case 9:
                    deleteEmail(scanner, emailClient);
                    break;
                case 10:
                    searchEmails(scanner, emailClient);
                    break;
                case 11:
                    archiveEmail(scanner, emailClient);
                    break;
                case 12:
                    receiveTestEmail(emailClient);
                    break;
                case 13:
                    sortEmails(scanner, emailClient);
                    break;
                case 14:
                    getEmails(scanner, emailClient);
                    break;
                case 15:
                    addContact(scanner, emailClient);
                    break;
                case 16:
                    updateContact(scanner, emailClient);
                    break;
                case 17:
                    removeContact(scanner, emailClient);
                    break;
                case 18:
                    blockContact(scanner, emailClient);
                    break;
                case 19:
                    unblockContact(scanner, emailClient);
                    break;
                case 20:
                    changeContactState(scanner, emailClient);
                    break;
                case 21:
                    viewContact(scanner, emailClient);
                    break;
                case 22:
                    searchContacts(scanner, emailClient);
                    break;
                case 23:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Login");
        System.out.println("2. Logout");
        System.out.println("3. View Inbox");
        System.out.println("4. View Sent");
        System.out.println("5. Compose Email");
        System.out.println("6. Read Email from Inbox");
        System.out.println("7. Read Email from Sent");
        System.out.println("8. Reply to Email");
        System.out.println("9. Delete Email from Inbox");
        System.out.println("10. Search Emails");
        System.out.println("11. Archive Email from Inbox");
        System.out.println("12. Receive Test Email");
        System.out.println("13. Sort Emails");
        System.out.println("14. Get Emails");
        System.out.println("15. Add Contact");
        System.out.println("16. Update Contact");
        System.out.println("17. Remove Contact");
        System.out.println("18. Block Contact");
        System.out.println("19. Unblock Contact");
        System.out.println("20. Change Contact State");
        System.out.println("21. View Contact");
        System.out.println("22. Search Contacts");
        System.out.println("23. Exit");
    }

    private static String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static void loginUser(Scanner scanner, EmailClient emailClient) {
        String username = getInput(scanner, "Enter username: ");
        String password = getInput(scanner, "Enter password: ");
        emailClient.login(username, password);
    }

    private static void composeEmail(Scanner scanner, EmailClient emailClient) {
        String recipient = getInput(scanner, "Enter recipient: ");
        String subject = getInput(scanner, "Enter subject: ");
        String body = getInput(scanner, "Enter body: ");
        String priority = getInput(scanner, "Enter priority (High, Normal, Low): ");
        String date = getInput(scanner, "Enter date (e.g., 2024-05-24): ");
        emailClient.composeEmail(recipient, subject, body, priority, date);
    }

    private static void readEmail(Scanner scanner, EmailClient emailClient, boolean fromInbox) {
        List<Email> emails = fromInbox ? emailClient.inbox : emailClient.sent;
        int index = getEmailIndex(scanner, emails.size());
        emailClient.readEmail(emails, index);
    }

    private static void replyToEmail(Scanner scanner, EmailClient emailClient) {
        int index = getEmailIndex(scanner, emailClient.inbox.size());
        String body = getInput(scanner, "Enter reply body: ");
        emailClient.replyToEmail(index, body);
    }

    private static void deleteEmail(Scanner scanner, EmailClient emailClient) {
        int index = getEmailIndex(scanner, emailClient.inbox.size());
        emailClient.deleteEmail(index);
    }

    private static void searchEmails(Scanner scanner, EmailClient emailClient) {
        String keyword = getInput(scanner, "Enter search keyword: ");
        emailClient.searchEmails(keyword);
    }

    private static void archiveEmail(Scanner scanner, EmailClient emailClient) {
        int index = getEmailIndex(scanner, emailClient.inbox.size());
        emailClient.archiveEmail(index);
    }

    private static void receiveTestEmail(EmailClient emailClient) {
        Email testEmail = new Email("test@example.com", "user@example.com", "Test Subject", "This is a test email.", "Normal", "Today");
        emailClient.receiveEmail(testEmail);
        System.out.println("Test email received.");
    }

    private static int getEmailIndex(Scanner scanner, int size) {
        int index = -1;
        while (index < 0 || index >= size) {
            System.out.print("Enter email index (1 to " + size + "): ");
            index = scanner.nextInt() - 1;
            scanner.nextLine();  // Consume newline
            if (index < 0 || index >= size) {
                System.out.println("Invalid index. Try again.");
            }
        }
        return index;
    }

    //
    private static void sortEmails(Scanner scanner, EmailClient emailClient) {
        try {
            System.out.print("Sort emails ascending (true/false): ");
            boolean ascending = scanner.nextBoolean();
            emailClient.sortEmails(ascending);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void getEmails(Scanner scanner, EmailClient emailClient) {
        try {
            System.out.print("Get emails from inbox (true/false): ");
            boolean fromInbox = scanner.nextBoolean();
            emailClient.getEmails(fromInbox).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addContact(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name: ");
            String email = getInput(scanner, "Enter contact email: ");
            String phoneNumber = getInput(scanner, "Enter contact phone number: ");
            emailClient.addContact(new Contact(name, email, phoneNumber, "active"));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void updateContact(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name to update: ");
            String email = getInput(scanner, "Enter new email: ");
            String phoneNumber = getInput(scanner, "Enter new phone number: ");
            emailClient.updateContact(name, email, phoneNumber);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void removeContact(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name to remove: ");
            emailClient.removeContact(name);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void blockContact(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name to block: ");
            emailClient.blockContact(name);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void unblockContact(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name to unblock: ");
            emailClient.unblockContact(name);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void changeContactState(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name to change state: ");
            String state = getInput(scanner, "Enter new state (active/blocked): ");
            emailClient.changeContactState(name, state);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void viewContact(Scanner scanner, EmailClient emailClient) {
        try {
            String name = getInput(scanner, "Enter contact name to view: ");
            emailClient.viewContact(name);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void searchContacts(Scanner scanner, EmailClient emailClient) {
        try {
            String query = getInput(scanner, "Enter search query: ");
            emailClient.searchContacts(query).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
