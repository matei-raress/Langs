abstract public class Tree {
    protected String name;
    protected String content;
    protected int level = 0;

    public int GetLevel() {
        return level;
    }

    public void SetLevel(int level) {
        this.level = level;
    }

    abstract public void Afisare();
}
