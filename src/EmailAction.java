
abstract class EmailAction implements Manageable {
    protected Email email;

    public EmailAction(Email email) {
        this.email = email;
    }
}