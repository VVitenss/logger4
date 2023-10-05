package org.danil.logger4;

public class LogWithUserData extends LogDecorator{
    private final String userData;
    public LogWithUserData(Log log,String userData) {
        super(log);
        this.userData=userData;
    }


    @Override
    public String getCompleteMessage(){
        return super.getCompleteMessage()+"|UserData: "+userData;
    }
}
