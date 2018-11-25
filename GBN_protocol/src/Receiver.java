import java.util.TimerTask;

public class Receiver {

    private String data;
    private int windowBegin;
    private int windowEnd;
    private int windowSize;
    private TaskTimer[] timers;
    private DataPath dataPath;
    private Frame[] receivedFrames;
    private TimerTask[] tasks;


    public void Receiver(int windowSize, DataPath dataPath){
        this.windowSize = windowSize;
        this.dataPath = dataPath;
        this.receivedFrames = new Frame[windowSize];
    }


    public void receiveWindow(){

        //Check The matching of sender and receiver size
        if(dataPath.getDataBuffer().size() != windowSize)
            System.out.println("Sent window size is not the same as Receiver window size");

        for (int i = 0 ; i < windowSize ; i++){
            //get frames from the sender
            receivedFrames[i] = dataPath.receive();

            //check if the received frame is not the expected one
            if(receivedFrames[i].getFrameID() != i) {
                //timeout the receiver to send the missed frame again
                System.out.println("Timeout, Frame No. [" + i + "] is misssing");
            }

        }



    }



}
