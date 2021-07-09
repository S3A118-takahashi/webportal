package jp.ac.hcs.s3a118.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
	/**
	 * ユーザ情報を操作する
	 * 
	 *
	 */
	@Autowired
	UserRepository userRepository;
	
	/**指定した店舗名と大サービスエリアコードに紐づく店舗情報を取得する
	 * @param
	 * @param
	 */
	public UserEntity getUserlist(String name) {
		UserEntity userEntity;
		try {
			userEntity = userRepository.selectAll();
		} catch(DataAccessException e) {
			e.printStackTrace();
			userEntity = null;
		}
		return userEntity;
		
	}
	
	/**
	 * ユーザ情報を1件追加する
	 * @param userData 追加するユーザ情報（パスワードは平文）
	 * @return 処理結果(成功：true, 失敗：false)
	 */
	public boolean insertOne(UserData userData) {
		int rowNumber;
		try {
			rowNumber = userRepository.insertOne(userData);
		}catch (DataAccessException e) {
			e.printStackTrace();
			rowNumber = 0;
		}
		return rowNumber > 0;
	}
	
	/**
	 * 入力項目をUserDataへ変換する
	 * (このメソッドは入力チェックを実施したうえで呼び出す事)
	 * @param form 入力データ
	 * @return UserData
	 */
	UserData refillToData(UserForm form) {
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setPassword(form.getPassword());
		data.setUser_name(form.getUser_name());
		data.setDarkmode(form.isDarkmode());
		data.setRole(form.getRole());
		// 初期値は有効とする
		data.setEnabled(true);
		return data;
	}
	
	/**
	 * ユーザを１件表示する
	 */
	public UserData getUserDetail(String user_id) {
		UserData userData;
		try {
			userData = userRepository.selectOne(user_id);
		} catch(DataAccessException e) {
			e.printStackTrace();
			userData = null;
		}
		return userData;
		
	}
}