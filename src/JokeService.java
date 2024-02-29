public class JokeService implements Service {
    @Override
    public void execute(String username, ChatHistoryModel chatModel) throws Exception {
        new JokeFetcher().executeService(username, chatModel);
    }
}