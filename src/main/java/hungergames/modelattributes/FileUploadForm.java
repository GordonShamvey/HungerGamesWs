package hungergames.modelattributes;

import java.io.*;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

    private List<MultipartFile> files;
    private String errorMsg = "";

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<MultipartFile> getFiles() {
        return files;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getErrorMsg() {
        return errorMsg;  //To change body of created methods use File | Settings | File Templates.
    }

    public boolean CheckFiles() {

        return true;  //To change body of created methods use File | Settings | File Templates.
    }

    public boolean saveFilesToDisk(String path) {

        for (MultipartFile mpf : files) {
            try {

                errorMsg += "\nFile saved to: " + path;
                File dest = new File(path, mpf.getOriginalFilename());
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                mpf.transferTo(dest);
                errorMsg += "File name: " + dest;

            } catch (IllegalStateException e) {
                errorMsg += "File uploaded failed:" + e.getMessage();
                return false;
            } catch (IOException e) {
                errorMsg += "File uploaded failed:" + e.getMessage();
                return false;
            }
        }

        return true;
    }

    public boolean SaveFiles(String path) {

        boolean res = true;

        for (MultipartFile mpf : files) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path + mpf.getOriginalFilename()));
                InputStream inputStream = new ByteArrayInputStream(mpf.getBytes());
                int token = -1;

                while ((token = inputStream.read()) != -1) {
                    bufferedOutputStream.write(token);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                inputStream.close();

            } catch (FileNotFoundException e) {
                errorMsg += e.getMessage();
                res = false;  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                errorMsg += e.getMessage();
                res = false;
                  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return res;
    }

    //Getter and setter methods
}