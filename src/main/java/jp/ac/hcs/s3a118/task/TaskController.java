package jp.ac.hcs.s3a118.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	/**
	 * タスク一覧画面を表示する
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - タスク管理
	 */
	@PostMapping("/task")
	public String getTask(Principal principal ,Model model) {
		
		TaskEntity taskEntity = taskService.selectAll(principal.getName());
		if (taskEntity == null) {
			log.info("[" + principal.getName() + "]タスク検索：エラーが発生しました。");
		}
		model.addAttribute("taskEntity", taskEntity);
		
		return "task/task";
	}
	@PostMapping("/task/insert")
	public String insertTask(@RequestParam("comment") String comment, @RequestParam("limitday") String limitday, Principal principal, Model model) {
		
		log.info("[" + principal.getName() + "]タスク追加：" + "コメント:" + comment + "期限日：" + limitday);
		boolean isSuccess = taskService.insertOne(principal.getName(), comment, limitday);
		if (isSuccess) {
			log.info("成功");
		}else {
			log.info("失敗");
		}
		
		return getTask(principal, model);
		
	}
}
