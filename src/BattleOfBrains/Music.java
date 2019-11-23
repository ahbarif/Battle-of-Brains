package BattleOfBrains;
import java.io.*;
import sun.audio.*;

public class Music extends Thread
{
    String audioFile, operation="run";
    AudioStream theStream;
    public boolean state;
    Music(String audioFile)
    {
        this.audioFile=audioFile;
    }
    Music(String audioFile, String operation)
    {
        state = false;
        this.audioFile=audioFile;
        this.operation=operation;
    }
    @Override
    public void run()
    {
        if(operation.equals("run"))
        {
            openSong();
            playSong();
        
        }
        else if(operation.equals("stop"))closeSong();
    }
    void openSong()
    {
        try
        {
            theStream = new AudioStream(new FileInputStream(audioFile));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    void playSong()
    {
        try
        {
            theStream = new AudioStream(new FileInputStream(audioFile));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        AudioPlayer.player.start(theStream);
        state = true;
    }
    void closeSong()
    {
        AudioPlayer.player.stop(theStream);
        state = false;
        this.interrupt();
    }
}
