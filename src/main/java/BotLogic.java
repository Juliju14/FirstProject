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

public class BotLogic extends TelegramLongPollingBot {

    private TextMessage letters = new TextMessage();


    public void botLogic() throws TelegramApiRequestException {
        ApiContextInitializer.init();
               TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BotLogic() {
            });
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {  //Метод для приема сообщений и обновлений

        if (update.hasMessage() && update.getMessage().hasText()) {
            String inputMessage = update.getMessage().getText();    //текст входящего сообщения
            long chatId = update.getMessage().getChatId();  //идентификатор чата входящего сообщения
            if (inputMessage.equals(letters.getCHOOSE_START())) {
                SendMessage message = new SendMessage()     // определяет сообщение в чат
                        .setChatId(chatId)
                        .setText(letters.getTEXT_START());
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

                for (Language i : JsonRead.jsonRead()) {  //значения кнопок из json файла
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    rowInline.add(new InlineKeyboardButton().setText(i.getLanguage()).setCallbackData(i.getId()));
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
    public String getBotUsername() {    //Возвращает имя бота
        return letters.getBOT_NAME();
    }
    public String getBotToken() {   //Возвращает token бота
        return letters.getBOT_TOKEN();
    }

}







