package world.ucode;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;
    public static boolean saving = true;

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
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        createNewTable();
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS game (\n" +
                " id integer PRIMARY KEY," +
                " name text NOT NULL UNIQUE," +
                " type integer," +
                " max integer," +
                " age integer, hunger integer, thirst integer," +
                " happy integer, clean integer, tired integer," +
                " health integer, isAlive integer, isSick integer," +
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

    public static void insertNewDB(String name, int type, int max) {
        String sql = "INSERT INTO " +
                "game(name,type,max,age,hunger,thirst,happy,clean,tired,health,isAlive,isSick,active) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setInt(2, type);
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

            pst.setInt(13, 1); // ative

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveDB(Model m) {
        String sql = "UPDATE game SET" +
                " active = 0" +
                " name,type,max,age,hunger,thirst,happy,clean,tired,health,isAlive,isSick" +
                " WHERE id=" + m.id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") + "\t" +
//                        rs.getString("name") + "\t" +
//                        rs.getInt("type"));
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDisactive() {
        String sql = "UPDATE game SET active=0 WHERE active=1";

        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<String> selectNames() {
        ArrayList<String> names = new ArrayList<>();
        String sql = "SELECT id,name FROM game";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                names.add(rs.getInt("id") + ". " + rs.getString("Name"));
//                System.out.println(rs.getInt("id") + ". " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return names;
    }

    public static void selectAll(){
        String sql = "SELECT id, name, type FROM game";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") + "\t" +
//                        rs.getString("name") + "\t" +
//                        rs.getInt("type"));
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadModel(Model model){
        String sql = "SELECT * FROM game WHERE active=1";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            model.id = rs.getInt("id");
            model.setName(rs.getString("name"));
//            model.setType(rs.getInt(3));
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
}
