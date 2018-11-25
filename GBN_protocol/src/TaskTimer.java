import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TaskTimer {


    private Timer tTimer;

    TaskTimer(){
        tTimer = new Timer();
    }

    public void start(TimerTask tTask, long duration, long delay){
        tTimer.schedule(tTask, duration, delay);
    }


}
