public class window {


    private int windowSize;
    private Frame[] frames;

    public void window(int windowSize){
        frames = new Frame[windowSize];
    }

    public long getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public Frame[] getFrames() {
        return frames;
    }

    public void setFrames(Frame[] frames) {
        this.frames = frames;
    }

    public Frame getFrameAt(int index){
        if(index < windowSize){
            return frames[index];
        }else
            System.out.println("Out of Range Frame Index.");
        return null;
    }

    public void printWindowFrames(){
        for (int i = 0 ; i < windowSize ; i++)
            System.out.println("Frame No. " + 0 + " : " + frames[i]);
    }
}
