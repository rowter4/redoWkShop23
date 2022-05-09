package sg.edu.nus.iss.workshop23Redo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop23Redo.models.Bff;
import sg.edu.nus.iss.workshop23Redo.repository.BffRepository;

@Service
public class BffService {

    @Autowired
    private BffRepository bffRepo;


    public void addNewUser(Bff newBff) throws BffException {
        Optional<Bff> opt = bffRepo.findUserByEmail(newBff.getEmail());

        if (opt.isPresent())
            throw new BffException("%s is in the database".formatted(newBff.getName()));

        if (!bffRepo.insertBff(newBff))
            throw new BffException("Cannot add %s as BFF. Please check with admin".formatted(newBff.getEmail()));
    }

    public List<Bff> getAllBffs(){
        return bffRepo.selectAllBff();
    }
}
