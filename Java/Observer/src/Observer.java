import java.util.Vector;
public class Observer
{
    protected MessageSet dataMessageSet;
    public void ShowText(){
        Vector<String> message = dataMessageSet.messages;
        for (String i : message){
            System.out.println(i);
        }
    }

    public void Catalog(MessageSet data)
    {
        dataMessageSet = data;
        dataMessageSet.observers.add(this);
    }
}
