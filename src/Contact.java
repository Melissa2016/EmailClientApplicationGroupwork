class Contact {
    private String name;
    private String email;
    private String phoneNumber;
    private ContactState state; // Using state pattern

    public Contact(String name, String email, String phoneNumber, String state) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        setState(state);
    }

    // State management
    public void setState(ContactState state) {
        this.state = state;
    }

    public void setState(String state) {
        switch (state.toLowerCase()) {
            case "active":
                this.state = new ActiveState();
                break;
            case "blocked":
                this.state = new BlockedState();
                break;
            default:
                this.state = new InactiveState();
                break;
        }
    }

    public void block() {
        state.block(this);
    }

    public void unblock() {
        state.unblock(this);
    }

    public void activate() {
        state.activate(this);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state='" + state.getClass().getSimpleName() + '\'' +
                '}';
    }
}
