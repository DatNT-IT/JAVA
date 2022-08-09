package giongnoi;

import java.util.Arrays;
import java.util.List;

import marytts.signalproc.effects.JetPilotEffect;
import marytts.signalproc.effects.LpcWhisperiserEffect;
import marytts.signalproc.effects.RobotiserEffect;
import marytts.signalproc.effects.StadiumEffect;
import marytts.signalproc.effects.VocalTractLinearScalerEffect;
import marytts.signalproc.effects.VolumeEffect;

public class Main {


    public static void main(String[] args) {
        TextToSpeech tts = new TextToSpeech();


        //in những giọng nói đang có
        tts.getAvailableVoices().stream().forEach(voice -> System.out.println("Voice: " + voice));

        // giọng hiện tại
        tts.setVoice("cmu-rms-hsmm");


//Hiệu ứng Scaler tuyến tính của bài hát
        VocalTractLinearScalerEffect vocalTractLSE = new VocalTractLinearScalerEffect(); //russian drunk effect
        vocalTractLSE.setParams("amount:70");

        //Hiệu ứng JetPilot
        JetPilotEffect jetPilotEffect = new JetPilotEffect(); //epic fun!!!
        jetPilotEffect.setParams("amount:100");

        //Hiệu ứng Robotiser
        RobotiserEffect robotiserEffect = new RobotiserEffect();
        robotiserEffect.setParams("amount:50");

        //Hiệu ứng Stadium
        StadiumEffect stadiumEffect = new StadiumEffect();
        stadiumEffect.setParams("amount:150");

        //Hiệu ứng Lpc Whisperiser
        LpcWhisperiserEffect lpcWhisperiserEffect = new LpcWhisperiserEffect(); //creepy
        lpcWhisperiserEffect.setParams("amount:70");

        //Hiệu ứng âm lượng
        VolumeEffect volumeEffect = new VolumeEffect(); //be careful with this i almost got heart attack
        volumeEffect.setParams("amount:0");
        //có thể thêm nhiều hiệu ứng bằng cách sử dụng phương thức getFullEffectAsString(),vị dụ stadiumEffect
        tts.getMarytts().setAudioEffects(stadiumEffect.getFullEffectAsString());


        //
        tts.speak("what", 2.0f, true, true);
//                List<String> arrayList = Arrays.asList("tôi là đạt");
//
//
//        for (int i = 0; i < 5; i++)
//            arrayList.forEach(word -> tts.speak(word, 2.0f, false, true));
    }


}
