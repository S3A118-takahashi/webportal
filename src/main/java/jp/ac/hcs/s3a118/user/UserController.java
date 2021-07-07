package jp.ac.hcs.s3a118.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 
	 * large_service_area（大サービスエリアコード）
	 *  * @param shopname 検索する店舗名
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 郵便番号
	 */
	@GetMapping("/user/list")
	public String getUserlist(Principal principal, Model model) {
		
		UserEntity userEntity = userService.getUserlist(principal.getName());
		model.addAttribute("userEntity", userEntity);
		log.info("[" + principal.getName() + "]：ログインユーザー");
		
		return "user/userList";
	}
}
