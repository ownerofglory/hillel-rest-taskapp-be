CREATE TABLE board (
                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(64) NOT NULL
);

CREATE TABLE task_list (
                           id INTEGER PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(64) NOT NULL,
                           board_id INTEGER NOT NULL,
                           FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE
)

CREATE TABLE task (
                      id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(64) NOT NULL,
                      description VARCHAR(255),
                      task_list_id INTEGER NOT NULL,
                      FOREIGN KEY (task_list_id) REFERENCES task_list(id)  ON DELETE CASCADE