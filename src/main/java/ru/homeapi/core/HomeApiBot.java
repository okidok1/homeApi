package ru.homeapi.core;

import lombok.SneakyThrows;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.UtilClass;

import java.util.Optional;

public class HomeApiBot extends TelegramLongPollingBot {

    @Autowired
    private UtilClass utilClass;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage() != null) {
            Message message = update.getMessage();

            /*utilClass.logMessage(message);*/ //TODO: Надо пофиксить, утил класс - нулл, не понимаю как заинжектить
            handleMessage(message);
        }
    }

    @SneakyThrows
    private void handleMessage(Message message) {
        //handling commands
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/wifi":
                        execute(SendMessage.builder()
                                .text("D5UTZPMD")
                                .chatId(message.getChatId())
                                .build());
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "@HomeApiBot_bot";
    }

    @Override
    public String getBotToken() {
        return "5716286799:AAFQJoKTnaDX40h-ZqUdCIUYz4inH_TGz0M";
    }


    @SneakyThrows
    public static void main(String[] args) {
        HomeApiBot bot = new HomeApiBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}
