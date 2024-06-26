package com.kindsonthegenius.fleetmsv2.es.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@Slf4j
public class DownloadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://upload//";

    private static final String DIRECTORY = "C://upload//";
    private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";

    //Allows to have access to files
    @Autowired
    ServletContext context;

    @GetMapping("/downloadfile")
    public String index() {
        System.out.println("downloadfile called");
        return "downloadFile";
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downlaodFile1(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = getMediaTypeForFileName(context, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Content-Length
                .contentLength(file.length())//
                .body(resource);
    }

    private MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {


        //   application/pdf
        //   application/xml
        //   image/fig, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (
                Exception e) {

            return MediaType.APPLICATION_OCTET_STREAM;
        }


    }
}
