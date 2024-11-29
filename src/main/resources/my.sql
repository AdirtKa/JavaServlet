SELECT * FROM subjects;
SELECT * FROM schedule;

SELECT time from schedule;

SELECT schedule.id, schedule.day_of_week, to_char(schedule.time, 'hh24:mi') as time, s.name, schedule.classroom
FROM schedule
INNER JOIN subjects s on s.id = schedule.subject_id;

SELECT subjects.id, subjects.name
FROM subjects;