package hungergames.services;

/**
 * Created with IntelliJ IDEA.
 * User: CSD
 * Date: 22.07.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class Logger {

    String log;

    public Logger()
    {
        this.log = "";
    }

    public void addToLog(String msg)
    {
       log += msg + "\n";
    }

    public String getLog()
    {
        return log;
    }

    public void clear() {
        this.log = "";
    }
}
