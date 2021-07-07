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
}