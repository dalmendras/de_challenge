-- QUERY 1 --
SELECT
d.department,
j.job,
COUNT(CASE WHEN quarter(he.datetime) = 1 THEN he.id ELSE NULL END) AS Q1,
COUNT(CASE WHEN quarter(he.datetime) = 2 THEN he.id ELSE NULL END) AS Q2,
COUNT(CASE WHEN quarter(he.datetime) = 3 THEN he.id ELSE NULL END) AS Q3,
COUNT(CASE WHEN quarter(he.datetime) = 4 THEN he.id ELSE NULL END) AS Q4
FROM hired_employees he
INNER JOIN departments d ON d.id = he.department_id
INNER JOIN jobs j        ON j.id = he.job_id
WHERE year(he.`datetime`) = 2021
GROUP BY 1, 2
ORDER BY 1, 2;


-- QUERY 2 --
SELECT
d.department,
COUNT(he.id) as hired
FROM hired_employees he
INNER JOIN departments d ON d.id = he.department_id
WHERE year(he.datetime) = 2021
GROUP BY 1
HAVING COUNT(he.id) >= (
							SELECT avg(count_dep) AS mean
							FROM (
									SELECT
									d.department,
									COUNT(he.id) as count_dep
									FROM hired_employees he
									INNER JOIN departments d ON d.id = he.department_id
									WHERE year(he.datetime) = 2021
									GROUP BY 1) as dep_stat
						)
ORDER BY 2 DESC
