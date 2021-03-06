package classes;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс {@code Dragon} отвечает за информацию о драконах.
 */
@SuppressWarnings("ALL")
public class Dragon implements Comparable<Dragon>, Serializable {
    /**
     * Уникальный номер дракона.
     */
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * Имя дракона.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * Месторасположение дракона.
     */
    private Coordinates coordinates; //Поле не может быть null
    /**
     * Дата и время создание экземпляра.
     */
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * Возраст дракона.
     */
    private int age; //Значение поля должно быть больше 0
    /**
     * Флаг, умеет ли дракон разговаривать.
     */
    private boolean speaking;
    /**
     * Типа дракона.
     */
    private DragonType type; //Поле не может быть null
    /**
     * Характер дракона.
     */
    private DragonCharacter character; //Поле не может быть null

    private String login;

    public boolean check() {
        return id > 0 && !name.isEmpty() && age > 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public DragonType getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSpeaking() {
        return speaking;
    }

    public void setSpeaking(boolean speaking) {
        this.speaking = speaking;
    }

    @Override
    public int compareTo(Dragon o) {
        return this.getName().compareTo(o.getName());

    }
}
