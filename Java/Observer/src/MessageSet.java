import java.util.Vector;

public class MessageSet {

    public Vector <String> messages = new Vector<>();
    public Vector <Observer> observers = new Vector<>();
    public void AddMessage (String mesaj){
        System.out.println("Textul introdus este: " + mesaj);
        messages.add(mesaj);
    }
    public void Print(){
        for (Observer obs : observers){
            obs.ShowText();
        }
    }
}
