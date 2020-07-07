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
    TextMessage letters = new TextMessage();

        public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();


        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BotMain());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }



    public String getBotUsername() {    //Возвращает имя бота
        TextMessage letters = new TextMessage();
        return letters.BOT_NAME;
    }

    public String getBotToken() {   //Возвращает token бота
        TextMessage letters = new TextMessage();
        return letters.BOT_TOKEN;
    }

    public void onUpdateReceived(Update update) {  //Метод для приема сообщений и обновлений

        if (update.hasMessage() && update.getMessage().hasText()) {
            String inputMessage = update.getMessage().getText();    //текст входящего сообщения
            long chatId = update.getMessage().getChatId();  //идентификатор чата входящего сообщения
            if (update.getMessage().getText().equals(letters.CHOOSE_START)) {


                SendMessage message = new SendMessage()     // определяет сообщение в чат
                        .setChatId(chatId)
                        .setText(letters.TEXT_START);



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

                // Цикл со считыванием из файла json для добавления кнопок
                rowsInline.add(rowInline1);
                rowsInline.add(rowInline2);
                rowsInline.add(rowInline3);
                rowsInline.add(rowInline4);

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


}
