package BookMyShow.controller;


import BookMyShow.dto.request.RegularUserSignupDTO;
import BookMyShow.models.ApplicationUser;
import BookMyShow.service.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegularUserController {
    @Autowired
    RegularUserService regularUserService;
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody  RegularUserSignupDTO regularUserSignupDTO){
        ApplicationUser user = regularUserService.signup(regularUserSignupDTO);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}