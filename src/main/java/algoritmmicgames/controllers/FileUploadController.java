package algoritmmicgames.controllers;

import java.util.ArrayList;
import java.util.List;

import algoritmmicgames.modelattributes.FileUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/file_upload", method = RequestMethod.GET)
    public ModelAndView displayForm() {

        return new ModelAndView("file_upload");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model map) {

        if(!uploadForm.CheckFiles()) {
            map.addAttribute("resultMessage", uploadForm.getErrorMsg());
            return "file_upload";
        }

        if(!uploadForm.saveFilesToDisk(System.getProperty("user.dir") + "\\webapp\\HungerGamesWebService\\HungerGamesBots")) {
            map.addAttribute("resultMessage", uploadForm.getErrorMsg());
            return "file_upload";
        }

        List<MultipartFile> files = uploadForm.getFiles();

        List<String> fileNames = new ArrayList<String>();

        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
            }
        }

        map.addAttribute("resultMessage", "File was successfuly uploaded" + uploadForm.getErrorMsg());
        map.addAttribute("files", fileNames);

        return "file_upload";

    }
}
