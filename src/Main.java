import Core.PlayervsBot;
import Core.PlayerVsPlayer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        PlayervsBot playervsBot = PlayervsBot.getInstance();
//        playervsBot.actionGame();
        PlayerVsPlayer playerVsPlayer = PlayerVsPlayer.getInstance();
        playerVsPlayer.actionGame();
    }
}
