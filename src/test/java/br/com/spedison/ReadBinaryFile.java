package br.com.spedison;


import br.com.spedison.utils.BytesUtils;
import br.com.spedison.vo.EventByteData;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.Duration;

public class ReadBinaryFile {

    @Test
    public void readBinaryFile() {

        int contador = 10;

        try (InputStream is = new FileInputStream("/dev/input/event19")) {

            byte[] buffer = new byte[24];

            while (contador > 0) {

                int readed;
                while ((readed = is.read(buffer)) == buffer.length) {

                    EventByteData ev = new EventByteData(buffer);
                    Double leituraSegundosTotalReverso = ev.getTime();
                    Double leituraSistema = System.currentTimeMillis()/1E3D;
                    System.out.printf("Diferença da leitura Reverso:: %2.20f %n",Math.abs(leituraSistema-leituraSegundosTotalReverso));

                    //String line = BytesUtils.bytesToHex(buffer);
                    //System.out.println("Line :: " + line + " Bytes lidos :: " + readed);

                    //System.out.println();


                    int code = ev.getCode();
                    int type = ev.getType();
                    int value = ev.getValue();
                    //Double atualEvento = ev.getTime(); //((double) ev.getTimeSeconds()) + (ev.getTimeMicroSeconds() / 1E6D);
                    //Double atualSystema = System.currentTimeMillis() / 1E3D;
                    //System.out.printf("A diferença é %2.20f %n", atualEvento - atualSystema);



                    /*System.arraycopy(time, 0, time1, 0, time1.length);
                    System.arraycopy(time, 8, time2, 0, time2.length);
                    Long a = Conversion.byteArrayToLong(time2,0,0,0,8);
                    System.out.printf("a = %d%n",a);
                    long _time1 = ByteBuffer.wrap(time1).getLong();
                    long _time2 = ByteBuffer.wrap(time2).getLong();
                    start += time.length;

                    System.arraycopy(buffer, start, type, 0, type.length);
                    start += type.length;
                    System.arraycopy(buffer, start, code, 0, code.length);
                    start += code.length;
                    System.arraycopy(buffer, start, value, 0, value.length);

                    BigInteger timeCurrent = new BigInteger(time);
                    System.out.println("Diferença = " + (timeCurrent.subtract(biAnterior)).abs());
                    biAnterior = timeCurrent;

                    int _code = ByteBuffer.wrap(code).getInt();
                    int _type = ByteBuffer.wrap(type).getInt();
                    int _value = ByteBuffer.wrap(value).getInt();*/

                    System.out.printf(
                            "\tCode = %d ; Type = %d ; Value = %s%n",
                            code, type, value);

                }

                contador--;
                try {
                    Thread.sleep(Duration.ofSeconds(5));
                } catch (InterruptedException ie) {
                }
            }
            is.close();
        } catch (IOException e) {
            System.err.println("Erro while reading binary file");
            e.printStackTrace();
        }

    }
}
