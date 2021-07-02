package jp.ac.hcs.s3a118.task;

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
	


	public boolean insertOne(String user_id, String comment, String limitday) {
		TaskData data = refillToData(user_id, comment, limitday);
		int count = taskReposirory.insertOne(data);
		return count > 0;
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
