package com.foundit.chat_api.user;


import com.foundit.chat_api.chat.Chat;
import com.foundit.chat_api.common.BaseAuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String email;
    private LocalDateTime lastSeen;
    private List<Chat> chatsAsSender;
    private List<Chat> chatsAsRecipient;
}
