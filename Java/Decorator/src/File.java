public class File extends Decorator
{
    public File(String name, String _content)
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
        System.out.print(content+ " ");
        System.out.println("</" + name + ">");
    }
}
