
CREATE database yb_chat; -- DB 생성
CREATE user ybadmin@localhost identified BY '1111'; -- user 생성
GRANT ALL PRIVILEGES ON yb_chat.* TO ybadmin@localhost; -- user 권한 부여

CREATE TABLE `member` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `create_at` TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
   `update_at` TIMESTAMP NULL,
   `name` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
   `password` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci',
   `phone` VARCHAR(50) NULL COLLATE 'utf8_general_ci',
   `email` VARCHAR(50) NULL COLLATE 'utf8_general_ci',
   `bio` TEXT NULL COLLATE 'utf8_general_ci',
   `country` VARCHAR(50) 'utf8_general_ci',
   PRIMARY KEY (`id`) USING BTREE
)
COMMENT='회원'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
--AUTO_INCREMENT=6
;

CREATE TABLE `chat_room` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `create_at` TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
   `update_at` TIMESTAMP NULL,
   `room_name` VARCHAR(50) NULL COLLATE 'utf8_general_ci',
   `room_topic` VARCHAR(50) NULL COLLATE 'utf8_general_ci',
   `room_desc` VARCHAR(50) NULL COLLATE 'utf8_general_ci',
   `status` ENUM('O','C') NULL COLLATE 'utf8_general_ci',
   PRIMARY KEY (`id`) USING BTREE
)
COMMENT='채팅그룹'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
--AUTO_INCREMENT=13
;

CREATE TABLE `chat_message` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `create_timestamp` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
   `update_timestamp` TIMESTAMP NULL,
   `message` TEXT NOT NULL COLLATE 'utf8_general_ci',
   `member_id` BIGINT(20) NOT NULL,
   `room_id` BIGINT(20) NOT NULL,
   PRIMARY KEY (`id`) USING BTREE,
   INDEX `FK_chat_message_member` (`member_id`) USING BTREE,
   INDEX `FK_chat_message_chat_room` (`room_id`) USING BTREE,
   CONSTRAINT `FK_chat_message_chat_room` FOREIGN KEY (`room_id`) REFERENCES `yb_chat`.`chat_room` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
   CONSTRAINT `FK_chat_message_member` FOREIGN KEY (`member_id`) REFERENCES `yb_chat`.`member` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `chat_room_member_join` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `create_at` TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
   `update_at` TIMESTAMP NULL,
   `room_id` BIGINT(20) NULL,
   `member_id` BIGINT(20) NULL,
   `connection` ENUM('Y','N') NULL COLLATE 'utf8_general_ci',
   PRIMARY KEY (`id`) USING BTREE,
   INDEX `FK_chat_room_join_member` (`member_id`) USING BTREE,
   INDEX `FK_chat_room_join_chat_room` (`room_id`) USING BTREE,
   CONSTRAINT `FK_chat_room_join_chat_room` FOREIGN KEY (`room_id`) REFERENCES `yb_chat`.`chat_room` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
   CONSTRAINT `FK_chat_room_join_member` FOREIGN KEY (`member_id`) REFERENCES `yb_chat`.`member` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
--AUTO_INCREMENT=40
;
