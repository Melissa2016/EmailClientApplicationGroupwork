
class ArchiveEmail extends EmailAction {
    public ArchiveEmail(Email email) {
        super(email);
    }

    @Override
    public void performAction(Email email) {
        email.setArchived(true);
    }
}