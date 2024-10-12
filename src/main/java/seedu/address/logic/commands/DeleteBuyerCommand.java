package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Buyer;
import seedu.address.model.client.Client;

/**
 * Represents a command to delete a buyer in the buyer management system.
 */
public class DeleteBuyerCommand extends Command {
    /** The command word for this specific action. */
    public static final String COMMAND_WORD = "deletebuyer";
    public static final String MESSAGE_ARGUMENTS = "phoneNumber: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the buyer identified by the phone number used in the displayed person list.\n"
            + "Parameters: phone number (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + "81621234";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Buyer: %1$s";
    private final String phoneNumber;

    /**
     * Constructs a {@code DeleteBuyerCommand} with the specified phone number.
     *
     * @param phoneNumber The phone number of the buyer to delete.
     */
    public DeleteBuyerCommand(String phoneNumber) {
        requireAllNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
    }
    /**
     * Executes the delete buyer command and removes the buyer from the model.
     *
     * @param model The model which the command should operate on.
     * @return A {@code CommandResult} object representing the result of the delete operation.
     * @throws CommandException If the buyer cannot be found or deleted.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Search for the person with the specified phone number
        Client personToDelete = model.getFilteredClientList().stream()
                .filter(person -> person instanceof Buyer)
                .filter(person -> person.getPhone().toString().equals(phoneNumber))
                .findFirst().orElseThrow(() -> new CommandException(String.format("Buyer not found. ", phoneNumber)));
        model.deleteClient(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    /**
     * Checks if this {@code DeleteBuyerCommand} is equal to another object.
     *
     * @param other The object to compare with this command.
     * @return {@code true} if the other object is an instance of {@code DeleteBuyerCommand} with the same phone number.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof DeleteBuyerCommand)) {
            return false;
        }
        // state check
        DeleteBuyerCommand d = (DeleteBuyerCommand) other;
        return this.phoneNumber.equals(d.phoneNumber);
    }
}
