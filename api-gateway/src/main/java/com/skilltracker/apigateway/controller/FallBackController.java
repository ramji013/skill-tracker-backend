package com.skilltracker.apigateway.controller;

import com.skilltracker.apigateway.bean.Error;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/oauthJwtFallBack")
    public Mono<Error> oauthJWTFallBack(){
        Error error = Error.builder().errorId("OAUTH_JWT_ERROR").message("Generating JWT token taking longer time").build();
        return Mono.just(error);
    }

    @RequestMapping("/registerUserFallBack")
    public Mono<Error> registerUserFallBack(){
        Error error = Error.builder().errorId("REGISTER_USER_ERROR").message("Register user taking longer time to respond").build();
        return Mono.just(error);
    }

    @RequestMapping("/createUserFallBack")
    public Mono<Error> addUserFallBack(){
        Error error = Error.builder().errorId("CREATE_USER_ERROR").message("create user service taking too longer to responsd or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/updateUserFallBack")
    public Mono<Error> updateUserFallBack(){
        Error error = Error.builder().errorId("UPDATE_USER_ERROR").message("update user service taking too longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/getAllUserFallBack")
    public Mono<Error> searchUserFallBack(){
        Error error = Error.builder().errorId("SEARCH_ALL_USER_ERROR").message("search all user service taking too longer to respond or it failed").build();
        return Mono.just(error);
    }


    @RequestMapping("/searchUserByNameFallBack")
    public Mono<Error> fetchAllUserFallBack(){
        Error error = Error.builder().errorId("SEARCH_USER_BY_USERNAME_ERROR").message("Search user by username taking too longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/addProfileFallBack")
    public Mono<Error> addProfileFallBack(){
        Error error = Error.builder().errorId("ADD_USER_PROFILE_ERROR").message("Add user profile taking longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/updateProfileFallBack")
    public Mono<Error> updateProfileFallBack(){
        Error error = Error.builder().errorId("UPDATE_USER_PROFILE_ERROR").message("Update user profile taking longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/restoreDBFallBack")
    public Mono<Error> restoreDBFallBack(){
        Error error = Error.builder().errorId("RESTORE_DB_ERROR").message("RestoreDB taking longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/getAllProfileFallBack")
    public Mono<Error> getAllProfileFallBack(){
        Error error = Error.builder().errorId("GET_ALL_PROFILE_ERROR").message("Fetching all profile taking longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/searchProfileByCriteriaFallBack")
    public Mono<Error> searchProfileByCriteriaFallBack(){
        Error error = Error.builder().errorId("SEARCH_PROFILE_BY_CRITERIA_ERROR").message("Searching profile by specific criteria taking longer to respond or it failed").build();
        return Mono.just(error);
    }

    @RequestMapping("/fetchAllSkillsFallBack")
    public Mono<Error> fetchAllSkillsFallBack(){
        Error error = Error.builder().errorId("FETCH_ALL_SKILLS_ERROR").message("Fetch all skills taking longer to respond or it failed").build();
        return Mono.just(error);
    }
}