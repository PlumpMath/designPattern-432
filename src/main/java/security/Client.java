package security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/7.
 */
public class Client {

    public static void main(String[] args) throws Exception {


        String verifyCodeMing = "12345678";
        //{"name":"xiaolongzuo"}
        String verfyCode = MD5Utils.EncoderByMd5(verifyCodeMing);
        //{"name":"xiaolongzuo","verifyCode":"md5(123456)"}

        //明文 privateKey 客户端随机生成一个密钥
        String ming = "12345678";

        //客户端使用该密钥对传输数据进行des加密
        //待加密内容
        byte[] result = DesUtils.encrypt(verfyCode.getBytes(),ming);
        System.out.println("加密后："+new String(result));

        HashMap<String, Object> map = RSAUtils.getKeys();
        //随机生成公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
        //模
        String modulus = publicKey.getModulus().toString();
        //公钥指数
        String public_exponent = publicKey.getPublicExponent().toString();
        //使用模和指数生成公钥和私钥
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);
        //加密后的密文 用明文加密
        String mi = RSAUtils.encryptByPublicKey(ming, pubKey);
        System.out.println(mi);

        //私钥指数
        String private_exponent = privateKey.getPrivateExponent().toString();
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);
        //解密后的明文 用密钥解密
        ming = RSAUtils.decryptByPrivateKey(mi, priKey);
        System.out.println(ming);

        //直接将如上内容解密
        try {
            byte[] decryResult = DesUtils.decrypt(result, ming);
            System.out.println("解密后："+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //{"privateKey":"rsa(abcdef)","data":"des({"name":"xiaolongzuo","verifyCode":"md5(123456)"})"}





    }

}
