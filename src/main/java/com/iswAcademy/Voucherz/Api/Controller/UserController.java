package com.iswAcademy.Voucherz.Api.Controller;


import com.iswAcademy.Voucherz.Api.Controller.Model.LoginInRequest;
import com.iswAcademy.Voucherz.Api.Controller.Model.Response;
import com.iswAcademy.Voucherz.Dao.UserDao;
import com.iswAcademy.Voucherz.Model.UpdateUserRequest;
import com.iswAcademy.Voucherz.Model.User;
import com.iswAcademy.Voucherz.Api.Controller.Model.UserRegistrationRequest;
import com.iswAcademy.Voucherz.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users/", consumes = "application/json", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
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


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Response updateUser(@PathVariable( "id" ) long id, @RequestBody @Validated final UpdateUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanySize(request.getCompanySize());
        userService.updateUser(id,user);
        return  new Response ("200", "Updated");

    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Response findUser(@PathVariable String email){
        User user = new User();
        userService.findUser(email);
        return new Response ("200", "found");
    }





//    @PostMapping("/login")
//    public Response login(@Validated LoginInRequest request){
//        Response response = null;
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setPhoneNumber(request.getPassword());
//        return  new Response ("200" , "You are Logged in");
//    }







}
