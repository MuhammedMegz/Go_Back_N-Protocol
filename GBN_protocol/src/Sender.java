import java.util.TimerTask;

public class Sender {

    private String data;
    private int currentPacketNo;
    private int currentSeqNo;
    private int framesToSend;
    private int windowSize;
    private boolean timeoutOcurred;
    private int timeoutNo;
    private DataPath dataPath;
    private TaskTimer[] timers;
    private Frame[] frames;
    private TimerTask[] tasks;
    
    
    public Sender(int windowSize, DataPath dataPath){
        this.windowSize = windowSize;
        currentPacketNo = 0;
        currentSeqNo = 0;
        framesToSend = windowSize;
        timeoutOcurred = false;
        timers = new TaskTimer[windowSize];
        this.dataPath = dataPath;
        frames = new Frame[windowSize];
        tasks = new TimerTask[windowSize+1];
    }

    public Sender(int windowSize, String data, DataPath dataPath){
        this.windowSize = windowSize;
        currentPacketNo = 0;
        currentSeqNo = 0;
        framesToSend = windowSize;
        timeoutOcurred = false;
        timers = new TaskTimer[windowSize];
        this.dataPath = dataPath;
        setData(data);
        frames = new Frame[windowSize];
        tasks = new TimerTask[windowSize];
    }

    public void setData(String data){

        this.data = data;

    }

    public void startSending(){

    	int dataLength = data.length();
    	while (currentPacketNo < dataLength) {
    		
    		sendFrame(currentPacketNo, currentSeqNo);
    		currentPacketNo++;
    		currentSeqNo = (currentSeqNo+1) % (windowSize+1);
    		framesToSend--;
    		while(framesToSend == 0) {//wait till any Ack is received or timeout happens
    			
    			if(timeoutOcurred) {
    				
    				currentPacketNo -= windowSize; 
    				currentSeqNo -= windowSize % (windowSize + 1);
    				framesToSend = windowSize;
    				timeoutOcurred = false;
    				
    			}else if(dataPath.hasAck()) {
    				
    				dataPath.receiveAck();
    				framesToSend++;
    				
    			}
    			
    		}
    		
    	}

    }

    private void sendFrame(int packetNo, int seqNo){

        frames[packetNo % windowSize].setFrameData(data.charAt(packetNo));
        frames[packetNo % windowSize].setSeqNo(seqNo);
        dataPath.send(frames[packetNo % windowSize]);
        tasks[packetNo % windowSize] = new TimerTask() {
            @Override
            public void run() {
            	timeoutOcurred = true;
            	timeoutNo = packetNo % windowSize;
            }
        };
        timers[packetNo % windowSize].start(tasks[packetNo % windowSize], 1000);

    }



}
