package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalClientBook;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperty.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalPropertyBook(),
                getTypicalClientBook(), getTypicalMeetingBook());
    }

    @Test
    public void execute_newMeeting_success() {
        Meeting validMeeting = new MeetingBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getPropertyBook(),
                model.getClientBook(), model.getMeetingBook());
        expectedModel.addMeeting(validMeeting);

        assertCommandSuccess(new AddMeetingCommand(validMeeting), model,
                String.format(AddMeetingCommand.MESSAGE_SUCCESS, Messages.format(validMeeting)),
                expectedModel);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(0);
        assertCommandFailure(new AddMeetingCommand(meetingInList), model,
                AddMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }
}