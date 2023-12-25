package BookMyShow.dto.request;

import BookMyShow.enums.UserType;
import BookMyShow.models.Hall;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HallOwnerSignUpDTO {
    String name;
    String email;
    long phoneNumber;
    String password;
    UserType type;
    List<Hall> halls;
    int companyAge;
}