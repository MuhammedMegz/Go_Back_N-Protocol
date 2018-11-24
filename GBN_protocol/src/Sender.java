import java.util.TimerTask;

public class Sender {

    private String data;
    private int windowBegin;
    private int windowEnd;
    private int windowSize;
    private myTimer[] timers;
    private DataPath dataPath;
    private Frame[] frames;
    private TimerTask[] tasks;
    public Sender(int windowSize, DataPath dataPath){
        this.windowSize = windowSize;
        windowBegin = 0;
        windowEnd = windowSize-1;
        timers = new myTimer[windowSize];
        this.dataPath = dataPath;
        frames = new Frame[windowSize];
        tasks = new TimerTask[windowSize];
    }

    public Sender(int windowSize, String data, DataPath dataPath){
        this.windowSize = windowSize;
        windowBegin = 0;
        windowEnd = windowSize-1;
        timers = new myTimer[windowSize];
        this.dataPath = dataPath;
        setData(data);
        frames = new Frame[windowSize];
        tasks = new TimerTask[windowSize];
    }

    public void setData(String data){

        this.data = data;

    }

    public void startSending(){



    }

    private void sendWindow(){

        for (int i=0; i<windowSize; i++){
            frames[i].setFrameData(data.charAt(i));
            frames[i].setFrameID(i);
            dataPath.send(frames[i]);
            tasks[i] = new TimerTask() {
                @Override
                public void run() {

                }
            };
            timers[i].start(1000, tasks[i]);
        }

    }



}
