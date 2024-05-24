import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class EmailClient {
    private String username;
    private String password;
    private boolean loggedIn = false;
    public List<Email> inbox = new ArrayList<>();
    protected List<Email> sent = new ArrayList<>();
    public List<Contact> contacts = new ArrayList<>();


    public void login(String username, String password) {
        this.username = username;
        this.password = password;
        loggedIn = true;
        System.out.println("Logged in as " + username);
    }

    public void logout() {
        loggedIn = false;
        username = null;
        password = null;
        System.out.println("Logged out.");
    }

    public void viewInbox() {
        System.out.println("Inbox:");
        for (int i = 0; i < inbox.size(); i++) {
            System.out.println((i + 1) + ". " + inbox.get(i).subject);
        }
    }

    public void viewSent() {
        System.out.println("Sent:");
        for (int i = 0; i < sent.size(); i++) {
            System.out.println((i + 1) + ". " + sent.get(i).subject);
        }
    }

    public void composeEmail(String recipient, String subject, String body, String priority, String date) {
        Email email = new Email(username, recipient, subject, body, priority, date);
        email.setSent(true);
        sent.add(email);
        System.out.println("Email sent to " + recipient);
    }

    public void readEmail(List<Email> emails, int index) {
        if (index >= 0 && index < emails.size()) {
            System.out.println(emails.get(index));
        } else {
            System.out.println("Invalid email index.");
        }
    }

    public void replyToEmail(int index, String body) {
        if (index >= 0 && index < inbox.size()) {
            Email original = inbox.get(index);
            Email reply = new Email(username, original.sender, "Re: " + original.subject, body, "Normal", "Today");
            reply.setSent(true);
            sent.add(reply);
            System.out.println("Replied to " + original.sender);
        } else {
            System.out.println("Invalid email index.");
        }
    }

    public void deleteEmail(int index) {
        if (index >= 0 && index < inbox.size()) {
            inbox.remove(index);
            System.out.println("Email deleted.");
        } else {
            System.out.println("Invalid email index.");
        }
    }

    public void searchEmails(String keyword) {
        System.out.println("Search results:");
        for (int i = 0; i < inbox.size(); i++) {
            Email email = inbox.get(i);
            if (email.subject.contains(keyword) || email.getBody().contains(keyword)) {
                System.out.println((i + 1) + ". " + email.subject);
            }
        }
    }

    public void receiveEmail(Email email) {
        inbox.add(email);
    }

    public void archiveEmail(int index) {
        if (index >= 0 && index < inbox.size()) {
            Email email = inbox.get(index);
            email.setArchived(true);
            System.out.println("Email archived.");
        } else {
            System.out.println("Invalid email index.");
        }
    }
    public void sortEmails(boolean ascending) {
        if (ascending) {
            inbox.sort(Comparator.comparing(Email::getDate));
        } else {
            inbox.sort(Comparator.comparing(Email::getDate).reversed());
        }
        System.out.println("Emails sorted.");
    }

    public List<Email> getEmails(boolean fromInbox) {
        return fromInbox ? new ArrayList<>(inbox) : new ArrayList<>(sent);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added: " + contact.getName());
    }

    public void updateContact(String name, String email, String phoneNumber) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.setEmail(email);
                c.setPhoneNumber(phoneNumber);
                System.out.println("Contact updated: " + c.getName());
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void removeContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                contacts.remove(c);
                System.out.println("Contact removed: " + name);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void blockContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.setState("blocked");
                System.out.println("Contact blocked: " + name);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void unblockContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.setState("active");
                System.out.println("Contact unblocked: " + name);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void changeContactState(String name, String state) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.setState(state);
                System.out.println("Contact state changed: " + name);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public Contact viewContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                System.out.println("Contact details:");
                System.out.println(c);
                return c;
            }
        }
        System.out.println("Contact not found.");
        return null;
    }

    public List<Contact> searchContacts(String query) {
        return contacts.stream()
                .filter(contact -> contact.getName().contains(query) || contact.getEmail().contains(query))
                .collect(Collectors.toList());
    }
}


