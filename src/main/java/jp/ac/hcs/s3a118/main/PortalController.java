package jp.ac.hcs.s3a118.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
