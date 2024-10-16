import java.util.concurrent.TimeUnit;

public class Download {

    public static int totalTime = 0;
    private static Download instance = null;
    File file;

    private Download(File file) throws InterruptedException {
        this.file = file;

        int time = (int) (file.getSize()/2);
        totalTime+=time;

        System.out.println(""+ file.getname() + "is downloading");

        while(time != 0)
        {
            System.out.print("#");
            TimeUnit.SECONDS.sleep(1);
            time --;
        }
        System.out.println("\n "+ file.getname() + "is downloaded");
    }

    public static Download GetInstance(File fisier) throws InterruptedException {
        if (instance == null) {
            instance = new Download(fisier);
        }
        return instance;
    }

    public static void Reset() {
        instance = null;
    }

}
