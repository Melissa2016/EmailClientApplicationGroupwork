public class Email {
    public String sender;
    public String subject;
    private String recipient;
    private String body;
    private boolean isSent;
    private boolean isArchived;
    private String priority;
    private String date;

    public Email(String sender, String recipient, String subject, String body, String priority, String date) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.isSent = false;
        this.isArchived = false;
        this.priority = priority;
        this.date = date;
    }

    // Getters and setters
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBody() {
        return body;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    @Override
    public String toString() {
        return "From: " + sender + "\nTo: " + recipient + "\nSubject: " + subject + "\nDate: " + date + "\nPriority: " + priority + "\n\n" + body;
    }
}
