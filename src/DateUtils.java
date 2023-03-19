import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DateUtils {
    //private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * @dateTimeFormatters - Список объектов DateTimeFormatter, содержащий предопределенные форматы даты и времени.
     */
    private final static List<DateTimeFormatter> DATE_TIME_FORMATTERS = new ArrayList<>();

    static {
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("dd MM yyyy"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy MM dd"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    /**
     * Метод возвращает количество дней между днем рождения и текущей датой в формате int.
     *
     * @param birthdayDateString строка, содержащая дату рождения в формате dd-MM-yyyy
     * @return количество дней между днем рождения и текущей датой
     * @throws InvalidDateException если строка содержит неверный формат даты или дата рождения находится в будущем
     */
    public static int getDaysSinceBirthday(String birthdayDateString) throws InvalidDateException {
        if (birthdayDateString == null) {
            throw new InvalidDateException("Birthday date string is null");
        }
        try {
            LocalDate birthdayDate = LocalDate.parse(birthdayDateString, DATE_TIME_FORMATTERS.get(2));
            LocalDate today = LocalDate.now();

            if (birthdayDate.isAfter(today)) {
                throw new InvalidDateException("Birthday date is in the future: " + birthdayDateString + ". The date should be in format dd-MM-yyyy.");
            }

            return (int) ChronoUnit.DAYS.between(birthdayDate, today);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format: " + birthdayDateString + ". The date should be in format dd-MM-yyyy.");
        }
    }

    /**
     * Парсит строку даты в неизвестном формате и возвращает объект LocalDate.
     *
     * @param dateString строка, содержащая дату в неизвестном формате
     * @return объект LocalDate, если формат даты соответствует любому из форматов, указанных в списке
     * dateTimeFormatters, в противном случае null
     * @throws DateTimeParseException, если строку даты нельзя разобрать ни одним из
     */
    public static LocalDate parseDateFromString(String dateString) {
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date string: " + dateString + " with formatter: " + formatter.toString() + "Error message: " + e.getMessage());
            }
        }
        return null;
    }

    /**
     * Конвертирует время из одной временной зоны в другую.
     *
     * @param zonedDateTime исходное время в виде объекта ZonedDateTime, не может быть null
     * @param targetZoneId  целевая временная зона в виде объекта ZoneId, не может быть null
     * @return время в целевой временной зоне в виде объекта ZonedDateTime
     * @throws NullPointerException если zonedDateTime или targetZoneId равны null
     */
    public static ZonedDateTime convertTime(ZonedDateTime zonedDateTime, ZoneId targetZoneId) {
        Objects.requireNonNull(zonedDateTime, "zonedDateTime parameter cannot be null");
        Objects.requireNonNull(targetZoneId, "targetZoneId parameter cannot be null");
        return zonedDateTime.withZoneSameInstant(targetZoneId);
    }

    /**
     * Метод findMaxDate находит наиболее старшую дату из листа localDateList
     *
     * @param localDateList содержит в себе список дат
     * @return возвращает наиболее старшую дату
     * @throws IllegalArgumentException Если список localDateList пустой.
     */
    public static LocalDateTime findMaxDate(List<LocalDateTime> localDateList) {
        if (localDateList.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        return localDateList.stream()
                .max(LocalDateTime::compareTo)
                .orElse(null);
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
