/* タスクテーブルのデータ */
INSERT INTO task (id, user_id, comment, limitday) VALUES (1, 'user', 'これやる', '2020-03-23');
INSERT INTO task (id, user_id, comment, limitday) VALUES (2, 'owner', 'あれやる', '2020-03-24');
INSERT INTO task (id, user_id, comment, limitday) VALUES (3, 'tester', 'それやる', '2020-03-31');
INSERT INTO task (id, user_id, comment, limitday) VALUES (4, 'developer', 'どれやる', '2020-03-25');
INSERT INTO task (id, user_id, comment, limitday) VALUES (5, 'master', 'もっとやる', '2020-04-20');

/* ユーザマスタのデータ（ADMIN権限） PASS:password */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role, enabled)
VALUES('isida@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '石田悠介', false, 'ROLE_ADMIN', true);
/* ユーザマスタのデータ（一般権限） PASS:password */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role, enabled)
VALUES('abe@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '安部華奈', false, 'ROLE_GENERAL', true);
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role, enabled)
VALUES('sano@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '佐野翼', true, 'ROLE_GENERAL', false);
