package Audio;

import GUI.Parameter;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioFile {
    long musicCurrentTime;
    Clip music;

    AudioInputStream musicInput;

    public static String filePath = Parameter.music2;
    private static AudioFile instance = null;


    public AudioFile() {
        try {
            musicInput = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            music = AudioSystem.getClip();
            music.open(musicInput);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in Music");
        }
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static AudioFile getInstance() {
        if (instance == null) instance = new AudioFile();
        return instance;
    }

}



