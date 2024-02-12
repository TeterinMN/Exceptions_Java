import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class DataEntryApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter writer = null;

        try {
            // Получаем данные от пользователя
            System.out.println("Введите данные в следующем порядке, разделенные пробелом:");
            System.out.println("Фамилия Имя Отчество дата рождения (в формате dd.mm.yyyy) номер телефона пол (f или m)");

            // Считываем строку с консоли
            String userInput = scanner.nextLine();

            // Разделяем данные по пробелам
            String[] userData = userInput.split(" ");

            // Проверяем, что количество данных соответствует требуемому
            if (userData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            // Получаем отдельные значения
            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String birthDateStr = userData[3];
            long phoneNumber = Long.parseLong(userData[4]);
            char gender = userData[5].charAt(0);

            // Проверяем формат даты рождения
            LocalDate birthDate = LocalDate.parse(birthDateStr, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            // Проверяем пол
            if (gender != 'm' && gender != 'f') {
                throw new IllegalArgumentException("Неверный формат пола");
            }

            // Открываем файл для записи (добавляем флаг APPEND, чтобы данные добавлялись в конец файла)
            writer = new BufferedWriter(new FileWriter(lastName + ".txt", true));

            // Создаем строку для записи в файл в нужном формате
            String dataLine = String.format("%s %s %s %s %d %c%n", lastName, firstName, middleName, birthDateStr, phoneNumber, gender);

            // Записываем данные в файл
            writer.write(dataLine);

            System.out.println("Данные успешно записаны в файл.");
        } catch (IllegalArgumentException e) {
            // Обрабатываем ошибку неверных данных
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            // Обрабатываем ошибку ввода-вывода
            e.printStackTrace();
        } catch (Exception e) {
            // Обрабатываем другие ошибки
            e.printStackTrace();
        } finally {
            // Закрываем соединение с файлом
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                // Обрабатываем ошибку закрытия файла
                e.printStackTrace();
            }
            // Закрываем сканер
            scanner.close();
        }
    }
}