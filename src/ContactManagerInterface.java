import java.io.FileNotFoundException;
import java.util.List;

public interface ContactManagerInterface {
    void addContact(Contact contact);
    void updateContact(String name, String email, String phoneNumber)throws ContactNotFoundException;
    void removeContact(String name)throws ContactNotFoundException;
    void blockContact(String name)throws ContactNotFoundException;
    void unblockContact(String name)throws ContactNotFoundException;
    void changeContactState(String name, String state)throws ContactNotFoundException;
    Contact viewContact(String name)throws ContactNotFoundException;
    List searchContacts(String query);
    void importContacts(List externalContacts)throws FileNotFoundException, SecurityException;
    List exportContacts()throws SecurityException;
}
