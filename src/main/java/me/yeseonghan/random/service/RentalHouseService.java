package me.yeseonghan.random.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class RentalHouseService {
    public String fetchAllDataFromApi(){
        String url = "https://data.myhome.go.kr:443/rentalHouseList"
                + "?ServiceKey=kzpIrWgRJly5HX294jNnJRRXncsLe1FgO8VtnlBpI91NNj8kfxSzZ4uMCw75b26zMCY1Tu9iPsn1U/91MxSZVA=="
                + "&brtcCode=41"
                + "&signguCode=41113"
                + "&numOfRows=10"
                + "&pageNo=1";


        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
