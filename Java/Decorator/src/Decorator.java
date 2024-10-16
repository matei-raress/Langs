abstract public class Decorator implements Interfata {
    protected String name;
    protected String content;
    protected int level = 0;

    public int GetLevel()
    {
        return level;
    }

    public void SetLevel(int level)
    {
        this.level = level;
    }

}
