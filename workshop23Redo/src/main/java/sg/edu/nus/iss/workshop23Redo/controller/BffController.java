package sg.edu.nus.iss.workshop23Redo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.workshop23Redo.models.Bff;
import sg.edu.nus.iss.workshop23Redo.service.BffException;
import sg.edu.nus.iss.workshop23Redo.service.BffService;

import static sg.edu.nus.iss.workshop23Redo.models.ConversionUtils.*;

@Controller
@RequestMapping("/bff")
public class BffController {
    
    @Autowired
    private BffService bffSvc;

    @PostMapping
    public ModelAndView checkForBff(@RequestBody MultiValueMap<String,String> form) {

        Bff extractedBff = convert(form);

        System.out.println(">>>>>> bff: " + extractedBff.getDob());
        System.out.println(">>>>>> bff: " + extractedBff.getName());

        ModelAndView mvc = new ModelAndView();
        try {
            bffSvc.addNewUser(extractedBff);
            mvc.addObject("message", "%s is already inside the database".formatted(extractedBff.getName()));
            mvc.addObject("bffList2", bffSvc.getAllBffs());
            mvc.setViewName("index");
        } catch (BffException ex) {
            mvc.addObject("message", "Error: %s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("index");
            ex.printStackTrace();
        }

        
        return mvc;
    }

}
