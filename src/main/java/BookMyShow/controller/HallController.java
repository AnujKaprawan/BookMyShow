package BookMyShow.controller;


import BookMyShow.dto.request.AddScreenDTO;
import BookMyShow.dto.request.HallOwnerSignUpDTO;
import BookMyShow.dto.responce.GeneralMessageDTO;
import BookMyShow.exceptions.ResourceNotExistException;
import BookMyShow.exceptions.UnAuthorized;
import BookMyShow.exceptions.UserDoesNotExistException;
import BookMyShow.models.ApplicationUser;
import BookMyShow.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    HallService hallService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody HallOwnerSignUpDTO hallOwnerSignUpDTO){
        ApplicationUser user = hallService.signUp(hallOwnerSignUpDTO);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PostMapping("/addscreen")
    public ResponseEntity addScreen(@RequestBody AddScreenDTO addScreenDTO, @RequestParam String email){
        try{
            hallService.addScreens(addScreenDTO, email);
        }catch (UserDoesNotExistException e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND); // 404
        }catch(UnAuthorized e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()), HttpStatus.UNAUTHORIZED); //401
        }catch (ResourceNotExistException e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity(new GeneralMessageDTO("Screens added successfully"), HttpStatus.CREATED); // 201
    }


}
