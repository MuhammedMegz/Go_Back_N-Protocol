Public class Frame {

    private int frameSize;  //No of Bits Per Frame
    private char[] frameData;  //Carried Data By The Frame


    public void Frame(int frameSize){
        frameData = new char[frameSize];
    }
    public int getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(int frameSize) {
        this.frameSize = frameSize;
    }

    public int getFrameData() {
        return frameData;
    }

    public void setFrameData(int frameData) {
        this.frameData = frameData;
    }
}
