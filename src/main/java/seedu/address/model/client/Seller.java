package seedu.address.model.client;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Seller in the address book.
 */
public class Seller extends Client {

    /**
     * Constructs a Seller object with the specified name, phone, and email.
     *
     * @param name  The name of the seller.
     * @param phone The phone number of the seller.
     * @param email The email address of the seller.
     */
    public Seller(Name name, Phone phone, Email email) {
        super(name, phone, email);
    }

    /**
     * Returns true if both clients are sellers and have the same phone number.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient instanceof Seller
                && otherClient.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, ClientTypes.SELLER.toString());
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
        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherPerson = (Seller) other;

        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("client type", ClientTypes.SELLER.toString())
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .toString();
    }
    @Override
    public boolean isBuyer() {
        return false;
    }
    @Override
    public boolean isSeller() {
        return true;
    }
    @Override
    public String getTypeString() {
        return ClientTypes.SELLER.getType();
    }
}
