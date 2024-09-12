package com.I2R.DemoServer.Controller;

import com.I2R.DemoServer.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;

@RestController
@RequestMapping("/api/xml")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/uploadfile")
    public ResponseEntity<String> uploadXmlFile(@RequestParam("file") MultipartFile file) {
        try {
            // Save the XML file to a temporary location
            File tempFile = File.createTempFile("uploaded", ".xml");
            file.transferTo(tempFile);

            orderService.saveXmlDataAsKeyValue(tempFile.getAbsolutePath());
            tempFile.delete();
            return ResponseEntity.ok("XML file uploaded and data stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload XML file.");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadXmlFile(@RequestBody String xmlData) {
        File tempFile = null;
        try {
            // Save the XML data to a temporary file
            tempFile = File.createTempFile("uploaded", ".xml");
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(xmlData);
            }

            // Process the XML data
            orderService.saveXmlDataAsKeyValue(tempFile.getAbsolutePath());

            return ResponseEntity.ok("XML data uploaded and stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload XML data.");
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
