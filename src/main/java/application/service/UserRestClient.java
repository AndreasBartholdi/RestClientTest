//*******************************************************************
// UserRestClient
//
// Description:
// User Rest client service - call Endpoints of a Rest-API
//
// ©power-manager-team -- 09.05.2023
//*******************************************************************

package application.service;

import static application.constants.UserEndPoints.*;


import application.models.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Slf4j
public class UserRestClient{
    private WebClient webClient;

    public UserRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    //http://localhost:1234/api/users/
    public List<User> retrieveAllUsers(){
        try {
            return webClient
                    .get()
                    .uri(GET_ALL_USERS)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .collectList()
                    .block();
        } catch (Exception ex){
            throw ex;
        }
    }

    //http://localhost:1234/api/users/{id}
    public User retrieveById(long userId){
       try{
           return webClient
                .get()
                .uri(GET_USERS_BY_ID,userId)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        } catch (Exception ex){
            throw ex;
        }
    }

    //http://localhost:1234/api/users
    public User addNewUser(User user){
       try {
           return webClient
                .post()
                .uri(ADD_USER)
                .syncBody(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        } catch (Exception ex){
            throw ex;
        }
    }

    //http://localhost:1234/api/users/{id}
    public Boolean deleteUser(long userId) {
        try {
            return webClient
                    .delete()
                    .uri(GET_USERS_BY_ID, userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is {} and the response body is {}" , ex.getRawStatusCode(),ex.getResponseBodyAsString());
            log.error("WebclientResponseException in deleteUser", ex);
            throw ex;
        }catch(Exception ex){
            log.error("Exception in deleteUser");
            throw ex;
        }
    }
}
