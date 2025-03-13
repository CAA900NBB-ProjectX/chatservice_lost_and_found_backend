package com.foundit.chat_api.chat;



import com.foundit.chat_api.common.BaseAuditingEntity;
import com.foundit.chat_api.message.Message;
import com.foundit.chat_api.message.MessageState;
import com.foundit.chat_api.message.MessageType;
import com.foundit.chat_api.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID,
            query = "SELECT DISTINCT c FROM Chat c WHERE c.sender = :senderId OR c.recipient = :senderId ORDER BY createdDate DESC"
)
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_LOGIN_USER_ITEM_ID,
            query = "SELECT DISTINCT c FROM Chat c WHERE (c.sender = :senderId AND c.recipient = :recipientId) OR (c.sender = :recipientId AND c.recipient = :senderId) ORDER BY createdDate DESC"
)
public class Chat extends BaseAuditingEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private int itemId;
    private Integer sender;
    private Integer recipient;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Message> messages;

}
