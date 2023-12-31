package BookMyShow.service;


import BookMyShow.dto.request.AddScreenDTO;
import BookMyShow.dto.request.AddShowDTO;
import BookMyShow.dto.request.HallOwnerSignUpDTO;
import BookMyShow.exceptions.ResourceNotExistException;
import BookMyShow.exceptions.UnAuthorized;
import BookMyShow.exceptions.UserDoesNotExistException;
import BookMyShow.models.ApplicationUser;
import BookMyShow.models.Hall;
import BookMyShow.models.Screen;
import BookMyShow.repository.ApplicationUserRepository;
import BookMyShow.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HallService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    RegularUserService regularUserService;
    @Autowired
    HallRepository hallRepository;

    @Autowired
    ScreenService screenService;
    public ApplicationUser signUp(HallOwnerSignUpDTO hallOwnerSignUpDTO){
        ApplicationUser hallOwner = new ApplicationUser();
        hallOwner.setName(hallOwnerSignUpDTO.getName());
        hallOwner.setEmail(hallOwnerSignUpDTO.getEmail());
        hallOwner.setPassword(hallOwnerSignUpDTO.getPassword());
        hallOwner.setType(hallOwnerSignUpDTO.getType().toString());
        hallOwner.setPhoneNumber(hallOwnerSignUpDTO.getPhoneNumber());
        hallOwner.setAge(hallOwnerSignUpDTO.getCompanyAge());
        List<Hall> halls = hallOwnerSignUpDTO.getHalls();
        applicationUserRepository.save(hallOwner);
        for(Hall hall : halls){
            hall.setOwner(hallOwner);
            hallRepository.save(hall);
        }
        return hallOwner;
    }

    public Hall getHallById(UUID id){
        return hallRepository.findById(id).orElse(null);
    }


    public void addScreens(AddScreenDTO addScreenDTO, String email){
        List<Screen> screens = addScreenDTO.getScreens();
        UUID hallId = addScreenDTO.getHallId();
        ApplicationUser user = regularUserService.getUserByEmail(email);
        if(user == null){
            throw new UserDoesNotExistException("User with this email does not exist.");
        }
        if(!user.getType().equals("HallOwner")){
            throw new UnAuthorized("User does not have access to perform this access");
        }
        Hall hall = getHallById(hallId);
        if(hall == null){
            throw new ResourceNotExistException(String.format("Hall id %s does not exist in System", hallId.toString())); // This hallId variable is the cariable we are passing in postman
        }
        if(hall.getOwner().getId() != user.getId()){
            throw new UnAuthorized("User does not own this hallId");
        }

        for(Screen screen : screens){
            screen.setHall(hall);
            screenService.registerScreen(screen);
        }
    }

    public void createShows(AddShowDTO addShowDTO){

    }

}