SELECT C.CODE, C.NAME, C.TYPE, TEACHER.NAME, CLASSROOM.NAME, C.SEMESTER, C.CREDIT FROM H668139.COURSE C INNER JOIN TEACHER ON C.TEACHER_ID=TEACHER.ID INNER JOIN CLASSROOM ON C.CLASSROOM_ID = CLASSROOM.ID LEFT JOIN HALLGATO ON 1=1 WHERE SEMESTER=3 AND ((HALLGATO.EHA, C.CODE) IN (SELECT HALLGATO_EHA, COURSE_CODE FROM REGISTEREDCOURSE WHERE HALLGATO_EHA=? AND GRADE=0))