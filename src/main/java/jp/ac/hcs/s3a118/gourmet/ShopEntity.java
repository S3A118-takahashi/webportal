package jp.ac.hcs.s3a118.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * グルメ検索機能の情報
 *
 */
@Data
public class ShopEntity {
	
	private String shopname;
	
	/** 郵便番号情報のリスト */
	private List<ShopData> results = new ArrayList<ShopData>();
	
}
