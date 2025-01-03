package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.User;

import java.util.Set;

public class GroupReadOnlyDTO {

    private Long id;
    private String title;
    private User owner;
    private Set<User> members;
    private Set<Message> messages;
}
