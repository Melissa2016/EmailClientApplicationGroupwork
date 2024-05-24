import java.util.List;

public interface IEmailClient {
    boolean login(String username, String password);

    void logout();

    List<Email> viewInbox();

    List<Email> viewSent();

    void composeEmail(String recipient, String subject, String body);

    Email readEmail(int emailId);

    void replyToEmail(int emailId, String body);

    void deleteEmail(int emailId);

    void addContact(String name, String email);

    void editContact(String oldEmail, String newName, String newEmail);

    void deleteContact(String email);

    List<Email> searchEmails(String keyword);

    void receiveEmail(Email email);
}