import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        String dayString = "15-12-2002";
        int daysFromBirthday = DaysFromBirthday(dayString);
        System.out.println(daysFromBirthday);
    }

    //Написать метод, который будет принимать дату рождения в строке и возвращать количество дней в формате инт,
    // которые прошли с этого дня.
    public static int DaysFromBirthday(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthdayDate = LocalDate.parse(birthday, formatter);
        LocalDate nowDate = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(birthdayDate, nowDate);
    }
}