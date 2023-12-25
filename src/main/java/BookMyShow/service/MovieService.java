package BookMyShow.service;

import BookMyShow.dto.request.MovieOwnerSignUpDTO;
import BookMyShow.models.ApplicationUser;
import BookMyShow.models.Movie;
import BookMyShow.repository.ApplicationUserRepository;
import BookMyShow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    public ApplicationUser signUp(MovieOwnerSignUpDTO movieOwnerSignUpDTO){
        ApplicationUser movieOwners = new ApplicationUser();
        movieOwners.setName(movieOwnerSignUpDTO.getName());
        movieOwners.setEmail(movieOwnerSignUpDTO.getEmail());
        movieOwners.setPassword(movieOwnerSignUpDTO.getPassword());
        movieOwners.setType(movieOwnerSignUpDTO.getType().toString());
        movieOwners.setPhoneNumber(movieOwnerSignUpDTO.getPhoneNumber());
        movieOwners.setAge(movieOwnerSignUpDTO.getCompanyAge());
        List<Movie> movies = movieOwnerSignUpDTO.getMovies();
        applicationUserRepository.save(movieOwners);
        for(Movie movie : movies){
            movie.setOwner(movieOwners);
            movieRepository.save(movie);
        }
        return movieOwners;
    }
}