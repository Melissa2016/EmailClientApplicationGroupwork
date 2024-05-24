class SendEmail extends EmailAction {
    public SendEmail(Email email) {
        super(email);
    }

    @Override
    public void performAction(Email email) {
        // Code to send email
        System.out.println("Email sent to: " + email.getRecipient());
        email.setSent(true);
    }
}

