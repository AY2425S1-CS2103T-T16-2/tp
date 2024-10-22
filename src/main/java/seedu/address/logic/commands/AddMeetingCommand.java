package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Adds a {@code Meeting} to the meeting book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the meeting log. \n"
            + "Parameters: "
            + PREFIX_MEETING_TITLE + "TITLE "
            + PREFIX_MEETING_DATE + "DATE "
            + PREFIX_BUYER + "BUYER "
            + PREFIX_SELLER + "SELLER "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_POSTALCODE + "POSTALCODE ";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "There is already a meeting of the same title "
            + "scheduled on this date. Please change either the meeting title or date.";

    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.getFilteredClientList().stream()
                .filter(Client::isBuyer)
                .filter(person -> person.getName().toString()
                        .toLowerCase().contains(toAdd.getBuyer().toString().toLowerCase()))
                .findFirst().orElseThrow(() ->
                        new CommandException(String.format("Buyer not found. ", toAdd.getBuyer())));
        model.getFilteredClientList().stream()
                .filter(Client::isSeller)
                .filter(person -> person.getName().toString().toLowerCase()
                        .contains(toAdd.getSeller().toString().toLowerCase()))
                .findFirst().orElseThrow(() ->
                        new CommandException(String.format("Seller not found. ", toAdd.getSeller())));
        model.getFilteredPropertyList().stream().filter(p ->
                p.getType().equals(toAdd.getType()) && p.getPostalCode().equals(toAdd.getPostalCode()))
                .findFirst().orElseThrow(() ->
                        new CommandException(String.format("Property not found. ", toAdd.getPostalCode())));

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand otherAddMeetingCommand = (AddMeetingCommand) other;
        return toAdd.equals(otherAddMeetingCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
