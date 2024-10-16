namespace Speed
{
    public interface IMeasure
    {
        void BeginTest();

        double EndTest(); // returneaza diferenta in milisecunde
    }
}