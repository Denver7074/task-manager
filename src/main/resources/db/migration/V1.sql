INSERT INTO task_status (id, name, code)
VALUES (1, 'Новая', 'new')
    ON CONFLICT DO NOTHING;

INSERT INTO task_status (id, name, code)
VALUES (2, 'В работе', 'work')
    ON CONFLICT DO NOTHING;

INSERT INTO task_status (id, name, code)
VALUES (3, 'Отработана', 'finish')
    ON CONFLICT DO NOTHING;

INSERT INTO task_priority (id, name, code)
VALUES (1, 'Высокий', 'high')
ON CONFLICT DO NOTHING;

INSERT INTO task_priority (id, name, code)
VALUES (2, 'Средний', 'medium')
ON CONFLICT DO NOTHING;

INSERT INTO task_priority (id, name, code)
VALUES (3, 'Низкий', 'low')
ON CONFLICT DO NOTHING;

-- INSERT INTO role (id, name, code)
-- VALUES (1, 'Автор', 'author')
-- ON CONFLICT DO NOTHING;
--
-- INSERT INTO role (id, name, code)
-- VALUES (2, 'Исполнитель', 'executor')
-- ON CONFLICT DO NOTHING;
