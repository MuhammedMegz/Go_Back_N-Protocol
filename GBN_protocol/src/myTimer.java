import java.util.Timer;
import java.util.TimerTask;

public class myTimer {


    private Timer timer;
    private int ID;

    public void myTimer(int ID){

        this.ID = ID;

    }

    public void start(long duration, TimerTask task){
        timer.cancel();
        timer.schedule(task, duration);
    }


    public void stop(){
        timer.cancel();
    }




}
