
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotMain {
    public static void main(String[] args)  {

        TelegramBotsApi botsApi = new TelegramBotsApi();
        ApiContextInitializer.init();
             try {
            botsApi.registerBot(new BotLogic());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}




