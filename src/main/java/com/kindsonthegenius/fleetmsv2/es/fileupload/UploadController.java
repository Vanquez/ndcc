package com.kindsonthegenius.fleetmsv2.es.fileupload;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Slf4j
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://upload//";

    @GetMapping("/es/fileupload")
    public String index(Model model) {
        System.out.println("i am called");
        File folder = new File("C://upload//");
        File[] files = folder.listFiles();
        model.addAttribute("documents", files);

        return "/es/esupload";
    }

    @PostMapping("/es/fileupload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes){

    if (file.isEmpty()) {
        redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        return "redirect:/es/fileupload";
    }

    try{

       // Get the file and save it somewhere
       byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");
    } catch (IOException e){
        e.printStackTrace();
    }

        return "redirect:/es/fileupload";
    }

    public String getDocuments(Model model) {
        // Assuming documents are stored in a folder named "documents"
        File folder = new File("C://upload//");
        File[] files = folder.listFiles();
        model.addAttribute("documents", files);
        return "documentList"; // Thymeleaf template name
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
               return "uploadStatus";
    }

}
