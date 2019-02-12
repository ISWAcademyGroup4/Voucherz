package com.iswAcademy.Voucherz.controller;


import com.iswAcademy.Voucherz.controller.service.Response;
import com.iswAcademy.Voucherz.controller.model.UpdateUserRequest;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.controller.model.UserRegistrationRequest;
import com.iswAcademy.Voucherz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/V1/users", consumes = "application/json", produces = "application/json")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createUser(@RequestBody UserRegistrationRequest request){
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanySize(request.getCompanySize());
        userService.createUser(user);
        return new Response("200","Successful");
    }


//    @RequestMapping(value = "/{email}", method = RequestMethod.PATCH)
//    @ResponseStatus(HttpStatus.OK)
//    public Response updateUser(@PathVariable("email") String email, @RequestBody @Validated final UpdateUserRequest request) {
////        User user = userService.findUserById(id);
//        User user = userService.updateUser(email, user)
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setCompanySize(request.getCompanySize());
//        userService.updateUser(email,user);
//        return  new Response ("200", "Updated");
//
//    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Response findUser(@PathVariable String email){
        User user = new User();
        userService.findUser(email);
        return new Response ("200", "found");
    }

}
