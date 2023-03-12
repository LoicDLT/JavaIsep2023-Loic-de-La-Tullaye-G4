package Music;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.FloatControl;
public class SoundEffectPlayer {

        private static Clip clip;

        public static void play(String filename) {
            try {
                File audioFile = new File(filename);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static void playloop(String filename) {
            try {
                File audioFile = new File(filename);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                });
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void stop() {
            if (clip != null) {
                clip.stop();
                clip.close();
            }
        }
        public static void setVolume(float volume) {
            if (clip != null) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            }
        }
    }

