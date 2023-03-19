import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Task - 1
        String dayString = "14-01-2007";
        try {
            System.out.println("Task-1: Прошло дней со дя рождения \"14 - 01 - 2007\": " +
                    DateUtils.getDaysSinceBirthday(dayString));
        } catch (DateUtils.InvalidDateException e) {
            throw new RuntimeException(e);
        }
        //Task - 2
        dayString = "15 12 2002";
        System.out.println("Task-2: " + DateUtils.parseDateFromString(dayString));
        dayString = "15 12-2002";
        System.out.println("Task-2: " + DateUtils.parseDateFromString(dayString));

        //Task - 3
        ZonedDateTime zonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Europe/Kiev"));
        ZoneId kiev = ZoneId.of("Europe/Kiev");
        LocalTime kievTime = DateUtils.convertTime(zonedDateTime, kiev).toLocalTime();
        System.out.println("Task - 3: " + kievTime.getHour() + ":" + kievTime.getMinute());

        //Task - 4
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        long finish = System.currentTimeMillis();
        System.out.println("Task - 4: " + (double) (finish - start) / 1000);

        //Task - 5
        Random randon = new Random();
        List<LocalDateTime> localDateTimes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int year = randon.nextInt(300) + 1800;
            Month month = Month.of(randon.nextInt(12) + 1);
            int dayOfMonth = randon.nextInt(month.maxLength()) + 1;
            int hour = randon.nextInt(24);
            int minutes = randon.nextInt(60);

            localDateTimes.add(LocalDateTime.of(year, month, dayOfMonth, hour, minutes));
        }

        System.out.println("Task - 5: max date " + DateUtils.findMaxDate(localDateTimes));

        //Task - 6
        LocalDate localDateTest1 = LocalDate.now();
        LocalDate localDateTest2 = LocalDate.now();
        //находится ли объект времени, у которого был вызван данный метод, после другого объекта времени, переданного в качестве аргумента
        System.out.println(localDateTest1.isAfter(localDateTest2));
        //
        System.out.println(localDateTest1.plusDays(12));

        //предназначенный для создания нового экземпляра объекта Temporal с измененным значением.
        LocalDate newLocalDate = localDateTest1.with(ChronoField.YEAR, 2007);
        System.out.println(newLocalDate);
        //
        System.out.println(localDateTest1.plus(Period.ofMonths(2)));

        //get
        System.out.println(localDateTest1.getEra());
        System.out.println(localDateTest1.getChronology());
        System.out.println(localDateTest1.getDayOfMonth());
        System.out.println(localDateTest1.getYear());
        //позволяет получить значение конкретного поля времени из этого объекта.
        System.out.println(localDateTest1.get(ChronoField.DAY_OF_MONTH));
        System.out.println(localDateTest1.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
        System.out.println(localDateTest1.getDayOfWeek());
        System.out.println(localDateTest1.getDayOfYear());
        System.out.println(localDateTest1.getMonthValue());
        //
        System.out.println(localDateTest1.atStartOfDay());
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        //
        Stream<LocalDate> dateStream = startDate.datesUntil(endDate);
        dateStream.forEach(ld -> System.out.print(ld + " "));
        //
        System.out.println();
        System.out.println(localDateTest1.isLeapYear());

    }
}

