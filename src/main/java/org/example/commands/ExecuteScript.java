package org.example.commands;

import org.example.Main;
import org.example.exceptions.IncorrectArgsNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Команда для выполнения скрипта из файла.
 */
public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", 1);
    }

    @Override
    public void execute() {
        try {
            String add = "src/main/resources/";
            String file = Main.console.getToken(1);
            String fileName = add + file;

            // Читаем файл скрипта
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue; // Пропускаем пустые строки
                    }

                    // Выводим выполняемую команду
                    System.out.println("Выполнение команды: " + line);

                    // Разбиваем строку на команду и аргументы
                    String[] tokens = Arrays.stream(line.split(" "))
                            .filter(s -> !s.isEmpty())
                            .toArray(String[]::new);

                    // Устанавливаем токены для консоли
                    Main.console.setTokens(tokens);

                    // Получаем команду
                    Command command = Main.inv.commands.get(tokens[0]);
                    if (command == null) {
                        System.out.println("Команда неизвестная: " + tokens[0]);
                        continue;
                    }

                    // Проверяем количество аргументов
                    if (tokens.length - 1 != command.getArgsAmount()) {
                        System.out.println("Неверное количество аргументов для команды: " + tokens[0]);
                        continue;
                    }

                    // Выполняем команду
                    try {
                        command.execute();
                    } catch (IncorrectArgsNumber e) {
                        System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Не указано имя файла для выполнения скрипта.");
        }
    }
}