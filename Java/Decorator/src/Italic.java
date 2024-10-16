public class Italic extends Decorator
{
    public Italic(String name, String _content)
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
        System.out.print("Text scris normal | "+"\033[3m" + content + "\033[0m ");
        System.out.println("</" + name + ">");
    }
}