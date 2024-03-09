import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        try
        {
            SymmetricEncryption se = new SymmetricEncryption();

            SecretKey sk = se.generateKey();

            System.out.print("Введите сообщение для шифрования: ");

            String message = sc.nextLine();

            byte[] cipher  = se.encrypt(message.getBytes(), sk);

            System.out.println("Сообщение после шифрования: " + new String(cipher));

            byte[] decryptMessage = se.decrypt(cipher, sk);

            System.out.println("Сообщение после расшифрования: " + new String(decryptMessage));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
        }

    }
}
