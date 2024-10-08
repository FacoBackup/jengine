package com.pine.messaging;

import com.pine.SerializableRepository;
import com.pine.injection.PBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Intended for grouping messages system-wide
 */
@PBean
public class MessageRepository implements SerializableRepository {
    public static final int MAX_MESSAGES_HISTORY = 100;
    public static final int MAX_MESSAGES = 4;
    public static final long MESSAGE_DURATION = 3000;
    public final Message[] messages = new Message[MAX_MESSAGES];
    public final List<Message> messagesHistory = new LinkedList<>();

    public void pushMessage(String message, MessageSeverity severity) {
        pushMessage(new Message(message, severity));
    }

    public void pushMessage(Message message) {
        if (message == null) {
            return;
        }
        if (messagesHistory.size() >= MAX_MESSAGES_HISTORY) {
            messagesHistory.removeFirst();
        }

        messagesHistory.add(message);
        if (messages[0] == null) {
            messages[0] = message;
        } else if (messages[1] == null) {
            messages[1] = message;
        } else if (messages[2] == null) {
            messages[2] = message;
        } else {
            messages[3] = message;
        }
    }

    public List<Message> getMessagesHistory() {
        return messagesHistory;
    }

    public void clearMessagesHistory() {
        messagesHistory.clear();
    }

    public Message[] getMessages() {
        return messages;
    }
}
