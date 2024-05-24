
class SetPriority extends EmailAction {
    private String priority;

    public SetPriority(Email email, String priority) {
        super(email);
        this.priority = priority;
    }

    @Override
    public void performAction(Email email) {
        email.setPriority(priority);
    }
}
