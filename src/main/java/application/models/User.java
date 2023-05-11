package application.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * @Data, @AllArgsConstructor, @NoArgsConstructor are an annotation form the lombok service.
 * This annotation will create all setter and getter and all constructors of this class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthdate;
    private String email;
}
