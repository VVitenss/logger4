package org.danil.logger4;

abstract class LogDecorator  implements  Log{
    protected  Log log;

    public  LogDecorator(Log log){
        this.log=log;//Принимаем объект который будет в основе декоратора
    }
    @Override
    public  String getCompleteMessage() {//Переопределяем метод чтобы работал в декораторе
        return log.getCompleteMessage();
    }

}
