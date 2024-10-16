package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.getTypicalClientBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperty.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListPropertiesCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalPropertyBook(),
                getTypicalClientBook());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getPropertyBook(),
                model.getClientBook());
    }

    @Test
    public void listPropertiesCommandGeneration() {
        Command command = new ListPropertiesCommand();

        // Check if command is an instance of ListPropertiesCommand
        assertTrue(command instanceof ListPropertiesCommand,
                "Command should be an instance of ListPropertiesCommand");
    }

    @Test
    public void testExecuteProperties() throws CommandException {
        Command command = new ListPropertiesCommand();
        CommandResult result = command.execute(this.model);
        assertEquals(result.getFeedbackToUser(), "Listed all properties");
    }

    @Test
    public void keywordPropertiesBuyers() {
        assertEquals("properties", ListPropertiesCommand.KEY_WORD);
    }
}
