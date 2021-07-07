package jp.ac.hcs.s3a118.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * グルメ情報を操作する
 * 
 *
 */
@Transactional
@Service
public class GourmetService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** グルメサーチAPI リクエストURL */
	private static final String URL = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";
	
	String API_KEY = "80c0f687598a365f";
	/**指定した店舗名と大サービスエリアコードに紐づく店舗情報を取得する
	 * @param
	 * @param
	 */
	public ShopEntity getGourmet(String shopname, String large_service_area) {
		// APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
		
		ShopEntity shopEntity = new ShopEntity();
		shopEntity.setSearchWord(shopname);
		
		// jsonクラスへの変換失敗の為、例外処理
		try {
			// 変換クラスを生成し、文字列からjsonクラスへ変換する(例外の可能性あり)
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			// resultsパラメータの抽出(配列分取得する)
			for(JsonNode result : node.get("results").get("shop")) {
				// データクラスの生成(result1件分)
				ShopData shopData = new ShopData();
				shopData.setId(result.get("id").asText());
				shopData.setName(result.get("name").asText());
				shopData.setLogo_image(result.get("logo_image").asText());
				shopData.setName_kana(result.get("name_kana").asText());
				shopData.setAddress(result.get("address").asText());
				shopData.setAccess(result.get("access").asText());
				shopData.setUrl(result.get("urls").get("pc").asText());
				shopData.setImage(result.get("photo").get("mobile").get("l").asText());
				
				// 可変長配列の末尾に追加
				shopEntity.getResults().add(shopData);
			}
		} catch(IOException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		return shopEntity;
		
	}
	
}
