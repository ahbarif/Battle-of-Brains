package BattleOfBrains;

public class Audio_Player {

    public static Music menuMusic = new Music("Resources\\gg.wav", "run");
    
    public static boolean sound = true;

    Audio_Player() {

        startMusic();
    }

    public void startMusic() {
        menuMusic.start();
    }
}
