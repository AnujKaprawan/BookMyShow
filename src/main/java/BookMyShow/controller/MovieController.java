package BookMyShow.controller;

import BookMyShow.dto.request.MovieOwnerSignUpDTO;
import BookMyShow.models.ApplicationUser;
import BookMyShow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody MovieOwnerSignUpDTO movieOwnerSignUpDTO){
        ApplicationUser user = movieService.signUp(movieOwnerSignUpDTO);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}