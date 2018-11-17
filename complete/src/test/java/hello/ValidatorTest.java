package hello;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ValidatorTest {

    private static final String DT0 = "2020-08-01T13:00";
    private static final String DT1 = "2020-01-01T13:00";
    private static final String DT2 = "2020-01-01T13:00+01:00";
    private static final String DT3 = "2020-01-01T13:00Z";
    private static final String DT4 = "01.01.2018 13:00";

    private static final String DT_LOCAL = "20\\d{2}\\-[0-1]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d";
    private static final String DT_OFFSET = "20\\d{2}\\-[0-1]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d[+-]\\d{2}:\\d{2}";
    private static final String DT_ZULU = "20\\d{2}\\-[0-1]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\dZ";

    @Test
    public void testDates() throws ParseException {
        OffsetDateTime dt = null;
        // checks
        dt = getDate(DT0);
        Assert.assertEquals("2020-08-01T13:00+02:00", dt.toString());
        dt = getDate(DT1);
        Assert.assertEquals("2020-01-01T13:00+01:00", dt.toString());
        dt = getDate(DT2);
        Assert.assertEquals("2020-01-01T13:00+01:00", dt.toString());
        dt = getDate(DT3);
        Assert.assertEquals("2020-01-01T13:00Z", dt.toString());
    }

    @Test(expected = ParseException.class)
    public void testBadDates() throws ParseException {
        // checks
        getDate(DT4);
    }

    @Test
    public void test() throws ParseException {
        Quote quote = new Quote();
        quote.setType("TYPE1");
        quote.setValue(new Value());
        quote.getValue().setDate(getDate(DT2));

        System.out.println(quote);

        Validator validator = getValidator();
        Set<ConstraintViolation<Quote>> violations = validator.validate(quote);
        for (ConstraintViolation<Quote> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    private OffsetDateTime getDate(String str) throws ParseException {
        OffsetDateTime dt = null;
        final ZoneId zoneId = ZoneId.systemDefault();
        if (str.matches(DT_LOCAL)) {
            LocalDateTime parsedDate = LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            dt = parsedDate.atZone(zoneId).toOffsetDateTime();
            System.out.println(str + " local -> " + parsedDate + " - " + dt);

        } else if (str.matches(DT_OFFSET)) {
            OffsetDateTime parsedDate = OffsetDateTime.parse(str, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            dt = parsedDate;
            System.out.println(str + " offset -> " + parsedDate + " - " + dt);

        } else if (str.matches(DT_ZULU)) {
            ZonedDateTime parsedDate = ZonedDateTime.parse(str, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            dt = parsedDate.toOffsetDateTime();
            System.out.println(str + " zoned -> " + parsedDate + " - " + dt);

        } else {
            throw new ParseException("Invalid date time format", 1);
        }
        return dt;
    }

    private Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

}
