package Database;

import Exeptions.ExceptionManager;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.*;


/*! \class public class Database
    \brief Se ocupa cu incarcarea si extragerea datelor legate de data de baze.

 */
public class Database {
    Connection c = null;/*!< Conexiunea la baza de data.*/
    Statement st = null;/*!< Obiectul de tip Statement care moddifica baza de date.*/
    ExceptionManager exept = new ExceptionManager();/*!< Un obiect ExceptionManager care se ocupa de exceptiile din baza de date.*/

    /*! \fn public Database()
        \brief Constructorul clasei

        Creeaza baza de date necesara jocului, mai exact tabela pentru scor, setari, ultima salvare
        si matricea de coliziuni.
        Daca baza de date e deja creata, arunca o exceptie care e tratata.

    */
    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Game.db");
            c.setAutoCommit(false);
            st = c.createStatement();


            String sql = "CREATE TABLE Leaderboard " + "( Score INT )";
            String settings = "CREATE TABLE Settings " + "(Audio INT, Score INT, Life INT)";
            String saves = "CREATE TABLE Save" + "(Save INT)";


          //  String matrixExec = "CREATE TABLE MatrixMap" + "(Matrix CHAR(3000))";
            //String photosExec = "CREATE TABLE Photo " + "(photo BLOB)";

            try {
                st.execute(saves);
                st.execute(settings);
              //  st.execute(matrixExec);
               // st.execute(photosExec);
                st.execute(sql);
            } catch (java.sql.SQLException e) {
                // exept.SqlExeption(e);
                System.out.println("Baza de date deja creata");
            }
            st.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("Opened database successfully");
    }

    /*! \fn public void insertScoreInDatabase(int Score)
       \brief Insereaza scorul in baza de date.

        \param Score Scorul player-ului.
   */
    public void insertScoreInDatabase(int Score) {
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();
                String sql = "INSERT INTO Leaderboard (Score)" + "VALUES ('" + Score + "') ";
                st.executeUpdate(sql);
                st.close();
                c.commit();
                c.close();
            } catch (java.sql.SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);
        }
    }

    /*! \fn public void insertSettingsInDatabase(int Audio,int Score, int Life )
       \brief Insereaza setarile in baza de date.

        \pararm Audio Setarea de audio on/off.
        \param Score Setarea de score on/off.
        \param Life Setarea de life on/off.
   */
    public void insertSettingsInDatabase(int Audio, int Score, int Life) {
        String sql;
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();


                ResultSet rs = st.executeQuery("Select * FROM Settings ;");
                if (rs.next() == false) {

                    sql = "INSERT INTO Settings (Audio,Score,Life)" + "VALUES (0,0,0) ";
                    st.executeUpdate(sql);
                }

                sql = "UPDATE Settings SET Audio =" + Audio + ",Score =" + Score + ",Life =" + Life + "";

                st.executeUpdate(sql);
                st.close();
                c.commit();
                c.close();
            } catch (java.sql.SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);
        }
    }

    /*! \fn public void insertSaveInDatabase(int Save)
       \brief Insereaza ultima salvare in baza de date.

        \pararm Save Salvarea introdusa in baza de date.
   */
    public void insertSaveInDatabase(int Save) {
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();
                ResultSet rs = st.executeQuery("Select * FROM Save ;");
                String sql;
                if (rs.next() == false) {

                    sql = "INSERT INTO Save (Save)" + "VALUES (1) ";
                    st.executeUpdate(sql);
                }
                sql = "UPDATE Save SET Save = " + Save + "";
                st.executeUpdate(sql);
                st.close();
                c.commit();
                c.close();
            } catch (java.sql.SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);
        }
    }

    /*! \fn public int extractSave()
       \brief Returneaza ultima salvare introdusa in baza de date.
   */
    public int extractSave() {
        int result = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();
                ResultSet rs = st.executeQuery("Select * FROM Save ;");
                result = rs.getInt("Save");

                rs.close();
                st.close();
                c.commit();
                c.close();
            } catch (SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);
        }

        return result;
    }

    /*! \fn public boolean[] extractSettings()
       \brief Returneaza un boolean vector de 3 elemente ce reprezinta setarile pt: Audio, Score, Life.
   */
    public boolean[] extractSettings() {
        boolean[] result = new boolean[3];
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();

                ResultSet rs = st.executeQuery("Select * FROM Settings ;");

                result[0] = rs.getBoolean("Audio");
                result[1] = rs.getBoolean("Score");
                result[2] = rs.getBoolean("Life");

                rs.close();
                st.close();
                c.commit();
                c.close();
            } catch (SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);
        }

        return result;
    }

    /*! \fn public int extractPlace(int Score)
       \brief Returuneaza locul pe care se afla player-ul in leaderboard.

       \param Score Score-ul player-ului.
   */
    public int extractPlace(int Score) {
        List<Integer> result = new ArrayList<>();
        int res = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();
                String sql = "SELECT * FROM LeaderBoard ";
                // sql="Select * FROM LABORATOR ;";

                ResultSet rs = st.executeQuery(sql);
                List<ResultSet> a = new ArrayList<>();

                while (rs.next()) {
                    a.add(rs);
                    result.add(rs.getInt("Score"));
                }
                Collections.sort(result, Collections.reverseOrder());

                res = result.indexOf(Score);
                rs.close();
                st.close();
                c.commit();
                c.close();
            } catch (java.sql.SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);

        }
        return res + 1;
    }

    /*! \fn public int[] extractFirst10()
       \brief Returneaza un vector int de 3 elemente ce reprezinta primele 10 locuri din leaderboard.

   */
    public int[] extractFirst10() {
        List<Integer> result = new ArrayList<>();
        int[] firsts = new int[10];
        int res = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Game.db");
                c.setAutoCommit(false);
                st = c.createStatement();
                String sql = "SELECT * FROM Leaderboard ";
                // sql="Select * FROM LABORATOR ;";

                ResultSet rs = st.executeQuery(sql);
                List<ResultSet> a = new ArrayList<>();

                while (rs.next()) {
                    a.add(rs);
                    result.add(rs.getInt("Score"));
                }
                Collections.sort(result, Collections.reverseOrder());

                for (int i = 0; i < result.size(); i++) {
                    if(i==10){break;}
                    firsts[i] = result.get(i);

                }
                st.close();
                c.commit();
                c.close();
            } catch (java.sql.SQLException e) {
                exept.SqlExeption(e);
            }
        } catch (ClassNotFoundException e) {
            exept.ClassConnectionException(e);

        }
        return firsts;
    }

    public String txtToString(String Path) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Path));
        } catch (FileNotFoundException e) {
            exept.FileExeption(e);
        }
        StringBuilder sb = new StringBuilder();
        try {
            String lines = br.readLine();
            while (lines != null) {
                sb.append(lines);
                sb.append("\n");
                lines = br.readLine();
            }
        } catch (IOException e) {
            exept.StringExeption(e);
        }

        String string = sb.toString();
        return string;

    }

    public List<String> formatStringToList(String string) {
        String[] numbers = string.split("\n");
        List<String> lista = new ArrayList<String>();

        for (int i = 0; i < numbers.length; i++) {
            lista.add(i, numbers[i]);
            System.out.println(lista.get(i));
        }

        return lista;
    }

    public void InsertPhotos() {

        try {
            String INSERT_PICTURE = "INSERT INTO Photo (photo) VALUES (?)";
            FileInputStream fis = null;
            PreparedStatement ps = null;
            File file = null;
            try {

                file = new File("src/IMG-20210902-WA0000.jpg");
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                exept.FileExeption(e);
            }

            ps = c.prepareStatement(INSERT_PICTURE);
            ps.setBinaryStream(1, fis, (int) file.length());
            ps.executeUpdate();
        } catch (SQLException e) {
            exept.SqlExeption(e);
        }
    }
}



