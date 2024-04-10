create table user
(
    user_id     mediumint unsigned auto_increment comment '用户ID：[0,8388607]用户获取其他与用户相关的数据'
        primary key,
    state       smallint unsigned default '1'               not null comment '账户状态：[0,10](1可用|2异常|3已冻结|4已注销)',
    user_group  varchar(32)                                 null comment '所在用户组：[0,32767]决定用户身份和权限',
    login_time  timestamp         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '上次登录时间：',
    phone       varchar(11)                                 null comment '手机号码：[0,11]用户的手机号码，用于找回密码时或登录时',
    phone_state smallint unsigned default '0'               not null comment '手机认证：[0,1](0未认证|1审核中|2已认证)',
    username    varchar(16)       default ''                not null comment '用户名：[0,16]用户登录时所用的账户名称',
    nickname    varchar(16)       default ''                null comment '昵称：[0,16]',
    password    varchar(64)       default ''                not null comment '密码：[0,32]用户登录所需的密码，由6-16位数字或英文组成',
    email       varchar(64)       default ''                null comment '邮箱：[0,64]用户的邮箱，用于找回密码时或登录时',
    email_state smallint unsigned default '0'               not null comment '邮箱认证：[0,1](0未认证|1审核中|2已认证)',
    avatar      varchar(255)                                null comment '头像地址：[0,255]',
    create_time timestamp         default CURRENT_TIMESTAMP not null comment '创建时间：'
)
    comment '用户账户：用于保存用户登录信息';

create table tag
(
    id       int auto_increment
        primary key,
    user_id  int           not null,
    tag_name varchar(16)   not null,
    top      int default 0 null
);

create table conversation
(
    id           int auto_increment
        primary key,
    tag_id       int         null,
    user_message text        null,
    bot_message  text        null,
    create_time  varchar(32) null,
    username     varchar(16) null
);
