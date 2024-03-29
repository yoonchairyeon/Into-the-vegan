package kr.ac.sku.intothevegan.application.service;


import kr.ac.sku.intothevegan.domain.PlaceInfo;
import kr.ac.sku.intothevegan.infrastructure.persistence.PlaceInfoRepository;
import kr.ac.sku.intothevegan.presentation.ImageSearchApi;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceService {

    @Autowired
    PlaceInfoRepository placeInfoRepository;

    /* 전체 플레이스 조회 */
    public List<PlaceInfo> getAllPlaces() {
        String result = "";

        try {
            /* 서울 열린 데이터 광장 Open API - 서울 음식점 정보 가져오기 */
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + "456869487863687234325169566655/" +
                    "json/CrtfcUpsoInfo/501/1500");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject CrtfcUpsoInfo = (JSONObject)jsonObject.get("CrtfcUpsoInfo");

            JSONArray infoArr = (JSONArray) CrtfcUpsoInfo.get("row");

            for(int i=0;i<infoArr.size();i++){

                JSONObject tmp = (JSONObject)infoArr.get(i);
                if(!tmp.get("CRTFC_GBN").equals("14")) continue;

                if (placeInfoRepository.count() > 500) break;
                if (placeInfoRepository.countUPSONM((String) tmp.get("UPSO_NM")) >= 1) continue;


                /* 네이버 검색 API - 음식점의 이미지 정보 가져오기 */
                String clientId = "uW4ly9VSD8W0CdnUTgvu";
                String clientSecret = "s7xQ2C3z4a";

                String text = null;
                try {
                    text = URLEncoder.encode((String) tmp.get("UPSO_NM"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("검색어 인코딩 실패",e);
                }

                String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text;

                Map<String, String> requestHeaders = new HashMap<>();
                requestHeaders.put("X-Naver-Client-Id", clientId);
                requestHeaders.put("X-Naver-Client-Secret", clientSecret);
                String responseBody = ImageSearchApi.get(apiURL,requestHeaders);

                String image_url = "https://cdn.pixabay.com/photo/2020/08/05/13/27/eco-5465459_1280.png";
                JSONParser parser = new JSONParser();
                JSONObject apiresult = (JSONObject) parser.parse(responseBody);
                JSONArray items = (JSONArray) apiresult.get("items");
                if(items != null && items.size() != 0) {
                    JSONObject item = (JSONObject) items.get(0);
                    image_url = (String) item.get("link");
                }

                PlaceInfo infoObj = new PlaceInfo(i+(long)i,
                        (String)tmp.get("UPSO_NM"),
                        (String)tmp.get("COB_CODE_NM"),
                        (String)tmp.get("CRTFC_GBN_NM"),
                        (String)tmp.get("RDN_CODE_NM"),
                        (String)tmp.get("TEL_NO"),
                        (String)tmp.get("FOOD_MENU"),
                        (String)tmp.get("X_CNTS"),
                        (String)tmp.get("Y_DNTS"),
                        image_url
                );
                placeInfoRepository.save(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return placeInfoRepository.findAll();
    }
}
