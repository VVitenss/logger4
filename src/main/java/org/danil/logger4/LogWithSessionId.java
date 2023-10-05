package org.danil.logger4;

public class LogWithSessionId extends LogDecorator{
    private final String sessionId;
    public LogWithSessionId(Log log,String sessionId) {
        super(log);
        this.sessionId=sessionId;
    }

    @Override
    public String getCompleteMessage(){
        return super.getCompleteMessage()+"|Session: "+sessionId;
    }


}
