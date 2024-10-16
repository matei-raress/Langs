import java.sql.* ;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Connection c = null ;
    Statement st=null ;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Lab.db");
            c.setAutoCommit(false);
            st= c.createStatement() ;
            String sql = "CREATE TABLE LABORATOR " +
                    "(May_2019 INT ," +
                    " May_2018 INT , " +
                    " Change CHAR(10) , " +
                    " Programming_Language CHAR(20), " +
                    " Ratings CHAR(10))";



            st.execute(sql) ;

            sql=" INSERT  INTO LABORATOR (May_2019, May_2018, Change, Programming_Language, Ratings)"+
            "VALUES(1, 1, '-0.38%','Java','14.243%');";


            st.executeUpdate(sql);
            sql=" INSERT  INTO LABORATOR (May_2019, May_2018, Change, Programming_Language, Ratings)"+
                    "VALUES(2, 2, '+0.24%','C++','16.005%');";

            st.executeUpdate(sql);
            sql=" INSERT  INTO LABORATOR (May_2019, May_2018, Change, Programming_Language, Ratings)"+
                    "VALUES(3, 3, '+0.43%','JavaScrip','7.830%');";


            st.executeUpdate(sql);
            sql=" INSERT  INTO LABORATOR (May_2019, May_2018, Change, Programming_Language, Ratings)"+
                    "VALUES(4, 4, '+2.64%','Python','8.095%');";


            st.executeUpdate(sql);

            ResultSet rs = st.executeQuery("Select * FROM LABORATOR ;");
            List<ResultSet> a =new ArrayList<ResultSet>();
            while ( rs.next() ) {
                a.add(rs) ;
                int m_2019 = rs.getInt("May_2019");
                int m_2018 = rs.getInt("May_2018");
                float change = rs.getFloat("Change");
                String name = rs.getString("Programming_Language");
                float rating = rs.getFloat("Ratings");
                System.out.println(m_2019+" "+ m_2018+" "+ change+" "+ name+" "+rating+" ");
            }
            System.out.println();

            System.out.println("Limbajele in crestere");
            rs = st.executeQuery( "SELECT * FROM LABORATOR WHERE Change LIKE '%+%';" );
            while ( rs.next() ) {
                int m_2019 = rs.getInt("May_2019");
                int m_2018 = rs.getInt("May_2018");
                float change = rs.getFloat("Change");
                String name = rs.getString("Programming_Language");
                float rating = rs.getFloat("Ratings");
                //if(change > 0f )

                    System.out.println(m_2019+" "+ m_2018+" "+ change+" "+ name+" "+rating+" ");
            }
            System.out.println();

            System.out.println("Limbajele cu ratings descrescator");
            rs = st.executeQuery("Select * FROM LABORATOR ORDER BY CAST(Ratings AS DOUBLE ) DESC");
            while ( rs.next() ) {
                int m_2019 = rs.getInt("May_2019");
                int m_2018 = rs.getInt("May_2018");
                float change = rs.getFloat("Change");
                String name = rs.getString("Programming_Language");
                float rating = rs.getFloat("Ratings");

                    System.out.println(m_2019+" "+ m_2018+" "+ change+" "+ name+" "+rating+" ");
            }
            System.out.println();

            Scanner cit = new Scanner(System.in) ;

            int iesire = 0 ;

            while(iesire != 1){
                System.out.print("Operatatia dorita: \n1 - Adaugare inregistrare \n 2 - Stergere inregistrare \n 3 - Modificare inregistrare \n 4 - Ordonare descrescatoare dupa rata \n 5 - Afisare continut tabela \n 0 - Terminare Operatie" );
                iesire=cit.nextInt() ;

                switch(iesire){
                    case 1 : sql=" INSERT  INTO LABORATOR (May_2019, May_2018, Change, Programming_Language, Ratings)"+ "VALUES(5, 5, '+2.66%','CSS','18.095%');";
                             st.executeUpdate(sql);
                             break ;
                    case 2 : sql= "DELETE * FROM LABORATOR WHERE Programming_Language='C++' " ;
                             st.executeUpdate(sql);
                             break ;
                    case 3 : sql= "UPDATE LABORATOR SET Programming_Language= 'C++' WHERE  May_2018= 2";
                             st.executeUpdate(sql);
                             break;
                    case 4 : sql = "UPDATE LABORATOR ORDER BY CAST(Ratings AS DOUBLE ) DESC ";
                            st.executeUpdate(sql);
                          break;
                    case 5:   ResultSet res = st.executeQuery("Select * FROM LABORATOR ;");
                                while ( res.next() ) {
                                    a.add(res) ;
                                    int m_2019 = res.getInt("May_2019");
                                    int m_2018 = res.getInt("May_2018");
                                    float change = res.getFloat("Change");
                                    String name = res.getString("Programming_Language");
                                    float rating = res.getFloat("Ratings");
                                    System.out.print(m_2019+" "+ m_2018+" "+ change+" "+ name+" "+rating+" ");
                                }
                                System.out.println();
                                rs.close() ;
                                break;
                    case 0 : iesire =0  ;
                            break ;
                }
            }
            rs.close() ;
            st.close();
            c.commit() ;
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}
