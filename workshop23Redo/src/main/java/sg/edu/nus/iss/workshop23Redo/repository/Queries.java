package sg.edu.nus.iss.workshop23Redo.repository;

public interface Queries {
    public static final String SQL_SELECT_USER_FROM_EMAIL = "select * from bff where email = ?"; 
    public static final String SQL_GET_ALL_BFF = "select * from bff order by name";
    public static final String SQL_INSERT_BFF = 
        "insert into bff (email, name, phone, status, pass_phrase, dob) values (?, ?, ?, ?, sha1(?), ?)";


    public static final String SQL_DELETE_BFF_BY_EMAIL = "delete from bff where email = ?";
}
