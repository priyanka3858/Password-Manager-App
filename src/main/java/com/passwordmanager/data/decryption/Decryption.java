package com.passwordmanager.data.decryption;
import com.passwordmanager.data.encryption.AES;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decryption {

    public String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            AES aes = new AES();
            SecretKeySpec secretKey = aes.setKey(secret);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e);
        }
        return null;
    }

    public String decrypt(String encrypted) {
        return encrypted;
    }
}