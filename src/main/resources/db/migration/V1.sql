INSERT INTO task_status (id, name, code, created_date, last_modified_date)
VALUES (1, 'Новая', 'new', current_timestamp, current_timestamp)
    ON CONFLICT DO NOTHING;

INSERT INTO task_status (id, name, code, created_date, last_modified_date)
VALUES (2, 'В работе', 'work', current_timestamp, current_timestamp)
    ON CONFLICT DO NOTHING;

INSERT INTO task_status (id, name, code, created_date, last_modified_date)
VALUES (3, 'Отработана', 'finish', current_timestamp, current_timestamp)
    ON CONFLICT DO NOTHING;

INSERT INTO task_priority (id, name, code, created_date, last_modified_date)
VALUES (1, 'Высокий', 'high', current_timestamp, current_timestamp)
ON CONFLICT DO NOTHING;

INSERT INTO task_priority (id, name, code, created_date, last_modified_date)
VALUES (2, 'Средний', 'medium', current_timestamp, current_timestamp)
ON CONFLICT DO NOTHING;

INSERT INTO task_priority (id, name, code, created_date, last_modified_date)
VALUES (3, 'Низкий', 'low', current_timestamp, current_timestamp)
ON CONFLICT DO NOTHING;

INSERT INTO role (id, name, code, created_date, last_modified_date)
VALUES (1, 'Автор', 'author', current_timestamp, current_timestamp)
ON CONFLICT DO NOTHING;

INSERT INTO role (id, name, code, created_date, last_modified_date)
VALUES (2, 'Исполнитель', 'executor', current_timestamp, current_timestamp)
ON CONFLICT DO NOTHING;
