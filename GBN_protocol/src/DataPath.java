import java.util.LinkedList;
import java.util.Queue;

public class DataPath {

    private Queue<Frame> dataBuffer;
    private Queue<Integer> ackBuffer;



    DataPath(){
        dataBuffer = new LinkedList<>();
        ackBuffer = new LinkedList<>();
    }

    public boolean hasData(){
        if(dataBuffer.size() == 0)
            return false;
        return true;
    }

    public void send(Frame frame){
        dataBuffer.add(frame);
    }

    public Frame receive(){
        return dataBuffer.remove();
    }

    public void sendAck(int seqNum){

        ackBuffer.add(seqNum);

    }

    public int receiveAck(){

        return ackBuffer.remove();

    }

    public boolean hasAck(){

        if(ackBuffer.size() == 0)
            return false;
        return true;

    }

    public Queue<Frame> getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(Queue<Frame> dataBuffer) {
        this.dataBuffer = dataBuffer;
    }
}
