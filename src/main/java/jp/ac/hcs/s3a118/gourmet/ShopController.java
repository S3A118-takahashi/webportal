package jp.ac.hcs.s3a118.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ShopController {
	@Autowired
	private GourmetService gourmetService;
	/**
	 * 
	 * large_service_area（大サービスエリアコード）
	 *  * @param shopname 検索する店舗名
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 郵便番号
	 */
	@PostMapping("/gourmet")
	public String getShopname(@RequestParam("shopname") String shopname, Principal principal, Model model) {
		
		String large_service_area = "SS40";
		
		ShopEntity shopEntity = gourmetService.getGourmet(shopname, large_service_area);
		model.addAttribute("shopEntity", shopEntity);
		log.info("[" + principal.getName() + "]住所検索：" + shopname);
		
		if (shopname == "") {
			return "index";
		}
		
		return "gourmet/gourmet";
	}
}
