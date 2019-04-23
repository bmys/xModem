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
    }
}
