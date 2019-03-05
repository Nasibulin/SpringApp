CREATE TABLE test (
  id NUMBER(10) NOT NULL,
  message VARCHAR2(100) NOT NULL,
  datetime TIMESTAMP(0) DEFAULT SYSTIMESTAMP NOT NULL,
  done NUMBER(1) DEFAULT 0 NOT NULL,
  PRIMARY KEY (id))
;

-- Generate ID using sequence and trigger
CREATE SEQUENCE test_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER test_seq_tr 
BEFORE INSERT ON test 
FOR EACH ROW

BEGIN
  SELECT test_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;


INSERT INTO test (message,done) VALUES ('Start the moral preparation for the speedy execution of the test task.', 0);
INSERT INTO test (message,done) VALUES ('Pay attention to China Mieville creativity. Recommended "Rails"', 0);
INSERT INTO test (message,done) VALUES ('Continue to maintain the physical form in the power loads', 1);
INSERT INTO test (message,done) VALUES ('Look in the pet shop and buy tablets for the dog', 1);
INSERT INTO test (message,done) VALUES ('The sister asked to look into the bookstore and check the availability of "1984"', 0);
INSERT INTO test (message,done) VALUES ('Alas. Absolutely do not understand anything in the set conditions. It is necessary to think', 0);
INSERT INTO test (message,done) VALUES ('Still do not understand anything. Pretty hard', 0);
INSERT INTO test (message,done) VALUES ('Maybe pay attention to Spring Boot? Looks interesting', 0);
INSERT INTO test (message,done) VALUES ('It remains to determine the visualization. Wide choice, very', 1);
INSERT INTO test (message,done) VALUES ('Explore the details of entry in html', 0);
INSERT INTO test (message,done) VALUES ('Try to create the first program to display', 0);
INSERT INTO test (message,done) VALUES ('Error. I continue to understand nothing. Need to learn the documentation', 1);
INSERT INTO test (message,done) VALUES ('More documentation to the god of documentation! The error does not go away', 0);
INSERT INTO test (message,done) VALUES ('Begins to work out. Difficult, but it becomes more or less clear', 0);
INSERT INTO test (message,done) VALUES ('Explore the intricacies of the Thymeleaf syntax for displaying data', 0);
INSERT INTO test (message,done) VALUES ('Problems with displaying the table. I need to test another idea', 0);
INSERT INTO test (message,done) VALUES ('It turns out. It''s crooked, but it works. It is necessary to repair', 1);
INSERT INTO test (message,done) VALUES ('It is necessary to raise information about reading from the database. Important condition', 0);
INSERT INTO test (message,done) VALUES ('Maybe try to add a stylization?', 0);
INSERT INTO test (message,done) VALUES ('Yes! Really works. I need to add a filter', 0);
INSERT INTO test (message,done) VALUES ('Problems with sorting the date. It is necessary to understand', 0);
INSERT INTO test (message,done) VALUES ('Done. It remains only to check for errors', 0);
INSERT INTO test (message,done) VALUES ('And again, add a stylized beauty', 0);
INSERT INTO test (message,done) VALUES ('And, of course, send the completed task for verification', 0);
