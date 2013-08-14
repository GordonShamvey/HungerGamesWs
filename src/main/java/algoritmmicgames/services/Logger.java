package algoritmmicgames.services;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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

    public void saveLog(String path) throws IOException {
        FileUtils.writeStringToFile(new File(path + "\\log.txt"), log);
    }
}
