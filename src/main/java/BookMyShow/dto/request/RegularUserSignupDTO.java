package BookMyShow.dto.request;

import BookMyShow.enums.UserType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegularUserSignupDTO {
    String name;
    String email;
    long phoneNumber;
    String password;
    UserType type; // movieOwners, HallOwners and RegularUserServce
    int age;
}
