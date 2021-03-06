package commands;

import classes.Dragon;
import managers.DBManager;
import serv.Init;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class AddIfMaxComm extends AbstractComm {
    public AddIfMaxComm(Init m) {
        super(m);
    }

    @Override
    public synchronized String make(Dragon mainCompetitor) {
        getMaker().getHistory_list().add("add if max");
        if (getMaker().getDragons().size() != 0) {
            Dragon competitor = Collections.max(getMaker().getDragons());
            if (competitor.compareTo(mainCompetitor) < 0) {
                try {
                    ResultSet temp = DBManager.getInstance().getConnection().createStatement().executeQuery("SELECT nextval('serial')");
                    temp.next();
                    long x = temp.getLong("nextval");
                    //System.out.println(x);
                    mainCompetitor.setId(x);
                    DBManager.getInstance().getConnection().createStatement().execute("" +
                            "insert into dragon VALUES (" + mainCompetitor.getId() + ",'" + mainCompetitor.getName() + "'," +
                            mainCompetitor.getCoordinates().getX() + ',' + mainCompetitor.getCoordinates().getY() + ",'" +
                            getMaker().getGson().toJson(mainCompetitor.getCreationDate()) + "'," + mainCompetitor.getAge()
                            + ',' + mainCompetitor.isSpeaking() + ",'" + getMaker().getGson().toJson(mainCompetitor.getType())
                            + "','" + getMaker().getGson().toJson(mainCompetitor.getCharacter())
                            + "','" + mainCompetitor.getLogin() + "')");
                } catch (SQLException e) {
                    return "Не удалось добавить элемент.";
                }
                getMaker().getDragons().add(mainCompetitor);
                getMaker().save();
                return "Элемент успешно добавлен.";
            } else return "Не удалось добавить элемент.";
        } else return "Элемент не с чем сравнивать. Коллекция пуста.";
    }
}
