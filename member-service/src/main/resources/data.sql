CREATE TABLE bills(m_id varchar(20), last_paid_date date, due_amount int, late_charge int, due_date date, p_id varchar(20));

INSERT INTO bills(m_id, last_paid_date, due_amount, late_charge, due_date, p_id) VALUES ('M101', '2020-06-01', 10000, 800, '2021-02-01',  'P101');
INSERT INTO bills(m_id, last_paid_date, due_amount, late_charge, due_date, p_id) values('M102', '2019-12-10', 50500, 6500, '2020-12-10', 'P103');
INSERT INTO bills(m_id, last_paid_date, due_amount, late_charge, due_date, p_id) values('M103', '2021-07-20', 12000, 0, '2022-07-30', 'P104');
INSERT INTO bills(m_id, last_paid_date, due_amount, late_charge, due_date, p_id) values('M104', '2021-01-01', 8000, 0, '2021-08-10', 'P102;');
INSERT INTO bills(m_id, last_paid_date, due_amount, late_charge, due_date, p_id) values('M105', '2020-01-05', 25000, 4000, '2021-07-05', 'P103');