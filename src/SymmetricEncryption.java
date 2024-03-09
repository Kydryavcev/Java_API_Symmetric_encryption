import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricEncryption
{
    private KeyGenerator keyGenerator;

    private Cipher cipher;

    /**
     * Конструктор класса {@code SymmetricEncryption}.
     *
     * <p>Конструктор инициализирует генератор ключей
     *  и объект-шифр для алгоритма шифрования <i>TripleDES</i>.</p>
     *
    * */
    public SymmetricEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        SecureRandom sr = new SecureRandom(); // создание датчика псевдослучайных чисел

        keyGenerator = KeyGenerator.getInstance("DESede");

        keyGenerator.init(sr); // связка генератора ключей с источником псевдослучайных чисел

        cipher = Cipher.getInstance("DESede");
    }

    /**
     * Создание нового секретного ключа.
     *
     * @return секретный ключ.
    **/
    public SecretKey generateKey()
    {
        return keyGenerator.generateKey();
    }

    /**
     * Зашифрование сообщения {@code message}.
     *
     * <p>Метод выполняет зашифрование сообщения {@code message} ключом {@code secretKey}.</p>
     *
     * @param message входное сообщение, которое будет подвержено зашифрованию.
     *
     * @param secretKey ключ шифрования.
     *
     * @return шифртекст в виде байтового массива.
     *
     * @throws InvalidKeyException если ключ {@code secretKey} по каким-либо причинам не подходит
     * для заданного при инициализации объекта класса {@code SymmetricEncryption} алгоритма шифрования.
     *
     * @throws IllegalStateException если шифр {@link SymmetricEncryption#cipher} находится в недоступном состоянии.
     *
     * @throws IllegalBlockSizeException если указан некорректный размер блока.
    **/
    public byte[] encrypt(byte[] message, SecretKey secretKey)
            throws InvalidKeyException, IllegalStateException, IllegalBlockSizeException
    {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] bytes = null;

        try
        {
            bytes = cipher.doFinal(message,0, message.length);
        }
        catch (BadPaddingException ex)
        {
            System.out.println("Невозможное возможно!");
            System.out.println(ex.getMessage());
        }


        return bytes;
    }

    /**
     * Расшифрование шифра {@code ciphertext}.
     *
     * <p>Метод выполняет расшифрование шифра {@code ciphertext} ключом {@code secretKey}.</p>
     *
     * @param ciphertextByteArray шифр в виде байтового массива, который будет подвержен расшифрованию.
     *
     * @param secretKey ключ шифрования
     *
     * @return Расшифрованное сообщение.
     *
     * @throws InvalidKeyException если ключ {@code secretKey} по каким-либо причинам не подходит
     * для заданного при инициализации объекта класса {@code SymmetricEncryption} алгоритма шифрования.
     *
     * @throws IllegalStateException если шифр {@link SymmetricEncryption#cipher} находится в недоступном состоянии.
     *
     * @throws IllegalBlockSizeException если указан некорректный размер блока.
     *
     * @throws BadPaddingException если, при расшифровании с отсечением дополнительных байтов, содержимое
     * дополнительных байтов не соответствует количеству байтов, подлежащих отсечению.
     **/
    public byte[] decrypt(byte[] ciphertextByteArray, SecretKey secretKey)
            throws InvalidKeyException, IllegalStateException, IllegalBlockSizeException, BadPaddingException
    {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytes = cipher.doFinal(ciphertextByteArray,0,ciphertextByteArray.length);

        return bytes;
    }
}
