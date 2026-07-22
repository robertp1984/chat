package org.softwarecave.chat.config.ai;

public class ChatInputValidationException extends RuntimeException {
    public ChatInputValidationException(String message) {
        super(message);
    }

    public ChatInputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
