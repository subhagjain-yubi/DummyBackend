package com.yubi.dbe.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class ApiServiceImpl {

    RestTemplate restTemplate= new RestTemplate();
    Gson gson= new Gson();

    public static ResponseEntity<Resource> getSampleFile() {

        try {
            Path path = Paths.get("/Users/subhag.jain/Desktop/WORKSPACE/dummyapi/DummyBackend/order-service/src/main/resources/Sample.xlsx");
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
           return ResponseEntity.notFound().build();
        }


    }

    public String getAccessToken()
    {
        String url="https://login.microsoftonline.com/6adb12a1-8428-45ce-80ac-c2719276c974/oauth2/v2.0/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name","feature");
        map.add("color","#5843AD");
        map.add("client_id"," 16069d56-b707-4f2b-9e8f-4935f6c459e1");
        map.add("scope","Files.ReadWrite");
        map.add("code","0.AXAAoRLbaiiEzkWArMJxknbJdFadBhYHtytPno9JNfbEWeFwAFk.AgABAAIAAAD--DLA3VO7QrddgJg7WevrAgDs_wUA9P-WwIqFtZD6vPPYwqAiB_oAD8zSrYgy298uxB1jK8wiL5vw6YSx7gUJipDxicLuiaoW-2Mbmyw8iJGE5d8tqTlSir967ycLLzh7uMJ-ui1t4lGTIhHLWYOUtY4Y_9r8tRhADSh4MrzenPzHPDUc_RMRfbrQ8EiJ-ClvWnS3OMZq-cns4wKehzLY0xsLCPtq5uBuLeSVPCcCd4d5VPEhaohEDT2RY5oePjVf-rVfZk3okLJ57Q-6Bp1C8CG25mhaxoa7Y8WJfO5Og106Kd48GE4bP3DZP3w-2JYUPbDoqdVgl4Wrj3QWeHknwTDXUh6K9kVXMy0UcxPv2WX1Xr4KoZ2YDGvQzkOLLvhwn5752y9kNSn1PprU38OKb3s9LWM_ibNiFgHgBCtvlm019uM_TCBMiIW1aqhTaTB3fxw0NiOR-XYybJbZFD-3T99xQAF6GqPZNkK92VYnUI_lGmoIDgb5d432kxC7eSRYLX3lSPmzQxZeIV-dpwL-CFhXUf8Yu18BTc3zUIQpldB_LmX81szHhd3Cb7LKUsWUvU85GRmwDQ1hUouA38qRA8j2-3doQmcSmhgrg2LX9F_-1B42XKPO6usyyuXk2dWj6rNbz2x3ZPeahsDZM10g3Rp1ljowEFDtDLkBKIchzdebWGZz8sMf8ADiU5yKvtRjf6WH0RHcuQC9gKXpM1n05Tv1B__gPpa813yuniwDb4it2w\n");
        map.add("grant_type","refresh_token");
        map.add("client_secret","DQM8Q~H6tV8WqDt7vg.p_x4HkYT7JJIaFoFv7bz8");
        map.add("refresh_token","0.AXAAoRLbaiiEzkWArMJxknbJdFadBhYHtytPno9JNfbEWeFwAFk.AgABAAEAAAD--DLA3VO7QrddgJg7WevrAgDs_wUA9P_PyaeT3D__bg75K5ShL_H2GS0rGPi8wjMeiPSiE1atjZrJ3RuiOV-eA4Dd9E5qZZDmwrRMh01hBz2xBI_x9aFT70i5T48XkBmK-fyqNLM7zuYBYKHbDlwLRklcAcd1f2qN8KcRx0QiujVV3k4PpT_dUHTqFhU5jnbcDHwA2ApxgbXHqEJtxkzYlEWwhiuERxp7UwWWAKClTyTX2ockjNCIvaRgK5mXmtdHfiIQm82J3HhLoA2s4MiOJjr2ijGWRQ-QrxNz-k0Ehtm71t_VzlHoNX-3cI4MZ9Nv_HMfIvClM8c0nVbkd4Np-8ZUdnO8y1ZIL0wk2ugbPkkOcrZWHURl_zvUGZvpDxxlzGBwsh_W0MHDt5ctbC4IsZfZFpV8fMTMwm1EPMjD6SfGS5vDBA8pFcLe3If6xdZT8lwyvvjY1MB_8RQ__cCuKmnrhW11wLDAkTqCI9zW5GcfZkN0sp-8JMs_hFmsNcENnMLHRq5x3efVsaN1uaRvlXmxiNkFM_g-npI7k4KbY-GfUcVTrv0xuz_Cse7FuUB-I_JK9NmRkZ6DGgnQJfcHyW4MjdRfiJgqWrz9Mdcib1aBIyZTmffbAtb8hLRTfNULyFd3Y1MShaxD_yvAaaT0h8KB54XItS67y6EOBrHYsTIC5IYuQ-7kJqfzr7xEZ1bdmPrOckAMUlty1KWtUQyRNtRlHv4CCif_rpo2qFzyqq0xtU9CKIXNoe1KzdSbgKXRVpbfMPCFyXgQyHBFfYTm");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String response =
                restTemplate.exchange(url,
                        HttpMethod.POST,
                        entity,
                        String.class).getBody();
        return gson.fromJson(response, JsonObject.class).get("access_token").getAsString();
    }

    public ResponseEntity<?> uploadFile(MultipartFile file) throws IOException {
        try {
            String filename = file.getOriginalFilename();
            String url = "https://graph.microsoft.com/v1.0/drives/87d32633-f0c8-45b3-aff3-c7d26d3567a8/items/root:/EligibilityCalculatorTest/" + filename + ":/content";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + getAccessToken());
            InputStream inputStream = file.getInputStream();
            byte[] data = IOUtils.toByteArray(inputStream);
            HttpEntity<byte[]> requestEntity = new HttpEntity<>(data,headers);
            ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
            Map<String,String> resp= new HashMap<>();
            resp.put("name", gson.fromJson( responseEntity.getBody(), JsonObject.class).get("name").getAsString());
            resp.put("id", gson.fromJson( responseEntity.getBody(), JsonObject.class).get("id").getAsString());

            return new ResponseEntity<>(gson.toJson(resp),HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>("Internal Server Error: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public String getFormulatedValue(String loanAmount, String roi, String tenure, String fileId) {
        String bearerToken="Bearer "+getAccessToken();
        String sessionId=getSessionId( fileId,  bearerToken);
        String worksheetNumber= getWorksheetNumber(fileId,bearerToken,sessionId);
//        worksheetNumber=worksheetNumber.replace("{","%7B").replace("}","%7D");
        String url="https://graph.microsoft.com/v1.0/me/drive/items/"+fileId+"/workbook/worksheets/"+worksheetNumber+"/range(address='A2:C2')";
//        String url="https://graph.microsoft.com/v1.0/me/drive/items/"+fileId+"/workbook/worksheets/{00000000-0001-0000-0000-000000000000}/range(address='A2:C2')";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);
        headers.set("workbook-session-id", sessionId);
        String body="{\n" +
                "    \"values\": [\n" +
                "        [\n" +
                "            \""+loanAmount+"\",\""+roi+"\",\""+tenure+"\"]\n" +
                "    ]\n" +
                "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(body,headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(600);
        requestFactory.setReadTimeout(600);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        restTemplate.setRequestFactory(requestFactory);
        ResponseEntity<String> responseEntity=restTemplate.exchange(builder.build().encode().toUri(),HttpMethod.PATCH,requestEntity,String.class);
        url="https://graph.microsoft.com/v1.0/me/drive/items/"+fileId+"/workbook/worksheets/"+worksheetNumber+"/range(address='B4:B4')";
        builder = UriComponentsBuilder.fromHttpUrl(url);
        responseEntity=restTemplate.exchange(builder.build().encode().toUri(),HttpMethod.GET,requestEntity,String.class);
        String value=gson.fromJson(responseEntity.getBody(),JsonObject.class).get("values").getAsString();
        Map<String,String> resp= new HashMap<>();
        resp.put("value",value);
        return gson.toJson(resp);
    }

    private String getSessionId(String fileId, String bearerToken)
    {
        String url="https://graph.microsoft.com/v1.0/me/drive/items/"+fileId+"/workbook/createSession";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);
        String body="{ \"persistChanges\": false }";
        HttpEntity<String> requestEntity = new HttpEntity<>(body,headers);
        ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
        return gson.fromJson(responseEntity.getBody(),JsonObject.class).get("id").getAsString();
    }

    private String getWorksheetNumber(String fileId, String bearerToken, String sessionId)
    {
        String url="https://graph.microsoft.com/v1.0/me/drive/items/"+fileId+"/workbook/worksheets";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("workbook-session-id", sessionId);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.GET,requestEntity,String.class);
        return gson.fromJson(responseEntity.getBody(),JsonObject.class).get("value").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
    }
}
