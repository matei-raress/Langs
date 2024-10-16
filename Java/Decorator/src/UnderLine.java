public class UnderLine extends Decorator
{
    public UnderLine(String name, String _content)
    {
        this.name = name;
        this.content = _content;

    }

    public void Afisare()
    {

        for(int i = 0; i < level; i++)
        {
            System.out.print("   ");
        }
        System.out.print("<" + name + "> ");
        String txt = content;
        String utxt = String.join("\u0332",txt.split("",-1));
        System.out.print("Text scris normal | " + utxt);
        System.out.println("</" + name + ">");
    }
}