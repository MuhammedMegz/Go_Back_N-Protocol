import java.util.Timer;
import java.util.TimerTask;

public class TaskTimer {


    private Timer tTimer;

    TaskTimer(){
        tTimer = new Timer();
    }

    public void start(TimerTask tTask, long delay){
        tTimer.schedule(tTask, delay);
    }
    
    public void reset() {
    	tTimer.cancel();
    }


}
