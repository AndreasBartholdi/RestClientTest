//*******************************************************************
// UserRestClient
//
// Description:
// User Rest client service - call Endpoints of a Rest-API
//
// ©power-manager-team -- 09.05.2023
//*******************************************************************


package application.service;

import application.models.User;
import application.constants.UserConstants;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static application.constants.UserConstants.*;

public class UserRestClient {

    private WebClient webClient;

    public UserRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    //http://localhost:1234/api/users/
    public List<User> retrieveAllUsers(){
        return webClient.get().uri(GET_ALL_USERS)
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }

    //http://localhost:1234/api/users/{id}
    public User retrieveById(long userId){
        return webClient.get().uri(GET_USERS_BY_ID,userId)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    //http://localhost:1234/api/users
    public User addNewUser(User user){
        return webClient.post().uri(ADD_USER)
                .syncBody(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    //http://localhost:1234/api/users/{id}
    public Boolean deleteUser(long userId){
        return webClient.delete().uri(GET_USERS_BY_ID, userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

}
