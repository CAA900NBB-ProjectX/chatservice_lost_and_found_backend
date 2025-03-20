package com.foundit.chat_api.chat;



import com.foundit.chat_api.common.BaseAuditingEntity;
import com.foundit.chat_api.message.Message;
import com.foundit.chat_api.message.MessageState;
import com.foundit.chat_api.message.MessageType;
import com.foundit.chat_api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
            query = "SELECT DISTINCT c FROM Chat c WHERE (c.sender = :senderId AND c.recipient = :recipientId AND c.itemId = :itemId) OR (c.sender = :recipientId AND c.recipient = :senderId AND c.itemId = :itemId) ORDER BY createdDate DESC"
)
@NamedQuery(name = ChatConstants.FIND_CHAT_LIST_BY_LOGIN_USER_ITEM_ID,
        query = "SELECT c FROM Chat c WHERE (c.recipient = :recipientId AND c.itemId = :itemId) ORDER BY createdDate DESC"
)
public class Chat extends BaseAuditingEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private int itemId;
    private Integer sender;
    private Integer recipient;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Message> messages;

}
