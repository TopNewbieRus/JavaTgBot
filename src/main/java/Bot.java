import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }

    }

    public final Message sendMessage(SendMessage sendMessage) throws TelegramApiException{
        if (sendMessage == null){
            throw new TelegramApiException("Parameter sendMessege can not be null");
        } else {
            return (Message) this.sendApiMethod(sendMessage);
        }
    }

    @Override
    public String getBotUsername() {
        return "UrfuOOP_bot";
    }

    @Override
    public String getBotToken() {
        return "1345760678:AAHgDxL1EB7J7Q-t6cXesdkS9M7bJzzlKTQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, Bot_Info.Info());
                break;
            }
        }
    }
}
