
INSERT INTO aichat.user (user_id, state, user_group, login_time, phone, phone_state, username, nickname, password, email, email_state, avatar, create_time) VALUES (1, 1, '管理员', '2024-04-01 15:27:54', '134', 0, 'admin', 'admin', '0adaf13981e124c964122bc941caa33a', '', 0, null, '2024-03-01 10:43:42');
INSERT INTO aichat.user (user_id, state, user_group, login_time, phone, phone_state, username, nickname, password, email, email_state, avatar, create_time) VALUES (2, 1, '医生', '2024-03-15 10:43:17', '1348', 1, 'jsss', 'jsss', '0c8ecdf260b09a179a8e3c964185356a', '3063494684@qq.com', 1, null, '2024-03-05 09:56:54');
INSERT INTO aichat.user (user_id, state, user_group, login_time, phone, phone_state, username, nickname, password, email, email_state, avatar, create_time) VALUES (3, 1, '医生', '2024-04-02 17:27:37', null, 0, 'doctor', 'doctor', 'c2fb1509e2274dbf4b187e2752fb8ae6', '', 0, null, '2024-03-06 09:51:03');
INSERT INTO aichat.user (user_id, state, user_group, login_time, phone, phone_state, username, nickname, password, email, email_state, avatar, create_time) VALUES (4, 1, '老人家属', '2024-04-01 15:28:07', null, 1, 'family', 'family1', 'e8ad3953b17e55a001b4e4fc6f5b145c', '', 0, null, '2024-03-06 14:39:31');
INSERT INTO aichat.user (user_id, state, user_group, login_time, phone, phone_state, username, nickname, password, email, email_state, avatar, create_time) VALUES (5, 1, '普通用户', '2024-04-02 23:28:37', '', 0, 'user', 'user', 'feb7433c700f5c45fc5c456b75ba49cf', '', 0, null, '2024-03-12 16:49:00');


INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (1, 5, 'test', 1);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (2, 5, 'test5', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (6, 5, 'test6', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (7, 5, 'test7', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (8, 5, 'test8', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (9, 5, 'test9', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (10, 5, 'test10', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (12, 5, 'test12', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (13, 5, 'test13', 1);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (14, 5, '新对话', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (18, 5, '新对话', 0);
INSERT INTO aichat.tag (id, user_id, tag_name, top) VALUES (19, 5, '新对话', 0);

INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (1, 1, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-03-25 23:50:14', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (2, 1, '你是', '你好，我是百度公司开发的文心一言，英文名是ERNIE Bot，可以协助你完成范围广泛的任务并提供有关各种主题的信息，比如回答问题，提供定义和解释及建议。如果你有任何问题，请随时向我提问。', '2024-03-26 21:09:54', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (3, 1, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-03-31 21:44:53', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (4, 1, '你好', '你好，有什么我可以帮助你的吗？', '2024-04-01 19:40:14', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (5, 2, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-04-01 22:55:27', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (6, 2, '你好', '你好，有什么我可以帮助你的吗？', '2024-04-01 22:56:38', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (7, 1, '你是', '你好，我是百度公司开发的文心一言，英文名是ERNIE Bot，可以协助你完成范围广泛的任务并提供有关各种主题的信息，比如回答问题，提供定义和解释及建议。如果你有任何问题，请随时向我提问。', '2024-04-01 23:05:26', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (8, 1, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-04-01 23:05:35', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (9, 9, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-04-02 16:31:36', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (10, 13, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-04-02 18:38:08', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (11, 7, 'test7说你好', '您好，请问有什么需要帮助的吗？', '2024-04-02 22:38:27', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (12, 7, '你能讲个故事吗', '当然可以，这是一个关于一只小熊和它的朋友们的冒险故事。

在一个美丽的森林里，住着一只可爱的小熊，它叫做毛毛。毛毛有很多好朋友，包括兔子跳跳，松鼠奇奇，和一只聪明的猫头鹰。一天，毛毛决定组织一次森林里的冒险，邀请所有的朋友一起去探险。

他们首先来到了森林深处的一片神秘的湖泊。湖中有一座小岛，岛上有一棵巨大的果树，树上结满了美味的果实。毛毛和他的朋友们决定一起摘果子吃。但是，当他们到达果树时，他们发现了一个问题：果树太高了，他们够不到。

这时，聪明的猫头鹰想到了一个主意。他用他的智慧找到了一个长长的树枝，并把它扔给了小熊。小熊用它够到了果实，其他朋友们也纷纷效仿。他们高兴地享受着美味的果实，然后继续他们的冒险之旅。

接下来，他们来到了森林的另一边，那里有一座巨大的山洞。他们决定一起探索这个山洞。在山洞里，他们发现了一个宝藏，里面装满了金子和宝石。他们高兴地分享了这些财富，然后继续他们的旅程。

最后，他们来到了一片神秘的沙漠。在那里，他们遇到了一只迷路的小鸟。毛毛和他的朋友们帮助小鸟找到了回家的路，并把它送走了。小鸟感激不已，为了感谢他们，它送给他们一串神奇的珍珠。

最后，他们带着这串珍珠回到了森林，分享了他们的冒险经历。他们感到非常开心和满足，因为他们不仅成功地完成了这次冒险，还结识了许多新朋友。

这个故事告诉我们，友谊和团结是克服困难的关键。只要我们团结一致，我们就可以战胜任何挑战。这就是毛毛和他的朋友们的故事，一个充满友谊、冒险和成长的奇妙故事。', '2024-04-02 22:50:51', 'user');
INSERT INTO aichat.conversation (id, tag_id, user_message, bot_message, create_time, username) VALUES (13, 18, '你是', '您好，我是百度研发的知识增强大语言模型，中文名是文心一言，英文名是ERNIE Bot。我能够与人对话互动，回答问题，协助创作，高效便捷地帮助人们获取信息、知识和灵感。', '2024-04-02 23:26:12', 'user');
