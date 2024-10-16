package Exeptions;

import java.io.FileNotFoundException;
import java.io.IOException;

/*! \class public class ExceptionManager
    \brief Clasa de tratare a exceptiilor.

    Aceasta afiseaza cate un mesaj pentru fiecare tip de exceptie si locatia din cod unde s-a depistat.
 */
public class ExceptionManager {

    /*! \fn public void SqlExeption( java.sql.SQLException a)
        \brief Trateaza exceptia de tip SQLException.
        \param a Referinta la exceptie.
     */
    public void SqlExeption(java.sql.SQLException a) {
        System.out.println("Eroare in baza de date : " + a.getMessage() + "  Locatia   :" + a.getLocalizedMessage() + "\n");
        a.printStackTrace();
    }

    /*! \fn public void FileExeption( FileNotFoundException a)
        \brief Trateaza exceptia de tip SQLException.
        \param a Referinta la exceptie.
     */
    public void FileExeption(FileNotFoundException a) {
        System.out.println("Un fisier nu a for gasit : " + a.getMessage() + "  Locatia   :" + a.getLocalizedMessage() + "\n");
        a.printStackTrace();
    }

    /*! \fn public void StringExeption(IOException a)
        \brief Trateaza exceptia de tip IOException.
        \param a Referinta la exceptie.
     */
    public void StringExeption(IOException a) {
        System.out.println("Exceptie in prelucrarea stringurilor : " + a.getMessage() + "  Locatia   :" + a.getLocalizedMessage() + "\n");
        a.printStackTrace();
    }

    /*! \fn  public void ClassConnectionException(ClassNotFoundException a)
        \brief Trateaza exceptia de tip ClassNotFoundException.
        \param a Referinta la exceptie.
     */
    public void ClassConnectionException(ClassNotFoundException a) {
        System.out.println("Nu s-a gasit aceasta clasa/conectare baze de date: " + a.getMessage() + "  Locatia   :" + a.getLocalizedMessage() + "\n");
        a.printStackTrace();
    }

    /*! \fn public void IOException(IOException a){
        \brief Trateaza exceptia de tip IOException.
        \param a Referinta la exceptie
     */
    public void IOException(IOException a) {
        System.out.println("Exceptie la incarcare poze/subpoze : " + a.getMessage() + "  Locatia   :" + a.getLocalizedMessage() + "\n");
        a.printStackTrace();
    }

    /*! \fn public void UnknownException(Exception e)
        \brief Trateaza exceptia de tip Exception
        \param a Referinta la exceptie
     */
    public void UnknownException(Exception e) {
        ClassNotFoundException test = new ClassNotFoundException();
        if (e.getClass().equals(test.getClass())) {     //WORKS
            System.out.println(" E exceptie cu clasa");
        }
    }
}
