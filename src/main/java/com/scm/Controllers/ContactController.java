package com.scm.Controllers;

import com.scm.Dto.ContactForm;
import com.scm.Helpers.AppConstants;
import com.scm.Helpers.Helper;
import com.scm.Helpers.Message;
import com.scm.Helpers.MessageType;
import com.scm.Services.ContactService;
import com.scm.Services.ImageService;
import com.scm.Services.UserService;
import com.scm.entities.Contact;
import com.scm.entities.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;
//
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();

        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }


    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String saveContact(@Valid  @ModelAttribute ContactForm contactForm, Authentication authentication, BindingResult rBindingResult ,HttpSession session){
        System.out.println("Processing registration");

        System.out.println(contactForm);


        if (rBindingResult.hasErrors()) {
            session.setAttribute("message", Message.builder().content("Please Correct the following errors").type(MessageType.red).build());
            return "user/add_contact";
        }
        String username =Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);

        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagePublicId(filename);

        }

        contactService.save(contact);
        session.setAttribute("message", Message.builder().content("You have successfully added a new contact").type(MessageType.blue).build());
        return "redirect:/user/contacts/add";
    }
}
