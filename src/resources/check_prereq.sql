SELECT P.ELOFELT, RC.GRADE FROM PREREQ P FULL OUTER JOIN REGISTEREDCOURSE RC ON RC.COURSE_CODE=P.ELOFELT AND RC.HALLGATO_EHA=? WHERE TARGY = ?