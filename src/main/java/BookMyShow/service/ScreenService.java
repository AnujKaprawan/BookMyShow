package BookMyShow.service;

import BookMyShow.models.Screen;
import BookMyShow.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreenService {
    @Autowired
    ScreenRepository screenRepository;

    public void registerScreen(Screen screen){
        screenRepository.save(screen);
    }

}