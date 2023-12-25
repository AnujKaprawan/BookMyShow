package BookMyShow.dto.request;


import BookMyShow.enums.UserType;
import BookMyShow.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieOwnerSignUpDTO {
    String name;
    String email;
    long phoneNumber;
    String password;
    UserType type;
    int companyAge;
    List<Movie> movies;
}