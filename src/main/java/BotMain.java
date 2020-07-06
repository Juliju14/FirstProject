import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
//1235076828:AAHRQwKEXuYd_9Z2qTgyVehVLeOT5MwEoVw
//

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

    }

    public String getBotUsername() {    //Метод для возвращения имени, указанный при регистрации
        return "firstInterviewBot"; //ВПИСАТЬ firstInterviewBot
    }

    public String getBotToken() {   //Метод для возвращения адреса, полученного при регистрации
        return "1235076828:AAHRQwKEXuYd_9Z2qTgyVehVLeOT5MwEoVw";
    }
}
