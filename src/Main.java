import org.omg.CORBA.portable.OutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Hello World!");
//        ByteArrayOutputStream collector = new ByteArrayOutputStream();
//
//        int length = collector.size();


            Receiver receiver = new Receiver(System.in, System.out, true);
            receiver.startTransmittion();

        //char a = '\u0001';

//        char a = 254;
//        char b = ControlChars.C;
//        byte c = (byte) a;
//        c = 0b1111111;
//        System.out.println(c );
//
//        byte val = (byte)2;
//
//        System.out.println();
        // 128 + (127 - k);
        // int = 127 - k; byte to int
        // int + 127 = k

//
//
//        byte c = (byte)0xFE;
//        System.out.println(c );

    }
}
