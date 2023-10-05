package org.danil.logger4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogError implements Log{
    Date date;
    String type;
    String message;

    static final SimpleDateFormat dateFormat = new SimpleDateFormat(Config.getDefaultDateFormatLog());

    public LogError(String message){
        this.message=message;
        date=new Date();
        type="INFO";
    }
    @Override
    public String getCompleteMessage() {
        return dateFormat.format(date)+"|"+type+"|"+message;
    }
}
