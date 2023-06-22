package io.bayrktlihn.template.client.tcmb;

import io.bayrktlihn.template.dto.tcmb.TarihDateDto;
import io.bayrktlihn.template.util.XML;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class TCMBClient {


    public TarihDateDto getCurrencies() {
        String toBeRequestedUrl = "https://www.tcmb.gov.tr/kurlar/today.xml";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> httpEntity = new HttpEntity<>(null, null);

        ResponseEntity<String> responseEntity = restTemplate.exchange(toBeRequestedUrl, HttpMethod.GET, httpEntity, String.class);

        HttpHeaders headers = responseEntity.getHeaders();

        String contentType = headers.getFirst("Content-Type");

        if (responseEntity.getStatusCode() == HttpStatus.OK && contentType != null && contentType.equalsIgnoreCase("application/xml")) {
            String xml = responseEntity.getBody();

            return XML.parse(xml, TarihDateDto.class);
        }

        throw new RuntimeException();
    }


}
