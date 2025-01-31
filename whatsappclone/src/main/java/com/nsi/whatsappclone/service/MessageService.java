package com.nsi.whatsappclone.service;

import com.nsi.whatsappclone.dto.MessageRequest;
import com.nsi.whatsappclone.dto.MessageResponse;
import com.nsi.whatsappclone.file.FileService;
import com.nsi.whatsappclone.model.Chat;
import com.nsi.whatsappclone.model.Message;
import com.nsi.whatsappclone.repository.ChatRepository;
import com.nsi.whatsappclone.repository.MessageRepository;
import com.nsi.whatsappclone.utils.MessageState;
import com.nsi.whatsappclone.utils.MessageType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 4:12 p.m.
 **/
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper;
    private final FileService fileService;

    public void saveMessage(MessageRequest messageRequest) {
        Chat chat = chatRepository.findById(messageRequest.getChatId()).orElseThrow(() -> new EntityNotFoundException("Chat not found"));

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setChat(chat);
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setType(messageRequest.getType());
        message.setState(MessageState.SENT);

        messageRepository.save(message);

        // todo notification
    }

    public List<MessageResponse> findChatMessages(String chatId) {
        return messageRepository.findMessagesByChatId(chatId).stream().map(messageMapper::toMessageResponse).toList();
    }

    @Transactional
    public void setMessageToSeen(String chatId, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found"));

        final String recipientId = getRecipientId(chat, authentication);

        messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);

        //todo notification
    }

    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found"));

        final String recipientId = getRecipientId(chat, authentication);
        final String senderId = getSenderId(chat, authentication);

        final String filePath = fileService.saveFile(file, senderId);

        Message message = new Message();
        message.setChat(chat);
        message.setSenderId(senderId);
        message.setReceiverId(recipientId);
        message.setType(MessageType.IMAGE);
        message.setState(MessageState.SENT);
        message.setMediaFilePath(filePath);

        messageRepository.save(message);

        // todo notification
    }

    private String getSenderId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }
}
