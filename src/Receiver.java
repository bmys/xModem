import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Receiver {

    private InputStream is;
    private OutputStream os;
    private byte msgType = ControlChars.NAK;
    byte [] msg;

    Receiver(InputStream is, OutputStream os, Boolean CBC) {
        this.is = is;
        this.os = os;
        if (CBC) {
            this.msgType = ControlChars.C;
            this.msg = new byte[132];
        }
        else{
            this.msg = new byte[133];
        }
    }

    void startTransmittion(){
        Calendar startCal = new GregorianCalendar();
        Calendar tryCal = new GregorianCalendar();
        tryCal.add(Calendar.SECOND, 10);

        Date startTime = startCal.getTime();

        startCal.add(Calendar.MINUTE, 1);
        Date endTime = startCal.getTime();


        int tries = 0;

        try {
            os.write(msgType);
            tries++;

            while(new Date().compareTo(endTime) <= 0 || tries >= 10){

                if(is.available() > 0){
                    is.read(msg, 0, msg.length);

                    if(msg[0] == ControlChars.SOH){

                        System.out.println("SOH found!");

                        if(msg[1] == 0x01){

                            System.out.println("First packet number found!");

                            if(msg[2] == ~ msg[1]){

                                System.out.println("Inverse of first packet number found!");

                            }
                            else{
                                System.out.println("Inverse number error!");
                            }
                        }

                        else{ System.out.println("Number is not 0x01");}

                        System.out.println("Transmission started!");
                    }
                    else{
                        System.out.println("Error SOH is not first byte!");
                    }

                    break;

                }

                if(new Date().compareTo(tryCal.getTime()) >= 0){
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
