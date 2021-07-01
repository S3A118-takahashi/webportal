package jp.ac.hcs.s3a118.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	/**
	 * 都市コードから明日の天気予報をする
	 * @param citycode 都市コード
	 * @param model
	 * @return 結果画面 - 天気予報
	 */
	@PostMapping("/weather")
	public String getWeather(Principal principal ,Model model) {
		
		String cityCode = "016010";
		
		WeatherEntity weatherEntity = weatherService.getWeather(cityCode);
		model.addAttribute("weatherEntity", weatherEntity);
		
		return "weather/weather";
	}
}
