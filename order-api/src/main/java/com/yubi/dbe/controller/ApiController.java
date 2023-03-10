package com.yubi.dbe.controller;

import com.yubi.dbe.service.impl.ApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class ApiController{

    @Autowired
    ApiServiceImpl apiService;


    @RequestMapping(value = {"/pushFile"}, method = RequestMethod.POST)
    public ResponseEntity<?> pushfile(@RequestParam(name = "loanAmount", required = false) String loanAmount,
                                            HttpServletRequest request
    ) {
        try {
//            String url="https://ej2services.syncfusion.com/production/web-services/api/spreadsheet/save";
//            RestTemplate restTemplate= new RestTemplate();
//            restTemplate.postForLocation(url,request);
////                return ResponseEntity.status(HttpStatus.OK).body(loanAmount);
////            HttpHeaders headers = request;
//            HttpHeaders headers=new HttpHeaders();
//            Map<String, String[]> map = request.getParameterMap();
//            HttpEntity<Map<String, String[]>> entity = new HttpEntity<>(map, headers);
//
//            String response =
//                    restTemplate.exchange(url,
//                            HttpMethod.POST,
//                            entity,
//                            String.class).getBody();
            return  ResponseEntity.ok().body("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }

    @RequestMapping(value = {"/values"}, method = RequestMethod.GET)
    public ResponseEntity<?> pushValues(@RequestParam(name = "loanAmount", required = true) String loanAmount,
                                        @RequestParam(name = "roi", required = true) String roi,
                                        @RequestParam(name = "tenure", required = true) String tenure,
                                        @RequestParam(name = "fileId", required = true) String fileId,
                                        HttpServletRequest request
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(apiService.getFormulatedValue(loanAmount,roi,tenure,fileId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }

    @RequestMapping(path = "/getSampleFile", method = RequestMethod.GET)
    public ResponseEntity<Resource> getSampleFile() throws IOException {
      return ApiServiceImpl.getSampleFile();
    }

    @RequestMapping(path = "/file", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadImages(@RequestPart("file") final MultipartFile file) throws IOException {
        try {
            return apiService.uploadFile(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }


}