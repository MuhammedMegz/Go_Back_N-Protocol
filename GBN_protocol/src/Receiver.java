

public class Receiver {

    int windowSize;
    DataPath dataPath;
    private final int[] expectedseqNum;
    private int currentFrameseqNum;

     Receiver(int windowSize, DataPath dataPath){
        this.windowSize = windowSize;
        this.dataPath = dataPath;
        expectedseqNum = new int[windowSize+1];
        for (int i = 0 ; i < windowSize+1 ; i++)
            expectedseqNum[i] = i;
        currentFrameseqNum = 0;
    }


    public void setCurrentFrameSeqNum(int currentFrameSeqNum){
         this.currentFrameseqNum = currentFrameSeqNum;
    }

    public void receiveFrame(){

         //Check the received ID is the expected one
        if(dataPath.receive().getSeqNo() == expectedseqNum[currentFrameseqNum]) {
            dataPath.sendAck(currentFrameseqNum);
            setCurrentFrameSeqNum(currentFrameseqNum++);
            System.out.println("Frame Received Successfully");
        }else {
            //wait until time out
            System.out.println("Received frame is incorrect");
        }
    }

}

