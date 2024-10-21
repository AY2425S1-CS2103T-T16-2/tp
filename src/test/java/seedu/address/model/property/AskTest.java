package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASK_ADMIRALTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASK_BEDOK;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AskTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ask(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Ask(invalidCode));
    }

    @Test
    public void isValidAsk() {
        // null name
        assertThrows(NullPointerException.class, () -> Ask.isValidAsk(null));

        // invalid name
        assertFalse(Ask.isValidAsk("")); // empty string
        assertFalse(Ask.isValidAsk(" ")); // spaces only
        assertFalse(Ask.isValidAsk("^")); // only non-alphanumeric characters
        assertFalse(Ask.isValidAsk("-00000")); // contains non-alphanumeric characters
        assertFalse(Ask.isValidAsk("00&000")); // contains non-alphanumeric characters
        assertFalse(Ask.isValidAsk("00.000")); // contains decimal characters
        assertFalse(Ask.isValidAsk("00000 ")); // contains space
        assertFalse(Ask.isValidAsk("000 0000")); // contains space delimiter
        assertFalse(Ask.isValidAsk("$1000000")); // contains dollar character

        // valid name
        assertTrue(Ask.isValidAsk("000000")); // alphabets only
        assertTrue(Ask.isValidAsk("123456"));
        assertTrue(Ask.isValidAsk("999999"));
    }

    @Test
    public void equals() {
        Ask ask = new Ask(VALID_ASK_BEDOK);

        // same values -> returns true
        assertTrue(ask.equals(new Ask(VALID_ASK_BEDOK)));

        // same object -> returns true
        assertTrue(ask.equals(ask));

        // null -> returns false
        assertFalse(ask.equals(null));

        // different types -> returns false
        assertFalse(ask.equals(5.0f));

        // different values -> returns false
        assertFalse(ask.equals(new Ask(VALID_ASK_ADMIRALTY)));
    }
}
