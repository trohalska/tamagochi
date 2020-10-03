package world.ucode;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public static void connect() {
        String url = "jdbc:sqlite:game.db";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection do not work.");
            e.printStackTrace();
        }
    }

    public static void createNewDB() {
        try {
            connect();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created." +
                        "The driver name is " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        createNewTable();
        createSettingsTable();
        updateDisactive();
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS game (\n" +
                " id integer PRIMARY KEY," +
                " name text NOT NULL," +
                " type text NOT NULL," +
                " max integer," +
                " age integer, hunger integer, thirst integer," +
                " happy integer, clean integer, tired integer," +
                " health integer, isAlive integer, isSick integer," +
                " time text, " +
                " active integer" +
                ");";
        try {
           st = conn.createStatement();
           st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateDisactive() {
        String sql = "UPDATE game SET active=0 WHERE active=1;";
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateActive(int id) {
        String sql = "UPDATE game SET active=1 WHERE id=? ;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void insertNewDB(String name, String type, int max) {
        String sql = "INSERT INTO " +
                "game(name,type,max,age,hunger,thirst,happy,clean,tired,health,isAlive,isSick,time,active) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, type);
            pst.setInt(3, max);

            pst.setInt(4, 0); // age
            pst.setInt(5, max * 2/10); // hunger
            pst.setInt(6, max * 2/10); // thirst
            pst.setInt(7, max * 2/10); // happy
            pst.setInt(8, max * 8/10); // clean
            pst.setInt(9, 0); // tired
            pst.setInt(10, max); // health
            pst.setInt(11, 1); // isAlive
            pst.setInt(12, 0); // isSick
            pst.setString(13, "0");  // time
            pst.setInt(14, 1); // active

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadModel(Model model){
        String sql = "SELECT * FROM game WHERE active=1;";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            model.id = rs.getInt("id");
            model.setName(rs.getString("name"));
            model.setType(rs.getString("type"));
            model.setTime(rs.getString("time"));
            model.setMax(rs.getInt("max"));

            model.setAge(rs.getInt("age"));
            model.setHunger(rs.getInt("hunger"));
            model.setThirst(rs.getInt("thirst"));
            model.setHappiness(rs.getInt("happy"));
            model.setCleanliness(rs.getInt("clean"));
            model.setTired(rs.getInt("tired"));
            model.setHealth(rs.getInt("health"));
            model.setAlive(rs.getInt("isAlive") > 0);
            model.setSick(rs.getInt("isSick") > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveModel(Model m) {
        String sql = "UPDATE game SET" +
                " age=?, hunger=?, thirst=?, happy=?, clean=?," +
                " tired=?, health=?, isAlive=?, isSick=?, active=?," +
                " time=datetime('now','localtime') WHERE id=?;";
        try {
            pst = conn.prepareStatement(sql);

            pst.setInt(1, m.getAge());
            pst.setInt(2, m.getHunger());
            pst.setInt(3, m.getThirst());
            pst.setInt(4, m.getHappiness());
            pst.setInt(5, m.getCleanliness());
            pst.setInt(6, m.getTired());
            pst.setInt(7, m.getHealth());
            pst.setInt(8, m.getAliveInt());
            pst.setInt(9, m.getSickInt());
            pst.setInt(10, 0);

            pst.setInt(11, m.id);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteModel(int id) {
        String sql = "DELETE FROM game WHERE id=?;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<String> selectNames() {
        ArrayList<String> names = new ArrayList<>();
        String sql = "SELECT id,name FROM game;";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                names.add(rs.getInt("id") + ". " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return names;
    }

    public static void CloseDB() throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (st != null) {
            st.close();
            st = null;
        }
        if (pst != null) {
            pst.close();
            pst = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    /** SETTINGS */
    public static void createSettingsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS settings (" +
                " id integer PRIMARY KEY, sounds text, theme text, difficulty text);";
        try {
            st = conn.createStatement();
            st.execute(sql);

            String sql1 = "INSERT INTO settings(sounds,theme,difficulty) VALUES(?,?,?)";
            pst = conn.prepareStatement(sql1);
//            pst.setInt(1, 1);
            pst.setString(1, "ON");
            pst.setString(2, "Standart");
            pst.setString(3, "Easy");
            pst.executeUpdate();

            String sql2 = "DELETE FROM settings WHERE id != 1;";
            st.execute(sql2);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void updateSettings(String sounds, String theme, String difficulty) {
        String sql = "UPDATE settings SET sounds=?, theme=?, difficulty=? WHERE id=1;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, sounds);
            pst.setString(2, theme);
            pst.setString(3, difficulty);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static String getSoundSettings() {
        String sql = "SELECT sounds FROM settings WHERE id=1;";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            return rs.getString("sounds");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static String getThemeSettings() {
        String sql = "SELECT theme FROM settings WHERE id=1;";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            return rs.getString("theme");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static String getDifficultySettings() {
        String sql = "SELECT difficulty FROM settings WHERE id=1;";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            return rs.getString("difficulty");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
