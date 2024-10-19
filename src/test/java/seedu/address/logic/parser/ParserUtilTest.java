package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.property.Ask;
import seedu.address.model.property.Bid;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Type;
import seedu.address.model.property.Unit;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_POSTALCODE = "123 45";
    private static final String INVALID_UNIT = "11";
    private static final String INVALID_TYPE = "PUBLIC";
    private static final String INVALID_ASK = "11.00";
    private static final String INVALID_BID = "11.00";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_POSTALCODE = "123456";
    private static final String VALID_UNIT = "11-11";
    private static final String VALID_TYPE = "HDB";
    private static final String VALID_ASK = "60000";
    private static final String VALID_BID = "50000";

    private static final String WHITESPACE = " \t\r\n";

    // Test for parsing index
    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    // Tests for person Name
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    // Tests for person Phone
    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    // Tests for person Address
    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    // Tests for person Email
    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    // Tests for Tags
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
        assertEquals(expectedTagSet, actualTagSet);
    }

    // Tests for client Name
    @Test
    public void parseClientName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientName(null));
    }

    @Test
    public void parseClientName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientName(INVALID_NAME));
    }

    @Test
    public void parseClientName_validValueWithoutWhitespace_returnsClientName() throws Exception {
        seedu.address.model.client.Name expectedName = new seedu.address.model.client.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseClientName(VALID_NAME));
    }

    @Test
    public void parseClientName_validValueWithWhitespace_returnsTrimmedClientName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        seedu.address.model.client.Name expectedName = new seedu.address.model.client.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseClientName(nameWithWhitespace));
    }

    // Tests for client Phone
    @Test
    public void parseClientPhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientPhone(null));
    }

    @Test
    public void parseClientPhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientPhone(INVALID_PHONE));
    }

    @Test
    public void parseClientPhone_validValueWithoutWhitespace_returnsClientPhone() throws Exception {
        seedu.address.model.client.Phone expectedPhone = new seedu.address.model.client.Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseClientPhone(VALID_PHONE));
    }

    @Test
    public void parseClientPhone_validValueWithWhitespace_returnsTrimmedClientPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        seedu.address.model.client.Phone expectedPhone = new seedu.address.model.client.Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseClientPhone(phoneWithWhitespace));
    }

    // Tests for client Email
    @Test
    public void parseClientEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientEmail((String) null));
    }

    @Test
    public void parseClientEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientEmail(INVALID_EMAIL));
    }

    @Test
    public void parseClientEmail_validValueWithoutWhitespace_returnsClientEmail() throws Exception {
        seedu.address.model.client.Email expectedEmail = new seedu.address.model.client.Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(VALID_EMAIL));
    }

    @Test
    public void parseClientEmail_validValueWithWhitespace_returnsTrimmedClientEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        seedu.address.model.client.Email expectedEmail = new seedu.address.model.client.Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(emailWithWhitespace));
    }

    // Tests for property PostalCode
    @Test
    public void parsePropertyPostalCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePostalCode((String) null));
    }

    @Test
    public void parsePropertyPostalCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePostalCode(INVALID_POSTALCODE));
    }

    @Test
    public void parsePropertyPostalCode_validValueWithoutWhitespace_returnsClientEmail() throws Exception {
        PostalCode expectedPostalCode = new PostalCode(VALID_POSTALCODE);
        assertEquals(expectedPostalCode, ParserUtil.parsePostalCode(VALID_POSTALCODE));
    }

    @Test
    public void parsePropertyPostalCode_validValueWithWhitespace_returnsTrimmedClientEmail() throws Exception {
        String postalCodeWithWhitespace = WHITESPACE + VALID_POSTALCODE + WHITESPACE;
        PostalCode expectedPostalCode = new PostalCode(VALID_POSTALCODE);
        assertEquals(expectedPostalCode, ParserUtil.parsePostalCode(postalCodeWithWhitespace));
    }

    // Tests for property Unit
    @Test
    public void parsePropertyUnit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUnit((String) null));
    }

    @Test
    public void parsePropertyUnit_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUnit(INVALID_UNIT));
    }

    @Test
    public void parsePropertyUnit_validValueWithoutWhitespace_returnsClientEmail() throws Exception {
        Unit expectedUnit = new Unit(VALID_UNIT);
        assertEquals(expectedUnit, ParserUtil.parseUnit(VALID_UNIT));
    }

    @Test
    public void parsePropertyUnit_validValueWithWhitespace_returnsTrimmedClientEmail() throws Exception {
        String unitWithWhitespace = WHITESPACE + VALID_UNIT + WHITESPACE;
        Unit expectedUnit = new Unit(VALID_UNIT);
        assertEquals(expectedUnit, ParserUtil.parseUnit(unitWithWhitespace));
    }

    // Tests for property Type
    @Test
    public void parsePropertyType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseType((String) null));
    }

    @Test
    public void parsePropertyType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseType(INVALID_TYPE));
    }

    @Test
    public void parsePropertyType_validValueWithoutWhitespace_returnsClientEmail() throws Exception {
        Type expectedType = new Type(VALID_TYPE);
        assertEquals(expectedType, ParserUtil.parseType(VALID_TYPE));
    }

    @Test
    public void parsePropertyType_validValueWithWhitespace_returnsTrimmedClientEmail() throws Exception {
        String typeWithWhitespace = WHITESPACE + VALID_TYPE + WHITESPACE;
        Type expectedType = new Type(VALID_TYPE);
        assertEquals(expectedType, ParserUtil.parseType(typeWithWhitespace));
    }

    // Tests for property Ask
    @Test
    public void parsePropertyAsk_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAsk((String) null));
    }

    @Test
    public void parsePropertyAsk_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAsk(INVALID_ASK));
    }

    @Test
    public void parsePropertyAsk_validValueWithoutWhitespace_returnsClientEmail() throws Exception {
        Ask expectedAsk = new Ask(VALID_ASK);
        assertEquals(expectedAsk, ParserUtil.parseAsk(VALID_ASK));
    }

    @Test
    public void parsePropertyAsk_validValueWithWhitespace_returnsTrimmedClientEmail() throws Exception {
        String askWithWhitespace = WHITESPACE + VALID_ASK + WHITESPACE;
        Ask expectedAsk = new Ask(VALID_ASK);
        assertEquals(expectedAsk, ParserUtil.parseAsk(askWithWhitespace));
    }

    // Tests for property Bid
    @Test
    public void parsePropertyBid_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBid((String) null));
    }

    @Test
    public void parsePropertyBid_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBid(INVALID_BID));
    }

    @Test
    public void parsePropertyBid_validValueWithoutWhitespace_returnsClientEmail() throws Exception {
        Bid expectedBid = new Bid(VALID_BID);
        assertEquals(expectedBid, ParserUtil.parseBid(VALID_BID));
    }

    @Test
    public void parsePropertyBid_validValueWithWhitespace_returnsTrimmedClientEmail() throws Exception {
        String bidWithWhitespace = WHITESPACE + VALID_BID + WHITESPACE;
        Bid expectedBid = new Bid(VALID_BID);
        assertEquals(expectedBid, ParserUtil.parseBid(bidWithWhitespace));
    }
}
