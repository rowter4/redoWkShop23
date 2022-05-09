package sg.edu.nus.iss.workshop23Redo.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.workshop23Redo.models.Bff;

import static sg.edu.nus.iss.workshop23Redo.repository.Queries.*;

@Repository
public class BffRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<Bff> findUserByEmail(String email) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_USER_FROM_EMAIL, email);
        
        if (!result.next())
            return Optional.empty();

        return Optional.of(convert(result));
    }

    public List<Bff> selectAllBff() {
        List<Bff> bffList = new LinkedList<>();
        
        final SqlRowSet result = template.queryForRowSet(SQL_GET_ALL_BFF);
        while(result.next()){
            Bff bff = convert(result);
            bffList.add(bff);
        }
        return bffList;
    }

    public static Bff convert(SqlRowSet result) {
        Bff newBff = new Bff();

        // the columnLabels are what is reflected inside the database. It must follow the table columns in schema.sql
        newBff.setEmail(result.getString("email"));
        newBff.setDob(result.getDate("dob"));
        newBff.setName(result.getString("name"));
        newBff.setPassphrase(result.getString("pass_phrase"));
        newBff.setPhone(result.getString("phone"));
        newBff.setStatus(result.getString("status"));

        return newBff;
    }

    public boolean insertBff(Bff bff) {
        // (email, name, phone, status , pass_phrase, dob) 
        int count = template.update(SQL_INSERT_BFF, bff.getEmail(), bff.getName()
                , bff.getPhone(), bff.getStatus(), bff.getPassphrase(), bff.getDob());
        return 1 == count;
    }

    // this segment is for the tests. There is a need to remove whatever that is inserted inside the database for testing.
    public boolean deleteBffByEmail(String email) {
        int count = template.update(SQL_DELETE_BFF_BY_EMAIL, email);
        return 1 == count;
    }
}
