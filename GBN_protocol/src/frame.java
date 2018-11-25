class Frame {

    private char frameData;  //Carried Data By The Frame
    private int seqNo;

    Frame(){
    	
    }
    
    Frame( int seqNo, char frameData){
        this.seqNo = seqNo;
        this.frameData = frameData;
    }

    public char getFrameData() {
        return frameData;
    }

    public void setFrameData(char frameData) {
        this.frameData = frameData;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo){

        this.seqNo = seqNo;

    }

}
