package BookMyShow.service;

import BookMyShow.dto.request.MovieOwnerSignUpDTO;
import BookMyShow.models.ApplicationUser;
import BookMyShow.models.Movie;
import BookMyShow.models.Ticket;
import BookMyShow.repository.ApplicationUserRepository;
import BookMyShow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Movie getMovieById(UUID id){
        return movieRepository.findById(id).orElse(null);
    }

    public int getTotalTicketCount(Movie movie){
        return movie.getTickets().size();
    }

    public int getBoxOfficeCollection(Movie movie){
        int totalIncome = 0;
        for(Ticket ticket : movie.getTickets()){
            totalIncome += ticket.getShow().getTicketPrice();
        }
        return totalIncome;
    }
}