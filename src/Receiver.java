import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Receiver {

    InputStream is = null;
    OutputStream os = null;
    private byte msgType = ControlChars.NAK;

    public Receiver(InputStream is, OutputStream os, Boolean CBC) {
        this.is = is;
        this.os = os;
        if (CBC) msgType = ControlChars.C;
    }

    void startTransmittion(){
        Calendar startCal = new GregorianCalendar();
        Calendar tryCal = new GregorianCalendar();
        tryCal.add(Calendar.SECOND, 10);

        Date startTime = startCal.getTime();

        startCal.add(Calendar.MINUTE, 1);
        Date endTime = startCal.getTime();


        int tries = 0;
        byte [] msg = new byte[256];

        try {
            os.write(msgType);
            tries++;

            while(new Date().compareTo(endTime) <= 0 || tries >= 10){

//                int count = is.read(msg, 0, msg.length);
                if(is.available() > 0){
//                    System.out.println(is.available());
                    is.read(msg, 0, 4);
                    System.out.println(msg);
                    break;

                }
                if(new Date().compareTo(tryCal.getTime()) >= 0){
                    System.out.println("elo");
                    os.write(msgType);
                    tries++;
                    tryCal.add(Calendar.SECOND, 10);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

  /*
    Xmodem jest inicjowany przez odbiornik. Oznacza to, że odbiorca wysyła początkowy znak NAK
    dla podstawowej wersji lub "C" dla wersji z sumą CRC do nadawcy wskazując, że jest gotowy do
    odbierania danych w odpowiednim trybie
    */

    /*
     Następnie nadawca wysyła pakiet 132 lub 133 bajtów,
    odbiorca sprawdza go i odpowiada znakiem ACK lub NAK, w którym to czasie nadawca wysyła
    następny pakiet lub ponownie wysyła poprzedni pakiet. Proces ten trwa tak długo, aż odbiornik
    odbierze znak EOT , na który odpowie znakiem AC
     */
}
