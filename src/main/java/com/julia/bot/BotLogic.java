package com.julia.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotLogic extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {  //Метод для приема сообщений и обновлений
        if (update.hasMessage() && update.getMessage().hasText()) {
            workMessage(update);
        } else if (update.hasCallbackQuery()) {
            workCallback(update);
        }
    }

    private void workMessage(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String inputMessage = update.getMessage().getText();    //текст входящего сообщения
            long chatId = update.getMessage().getChatId();  //идентификатор чата входящего сообщения
            if (inputMessage.equals(TextMessage.CHOOSE_START)) {
                SendMessage message = new SendMessage()     // определяет сообщение в чат
                        .setChatId(chatId)
                        .setText(TextMessage.TEXT_START);
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();//клавиатура
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

                for (NameList i : JsonRead.jsonRead(TextMessage.LANG_PATH)) {  //значения кнопок из json файла
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    rowInline.add(new InlineKeyboardButton().setText(i.getName()).setCallbackData(i.getCallback())
                    );
                    rowsInline.add(rowInline);
                }

                // Добавляем в сообщение
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message); // отправляем сообщение пользователю
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void workCallback(Update update) { //получение сообщения от пользователя
        Message callbackMessage = update.getCallbackQuery().getMessage();
        String callbackData = update.getCallbackQuery().getData();


        String[] res = callbackData.split("-");

        switch (res[0]) {
            case "language":
                getLanguageCategories(callbackMessage, res);
                break;
            case "sections":
                break;
        }

    }

    private void getLanguageCategories(Message callbackMessage, String[] res) {

        EditMessageText message = new EditMessageText()     // определяет сообщение в чат
                .setChatId(callbackMessage.getChatId())
                .setMessageId(callbackMessage.getMessageId())
                .setText(TextMessage.TEXT_SECT);

        EditMessageText messageUserCall = new EditMessageText()     // определяет сообщение в чат
                .setChatId(callbackMessage.getChatId())
                .setMessageId(callbackMessage.getMessageId())
                .setText("\uD83D\uDCA4");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();


        for (NameList i : JsonRead.jsonRead(TextMessage.SECT_PATH)) {  //значения кнопок из json файла
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton().setText(i.getName()).setCallbackData(i.getCallback())
            );
            rowsInline.add(rowInline);
        }

        // Добавляем в сообщение
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
            try {
                if (JsonRead.jsonRead(TextMessage.SECT_PATH).get(0).getCallback().contains(res[1])) {
                    execute(message); // отправляем сообщение пользователю
                } else {
                    execute(messageUserCall);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
        }

    }

    public String getBotUsername() {    //Возвращает имя бота
        return TextMessage.BOT_NAME;
    }

    public String getBotToken() {   //Возвращает token бота
        return TextMessage.BOT_TOKEN;
    }


}




