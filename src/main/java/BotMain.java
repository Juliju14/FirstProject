import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class BotMain extends TelegramLongPollingBot {
    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
try {
    telegramBotsApi.registerBot(new BotMain());
}
catch (TelegramApiRequestException e){
e.printStackTrace();
}
    }

    public void onUpdateReceived(Update update) {  //Метод для приема сообщений и обновлений
        if (update.hasMessage() && update.getMessage().hasText()) {
            String inputMessage = update.getMessage().getText();//текст входящего сообщения
            long chatId = update.getMessage().getChatId(); //идентификатор чата входящего сообщения
            if (update.getMessage().getText().equals("/start")) {


                SendMessage message = new SendMessage() // определяем сообщение в чат
                        .setChatId(chatId)
                        .setText("Вы нажали /start");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
                rowInline1.add(new InlineKeyboardButton().setText("Java").setCallbackData("update_msg_text"));
                List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                rowInline2.add(new InlineKeyboardButton().setText("HTML+CSS").setCallbackData("update_msg_text"));
                List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
                rowInline3.add(new InlineKeyboardButton().setText("ReactJS").setCallbackData("update_msg_text"));
                List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
                rowInline4.add(new InlineKeyboardButton().setText("NodeJS").setCallbackData("update_msg_text"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline1);
                rowsInline.add(rowInline2);
                rowsInline.add(rowInline3);
                rowsInline.add(rowInline4);

                // Add it to the message
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

    public String getBotUsername() {    //Метод для возвращения имени, указанный при регистрации
        return "firstInterviewBot"; //ВПИСАТЬ firstInterviewBot
    }

    public String getBotToken() {   //Метод для возвращения адреса, полученного при регистрации
        return "1235076828:AAHRQwKEXuYd_9Z2qTgyVehVLeOT5MwEoVw";
    }
}
