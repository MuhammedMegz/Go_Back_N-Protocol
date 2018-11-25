import java.util.TimerTask;
import java.net.*;
import java.io.*;

public class Sender {

    private String data;
    private int currentPacketNo;
    private int currentSeqNo;
    private int framesToSend;
    private int windowSize;
    private boolean timeoutOcurred;
    private int timeoutNo;
    private Socket socket;
    private TaskTimer[] timers;
    private Frame[] frames;
    private TimerTask[] tasks;
    
    
    public Sender(int windowSize, Socket socket){
        this.windowSize = windowSize;
        currentPacketNo = 0;
        currentSeqNo = 0;
        framesToSend = windowSize;
        timeoutOcurred = false;
        this.socket = socket;
        this.frames = new Frame[windowSize];
        this.tasks = new TimerTask[windowSize];
        timers = new TaskTimer[windowSize];
    }

    public Sender(int windowSize, String data, Socket socket){
        this.windowSize = windowSize;
        currentPacketNo = 0;
        currentSeqNo = 0;
        framesToSend = windowSize;
        timeoutOcurred = false;
        this.socket = socket;
        setData(data);
        this.frames = new Frame[windowSize];
        this.tasks = new TimerTask[windowSize];
        timers = new TaskTimer[windowSize];
    }

    public void setData(String data){

        this.data = data;

    }

    public void startSending(){

    	int dataLength = data.length();
    	while (currentPacketNo < dataLength) {
    		
    		sendFrame(currentPacketNo, currentSeqNo);
    		System.out.println("Sender: Packet " + currentPacketNo + " with sequence number " + currentSeqNo
    				+ " has been sent");
    		currentPacketNo++;
    		currentSeqNo = (currentSeqNo+1) % (windowSize+1);
    		framesToSend--;
    		while(framesToSend == 0) {//wait till any Ack is received or timeout happens
    			
    			if(timeoutOcurred) {
    				
    				System.out.println("Sender: Timer " + timeoutNo + " timeout!");
    				System.out.println("Sender: Resending the frame.....");
    				currentPacketNo -= windowSize; 
    				currentSeqNo -= windowSize % (windowSize + 1);
    				framesToSend = windowSize;
    				timeoutOcurred = false;
    				for(int i=0; i< windowSize; i++) {//reset all timers
    					timers[i].reset();
    				}
    				
    			}else if(dataPath.hasAck()) {
    				
    				System.out.println("Sender: Ack" + dataPath.receiveAck() + "has been received.");
    				framesToSend++;
    				
    			}
    			
    		}
    		
    	}
    	
    	System.out.println("Sender: All data have been sent succesfully.");

    }

    private void sendFrame(int packetNo, int seqNo){

    	if(frames[packetNo % windowSize] == null) {
    		frames[packetNo % windowSize] = new Frame();
    	}
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
        if(timers[packetNo % windowSize] == null) {
        	timers[packetNo % windowSize] = new TaskTimer();
        }
        timers[packetNo % windowSize].start(tasks[packetNo % windowSize], 100);

    }



}
