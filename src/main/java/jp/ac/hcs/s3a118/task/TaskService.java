package jp.ac.hcs.s3a118.task;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク情報を操作する
 */
@Transactional
@Service
public class TaskService {
	
	@Autowired
	TaskReposirory taskReposirory;
	
	/**
	 * 指定したユーザIDのタスク情報を全件取得する
	 * @param userId ユーザID
	 * @return TaskEntity
	 * @throws DataAccessException
	 */
	public TaskEntity selectAll(String userId) {
		TaskEntity taskEntity;
		try {
			taskEntity = taskReposirory.selectAll(userId);
		} catch(DataAccessException e) {
			e.printStackTrace();
			taskEntity = null;
		}
		return taskEntity;
	}
	/**
	 * 入力されたタスク内容と期限日でDBに登録する
	 * @param user_id ユーザID
	 * @param comment タスク内容
	 * @param limitday 期限日
	 * @return 1件以上であればtrue、それ以外はfalse
	 */
	public boolean insertOne(String user_id, String comment, String limitday) {
		//TaskData型へ詰め替える
		TaskData data = refillToData(user_id, comment, limitday);
		//登録件数を取得する
		int count = taskReposirory.insertOne(data);
		return count > 0;
	}
	

	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskReposirory.tasklistCsvOut(user_id);
	}

	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 */
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
	
	
	private TaskData refillToData(String user_id, String comment, String limitday) {
		TaskData data = new TaskData();
		data.setUser_id(user_id);
		data.setComment(comment);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			data.setLimitday(format.parse(limitday));
		} catch(ParseException e) {
			// 何もしない(入力チェック済みの為、変化エラーは起こりえない)
		}
		return data;
	}
}
