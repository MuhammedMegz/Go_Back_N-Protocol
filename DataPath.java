import java.util.Queue;

public class DataPath {

    private Queue<Frame> dataBuffer;


    public void addToDataBuffer(Frame frame){
        dataBuffer.add(frame);
    }

    public Frame getFromDataBuffer(){
        return dataBuffer.remove();
    }

    public Queue<Frame> getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(Queue<Frame> dataBuffer) {
        this.dataBuffer = dataBuffer;
    }
}
