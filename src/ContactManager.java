public abstract class ContactManager extends AbstractContactManager {
    // Specific functionalities can be added here if needed
    public void displayMenu() {
        System.out.println("***********************************");
        System.out.println("*********============**************");
        System.out.println("Contact Management SubSystem");
        System.out.println("***********************************");
        System.out.println("*********============**************");
        System.out.println("1. Add a new contact");
        System.out.println("2. Update an existing contact");
        System.out.println("3. Remove a contact");
        System.out.println("4. Block a contact");
        System.out.println("5. Unblock a contact");
        System.out.println("6. Change contact state");
        System.out.println("7. View contact details");
        System.out.println("8. Search for contacts");
        System.out.println("9. Import contacts");
        System.out.println("10. Export contacts");
        System.out.println("11. Exit");
    }
}