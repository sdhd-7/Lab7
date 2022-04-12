package serv;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import programm.defaults.Dragon;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class Init {
    private final List<Dragon> dragons = Collections.synchronizedList(new LinkedList<>());
    private final Gson gson = new Gson();
    private File file;
    private final List<String> history_list = Collections.synchronizedList(new LinkedList<>());
    private final Date initdate;

    public Init(String filename) {
        try {
            file = new File(filename);
        } catch (Exception ex) {
            System.err.println("Файл не существует, либо не доступен для чтения");
            System.exit(1);
        }
        this.initdate = new Date();
        try (BufferedReader inputStreamReader = new BufferedReader(new FileReader(file))) {
            System.out.println("Идёт загрузка коллекции " + filename);
            String nextLine;
            StringBuilder result = new StringBuilder();
            while ((nextLine = inputStreamReader.readLine()) != null)
                result.append(nextLine);

            try {
                Type collectionType = new TypeToken<LinkedList<Dragon>>() {
                }.getType();
                LinkedList<Dragon> addedDragon = gson.fromJson(result.toString(), collectionType);
                for (Dragon s : addedDragon) {
                    if (!dragons.contains(s) && s.check()) dragons.add(s);
                }
            } catch (JsonSyntaxException ex) {
                System.err.println("Ошибка синтаксиса Json. Коллекция не может быть загружена.");
                System.exit(1);
            }
            System.out.println("Коллекция успешно загружена. Добавлено " + dragons.size() + " элементов.");
        } catch (Exception ex) {
            System.err.println("Файл не существует или недоступен для чтения.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }

    /**
     * Сохранят коллекцию в файл.
     */
    public void save() {
        history_list.add("save");
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(gson.toJson(dragons));
            System.out.println("Данные успешно сохранены");
        } catch (Exception ex) {
            System.out.println("Коллекция не может быть записана в файл.");
        }
    }

    public List<Dragon> getDragons() {
        return dragons;
    }

    public List<String> getHistory_list() {
        return history_list;
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public String toString() {
        return "Тип коллекции: " + dragons.getClass() +
                "\nТип элементов: " + Dragon.class +
                "\nДата инициализации: " + initdate +
                "\nКоличество элементов: " + dragons.size();
    }
}
