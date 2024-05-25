interface ContactState {
    void block(Contact contact);
    void unblock(Contact contact);
    void activate(Contact contact);
}

class ActiveState implements ContactState {
    @Override
    public void block(Contact contact) {
        contact.setState(new BlockedState());
        System.out.println("Contact blocked: " + contact.getName());
    }

    @Override
    public void unblock(Contact contact) {
        System.out.println("Contact is already active: " + contact.getName());
    }

    @Override
    public void activate(Contact contact) {
        System.out.println("Contact is already active: " + contact.getName());
    }
}

class InactiveState implements ContactState {
    @Override
    public void block(Contact contact) {
        contact.setState(new BlockedState());
        System.out.println("Contact blocked: " + contact.getName());
    }

    @Override
    public void unblock(Contact contact) {
        contact.setState(new ActiveState());
        System.out.println("Contact activated: " + contact.getName());
    }

    @Override
    public void activate(Contact contact) {
        contact.setState(new ActiveState());
        System.out.println("Contact activated: " + contact.getName());
    }
}

class BlockedState implements ContactState {
    @Override
    public void block(Contact contact) {
        System.out.println("Contact is already blocked: " + contact.getName());
    }

    @Override
    public void unblock(Contact contact) {
        contact.setState(new ActiveState());
        System.out.println("Contact unblocked: " + contact.getName());
    }

    @Override
    public void activate(Contact contact) {
        System.out.println("Contact cannot be activated directly from blocked state: " + contact.getName());
    }
}
