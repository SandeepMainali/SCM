package com.scm.Controllers;


import com.scm.Dto.UserForm;
import com.scm.Helpers.Message;
import com.scm.Helpers.MessageType;
import com.scm.Services.UserService;
import com.scm.entities.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/search")
    public String searchPage() {
        return "search";
    }

    @GetMapping("/navbar")
    public String navbarPage() {
        return "user/user_navbar";
    }

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/home")
    public String ShowhomePage() {
        return "home";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }

    @RequestMapping(value = "signup",method = RequestMethod.POST)
    public String Register(@Valid @ModelAttribute("userForm") UserForm userForm, HttpSession session, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {

            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://upload.wikimedia.org/wikipedia/commons/a/ac/Default_pfp.jpg");
        User savedUser = userService.saveUser(user);

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/register";
    }
}
