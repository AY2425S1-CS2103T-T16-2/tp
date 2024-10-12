package seedu.address.model.client;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Buyer in the address book.
 */
public class Buyer extends Client {

    /**
     * Constructs a Buyer object with the specified name, phone, and email.
     *
     * @param name  The name of the buyer.
     * @param phone The phone number of the buyer.
     * @param email The email address of the buyer.
     */
    public Buyer(Name name, Phone phone, Email email) {
        super(name, phone, email);
    }

    /**
     * Returns true if both clients are buyers and have the same phone number.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient instanceof Buyer
                && otherClient.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, ClientTypes.BUYER.toString());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherPerson = (Buyer) other;

        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("client type", ClientTypes.BUYER.toString())
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .toString();
    }

    @Override
    public boolean isBuyer() {
        return true;
    }
    @Override
    public boolean isSeller() {
        return false;
    }
}
