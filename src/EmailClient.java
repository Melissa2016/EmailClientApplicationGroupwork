
    import java.util.ArrayList;
import java.util.List;

    public class EmailClient {
        private static EmailClient instance;
        private String currentUser;
        public List<Email> inbox;
        public List<Email> sent;
        private List<Contact> contacts;

        private EmailClient() {
            inbox = new ArrayList<>();
            sent = new ArrayList<>();
            contacts = new ArrayList<>();
        }

        public static EmailClient getInstance() {
            if (instance == null) {
                instance = new EmailClient();
            }
            return instance;
        }

        public void login(String username, String password) {
            // Implement login logic
            currentUser = username;
            System.out.println("Logged in as " + username);
        }

        public void logout() {
            // Implement logout logic
            System.out.println("Logged out");
            currentUser = null;
        }

        public void viewInbox() {
            // Implement view inbox logic
            System.out.println("Inbox:");
            for (Email email : inbox) {
                System.out.println(email);
            }
        }

        public void viewSent() {
            // Implement view sent emails logic
            System.out.println("Sent Emails:");
            for (Email email : sent) {
                System.out.println(email);
            }
        }

        public void composeEmail(String recipient, String subject, String body, String priority, String date) {
            // Implement compose email logic
            Email email = new Email(currentUser, recipient, subject, body, priority, date);
            sent.add(email);
            System.out.println("Email sent to " + recipient);
        }

        public void readEmail(List<Email> emails, int index) {
            // Implement read email logic
            if (index >= 0 && index < emails.size()) {
                Email email = emails.get(index);
                System.out.println(email);
            } else {
                System.out.println("Invalid email index.");
            }
        }

        public void replyToEmail(int index, String body) {
            // Implement reply to email logic
            if (index >= 0 && index < inbox.size()) {
                Email originalEmail = inbox.get(index);
                Email reply = new Email(currentUser, originalEmail.getSender(), "Re: " + originalEmail.getSubject(), body, "Normal", "Today");
                sent.add(reply);
                System.out.println("Reply sent to " + originalEmail.getSender());
            } else {
                System.out.println("Invalid email index.");
            }
        }

        public void deleteEmail(int index) {
            // Implement delete email logic
            if (index >= 0 && index < inbox.size()) {
                inbox.remove(index);
                System.out.println("Email deleted.");
            } else {
                System.out.println("Invalid email index.");
            }
        }

        public void searchEmails(String keyword) {
            // Implement search emails logic
            System.out.println("Search results:");
            for (Email email : inbox) {
                if (email.getSubject().contains(keyword) || email.getBody().contains(keyword)) {
                    System.out.println(email);
                }
            }
        }

        public void archiveEmail(int index) {
            // Implement archive email logic
            if (index >= 0 && index < inbox.size()) {
                Email email = inbox.remove(index);
                System.out.println("Email archived: " + email);
            } else {
                System.out.println("Invalid email index.");
            }
        }

        public void receiveEmail(Email email) {
            // Implement receive email logic
            inbox.add(email);
        }

        public void sortEmails(boolean ascending) {
            // Implement sort emails logic
            inbox.sort((e1, e2) -> ascending ? e1.getDate().compareTo(e2.getDate()) : e2.getDate().compareTo(e1.getDate()));
            System.out.println("Emails sorted.");
        }

        public List<Email> getEmails(boolean fromInbox) {
            // Implement get emails logic
            return fromInbox ? inbox : sent;
        }

        public void addContact(Contact contact) {
            // Implement add contact logic
            contacts.add(contact);
            System.out.println("Contact added: " + contact.getName());
        }

        public void updateContact(String name, String email, String phoneNumber) {
            // Implement update contact logic
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    contact.setEmail(email);
                    contact.setPhoneNumber(phoneNumber);
                    System.out.println("Contact updated: " + name);
                    return;
                }
            }
            System.out.println("Contact not found: " + name);
        }

        public void removeContact(String name) {
            // Implement remove contact logic
            contacts.removeIf(contact -> contact.getName().equals(name));
            System.out.println("Contact removed: " + name);
        }

        public void blockContact(String name) {
            // Implement block contact logic
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    contact.setState("blocked");
                    System.out.println("Contact blocked: " + name);
                    return;
                }
            }
            System.out.println("Contact not found: " + name);
        }

        public void unblockContact(String name) {
            // Implement unblock contact logic
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    contact.setState("active");
                    System.out.println("Contact unblocked: " + name);
                    return;
                }
            }
            System.out.println("Contact not found: " + name);
        }

        public void changeContactState(String name, String state) {
            // Implement change contact state logic
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    contact.setState(state);
                    System.out.println("Contact state changed: " + name + " to " + state);
                    return;
                }
            }
            System.out.println("Contact not found: " + name);
        }

        public void viewContact(String name) {
            // Implement view contact logic
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    System.out.println(contact);
                    return;
                }
            }
            System.out.println("Contact not found: " + name);
        }

        public List<Contact> searchContacts(String query) {
            // Implement search contacts logic
            List<Contact> result = new ArrayList<>();
            for (Contact contact : contacts) {
                if (contact.getName().contains(query) || contact.getEmail().contains(query) || contact.getPhoneNumber().contains(query)) {
                    result.add(contact);
                }
            }
            return result;
        }
    }


