package org.danil.logger4;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.concurrent.Future;

public class LogToFile implements LoggerStrategy{
    private String pathDir=Config.getDefautlPath();//"logs/";//Путь к файлам по умолчанию
    private String pathFile=null;//Динамическое имя файла
    private double maxSizeMB=Config.getDefaultFileSizeMb();//200;//Размер файла по умолчанию для ротации
    @Override
    public void createLog(Log log) {

        getFileName();//Создаём сам файл и получаем его имя

        Path pathFile = Paths.get(this.pathDir+this.pathFile);//Создаём путь к файлу

        AsynchronousFileChannel fileChannel =//Открываем канал для асинхронной записи
                null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);//Создаём буфер

        File file = new File(this.pathDir+this.pathFile);// Берём сам файл

        try {
            fileChannel = AsynchronousFileChannel.open(pathFile, StandardOpenOption.WRITE);//Открываем канал для записи
        } catch (IOException e) {
            Logger logger=Logger.getInstance();//В случае ошибки её в и пишем в файл
            logger.logError(e.getMessage());
            throw new RuntimeException(e);
        }
        if (fileChannel != null) {
            long position_write = file.length();//Пишем в файл начиная с последней позиции
            String textToWrite="\n"+log.getCompleteMessage();//Это само сообщение добавляем к нему перенос по строкам
            buffer.put(textToWrite.getBytes());//Конвертируем в буфер
            buffer.flip();
            Future operation_write = fileChannel.write(buffer, position_write);//Отправляем на запись
            buffer.clear();
            //while (!operation_write.isDone()) ;//Не уверен что нужно ждать
//            System.out.println("Write done "+System.currentTimeMillis());
        }
    }


    void getFileName(){

        String pathFile=this.pathFile;
        //Для начала проверим знаем ли мы куда писать

        Date date=new Date();
        SimpleDateFormat DateFormat = new SimpleDateFormat(Config.getDefaultDateFormatFile());
        if(pathFile==null){
            pathFile=DateFormat.format(date)+".txt";
        }else{
            //Иначе проверяем совпадает ли наш файл с датой
            String pure_name;
            if(pathFile.contains("_")){
                pure_name=pathFile.split("_")[0];
            }else{
                pure_name=pathFile.split(".txt")[0];
            }
            if(!pure_name.equals(DateFormat.format(date))){
                this.pathFile=null;
                getFileName();
                return;
            }
        }

        //Теперь если файл знаем и по дате всё верно, проверяем размер файла
        File directory = new File(this.pathDir);
        if (! directory.exists()){
            directory.mkdir();
        }

        File file = new File(this.pathDir + "/" + pathFile);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        double file_size=(double) file.length()/(1024*1024);
        if(file_size>maxSizeMB){//больше допущенного
            int file_number=0;
            if(pathFile.contains("_")){//если нумерованный
                String num_file=pathFile.substring(pathFile.indexOf("_")+1,pathFile.indexOf("."));
                file_number= Integer.parseInt(num_file);
            }
            file_number++;//Увеличиваем на 1

            pathFile=DateFormat.format(date)+"_"+file_number+".txt";

            this.pathFile=pathFile;
            getFileName();
        }else{
            this.pathFile=pathFile;
        }

    }

    String path_file(){
        Date date=new Date();
        SimpleDateFormat DateFormat
                = new SimpleDateFormat(Config.getDefaultDateFormatFile());
        String file_name=DateFormat.format(date)+".txt";
        Path pathFile = Paths.get(this.pathDir+file_name);
        File file=new File(this.pathDir+file_name);
        if(file.exists()){
            Double file_size=(double) file.length()/(1024*1024);
            if(file_size>maxSizeMB){
            }
        }
        return null;
    }
    void setPath(String path){
        this.pathDir=path;
    }

    void  setPath(Path path){

    }
}
