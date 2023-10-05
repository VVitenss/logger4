package org.danil.logger4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWarning implements Log{
    Date date;
    String type;
    String message;

    static final SimpleDateFormat dateFormat = new SimpleDateFormat(Config.getDefaultDateFormatLog());

    public LogWarning(String message){
        this.message=message;
        date=new Date();
        type="WARNING";
    }
    @Override
    public String getCompleteMessage() {

        return dateFormat.format(date)+"|"+type+"|"+message;
    }
}
