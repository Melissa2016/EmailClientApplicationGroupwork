import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractContactManager implements ContactManagerInterface {
    protected List<Contact> contacts = new ArrayList<>();

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void updateContact(String name, String email, String phoneNumber) throws ContactNotFoundException {
        Contact contact = findContactByName(name);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
    }

    @Override
    public void removeContact(String name) throws ContactNotFoundException {
        Contact contact = findContactByName(name);
        contacts.remove(contact);
    }

    @Override
    public void blockContact(String name) throws ContactNotFoundException {
        changeContactState(name, "blocked");
    }

    @Override
    public void unblockContact(String name) throws ContactNotFoundException {
        changeContactState(name, "active");
    }

    @Override
    public void changeContactState(String name, String state) throws ContactNotFoundException {
        Contact contact = findContactByName(name);
        contact.setState(state);
    }

    @Override
    public Contact viewContact(String name) throws ContactNotFoundException {
        try {
            return findContactByName(name);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> searchContacts(String query) {
        return contacts.stream()
                .filter(contact -> contact.getName().contains(query) || contact.getEmail().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public void importContacts(List externalContacts) throws FileNotFoundException, SecurityException {
        if (externalContacts == null) {
            throw new FileNotFoundException("External contacts file not found.");
        }
        if (externalContacts.isEmpty()) {
            throw new SecurityException("Security exception: Unable to import contacts.");
        }
        contacts.addAll(externalContacts);
    }

    @Override
    public List<Contact> exportContacts() throws SecurityException {
        if (contacts.isEmpty()) {
            throw new SecurityException("Security exception: No contacts to export.");
        }
        return new ArrayList<>(contacts);
    }

    private Contact findContactByName(String name) throws ContactNotFoundException {
        return contacts.stream()
                .filter(contact -> contact.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ContactNotFoundException("Contact not found: " + name));
    }
}
