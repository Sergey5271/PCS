package com.pcs.controller;

import com.pcs.model.Message;
import com.pcs.repository.MessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Controller
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/listMess")
    public String home(Model model) {
        model.addAttribute("msgs", messageRepository.findAll());
        return "message/messages";
    }

    @PostMapping("/messages")
    public String saveMessage(Message message) {
        messageRepository.save(message);
        return "redirect:/listMess";
    }


    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

}
