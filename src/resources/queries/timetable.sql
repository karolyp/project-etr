SELECT C.NAME, CR.NAME, C.TIME FROM COURSE C INNER JOIN REGISTEREDCOURSE RC ON RC.COURSE_CODE=C.CODE AND RC.HALLGATO_EHA = ? AND RC.GRADE = 0 INNER JOIN CLASSROOM CR ON C.CLASSROOM_ID=CR.ID WHERE C.TIME <> 'NAN'