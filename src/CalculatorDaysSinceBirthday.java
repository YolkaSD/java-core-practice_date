import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class CalculatorDaysSinceBirthday {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Метод возвращает количество дней между днем рождения и текущей датой в формате int.
     *
     * @param birthdayDateString строка, содержащая дату рождения в формате dd-MM-yyyy
     * @return количество дней между днем рождения и текущей датой
     * @throws InvalidDateException если строка содержит неверный формат даты или дата рождения находится в будущем
     */
    public static int getDaysSinceBirthday(String birthdayDateString) throws  InvalidDateException{
        if (birthdayDateString == null) {
            throw new InvalidDateException("Birthday date string is null");
        }
        try {
            LocalDate birthdayDate = LocalDate.parse(birthdayDateString, FORMATTER);
            LocalDate today = LocalDate.now();

            int days = (int) ChronoUnit.DAYS.between(birthdayDate, today);

            if (birthdayDate.isAfter(today)) {
                throw new InvalidDateException("Birthday date is in the future: " + birthdayDateString +
                        ". The date should be in format dd-MM-yyyy.");
            }

            return days;
        } catch (DateTimeParseException e ) {
            throw new InvalidDateException("Invalid date format: " + birthdayDateString +
                    ". The date should be in format dd-MM-yyyy.");
        }
    }

    static class InvalidDateException extends Exception {
        public InvalidDateException(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return getMessage();
        }
    }
}
