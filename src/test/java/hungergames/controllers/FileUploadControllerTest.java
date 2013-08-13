package hungergames.controllers;

import hungergames.modelattributes.FileUploadForm;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: CSD
 * Date: 05.08.13
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadControllerTest {

    @Ignore
    public void testDisplayForm() throws Exception {

        FileUploadController fuc = new FileUploadController();

        MockMultipartFile mockMpf = new MockMultipartFile("test_file.txt", new byte[1]);
        FileUploadForm fUf = new FileUploadForm();
        fUf.setFiles(new ArrayList<MultipartFile>(Arrays.asList(new MultipartFile[]{mockMpf})));

        //String view = fuc.save(fUf,);

        //assertEquals("ViewName should be 'add_jar_form'", "add_jar_form", view);
       //
    }

    @Test
    public void testSave() throws Exception {

    }
}
