-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала»,
-- «длительность»;
WITH Table1 AS (
SELECT	s.film_id,
	s.id,
	s.session_start + f.length AS end_time,
	s.session_start,
	f.title,
	f.length
FROM seances s
JOIN films f ON s.film_id = f.id
WHERE	(s.session_start >= '14.02.2021 00:00:00'
AND		s.session_start < '15.02.2021 00:00:00')
), Table2 AS (
SELECT	s.film_id,
	s.id,
	s.session_start,
	f.title,
	f.length
FROM seances s
JOIN films f ON s.film_id = f.id
WHERE	(s.session_start >= '14.02.2021 00:00:00'
AND		s.session_start < '15.02.2021 00:00:00')
)
SELECT 	t1.title as film1,
	t1.session_start as film1_start,
	t1.length as film1_length,
	t2.title as film2,
	t2.session_start as film2_start,
	t2.length as film2_length
FROM	Table1 t1,
	Table2 t2
WHERE	t1.end_time > t2.session_start
AND	t1.session_start < t2.session_start

--перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма»,
-- «длительность перерыва»;
--не очень понял надо ли выводить только промежутки между фильмами, идущими подряд,
-- или между всеми фильмами. Вывел между всеми.
WITH Table1 AS (
SELECT	s.film_id,
		s.id,
		s.session_start + f.length AS end_time,
		s.session_start,
		f.title,
		f.length
FROM seances s
JOIN films f ON s.film_id = f.id
WHERE	(s.session_start >= '14.02.2021 00:00:00'
AND		s.session_start < '15.02.2021 00:00:00')
), Table2 AS (
SELECT	s.film_id,
		s.id,
		s.session_start,
		f.title,
		f.length
FROM seances s
JOIN films f ON s.film_id = f.id
WHERE	(s.session_start >= '14.02.2021 00:00:00'
AND		s.session_start < '15.02.2021 00:00:00')
)
SELECT 	t1.title as film1_title,
 		t1.session_start as film1_start,
 		t1.length as film1_length,
 		t2.session_start as film2_start,
 		t2.session_start - t1.end_time as break_duration
FROM	Table1 t1,
		Table2 t2
WHERE	t2.session_start > t1.end_time + '0:30'
ORDER BY break_duration DESC

--список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа
-- зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
--Итого так и не получилось сделать внизу.
SELECT	t1.title,
	SUM(t1.ticket_count) as visitor_count,
	ROUND(SUM(t1.ticket_count) / COUNT(t1.film_id)) visitor_average,
	SUM(t1.sess_sum) as sum
FROM (
	SELECT	(SELECT title FROM films WHERE id = s.film_id) as title,
		s.film_id,
		s.id as sess_id,
		COUNT(tk.id) as ticket_count,
		SUM(s.ticket_price) as sess_sum
	FROM	seances s
	JOIN	tickets tk ON tk.session_id = s.id
	GROUP BY title, s.id
) t1
GROUP BY title

UNION ALL
	
SELECT	'Total' as title,
	COUNT(tk.id) as visitor_count,
	null as visitor_average,
	SUM(s.ticket_price) as sum
FROM	seances s
JOIN	tickets tk ON tk.session_id = s.id
ORDER BY sum DESC

--число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15,
-- с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
SELECT	'from 9 to 15' as period,
		COUNT(tk.id),
		SUM(s.ticket_price)
FROM	seances s
JOIN	tickets tk ON s.id = tk.session_id
WHERE	(s.session_start >= '14.02.2021 9:00' 
AND		s.session_start < '14.02.2021 15:00')

UNION ALL

SELECT	'from 15 to 18' as period,
		COUNT(tk.id),
		SUM(s.ticket_price)
FROM	seances s
JOIN	tickets tk ON s.id = tk.session_id
WHERE	(s.session_start >= '14.02.2021 15:00' 
AND		s.session_start < '14.02.2021 18:00')

UNION ALL

SELECT	'from 18 to 21' as period,
		COUNT(tk.id),
		SUM(s.ticket_price)
FROM	seances s
JOIN	tickets tk ON s.id = tk.session_id
WHERE	(s.session_start >= '14.02.2021 18:00' 
AND		s.session_start < '14.02.2021 21:00')

UNION ALL

SELECT	'from 21 to 00' as period,
		COUNT(tk.id),
		SUM(s.ticket_price)
FROM	seances s
JOIN	tickets tk ON s.id = tk.session_id
WHERE	(s.session_start >= '14.02.2021 21:00' 
AND		s.session_start < '15.02.2021 00:00')

