package security;

/**
 * Created by Administrator on 2017/4/7.
 */
public class HttpsHelper {


    //{"privateKey":"rsa(abcdef)","data":"des({"name":"xiaolongzuo","verifyCode":"md5(123456)"})"}
    public String sendJsonAndGetJson(String json){

        SecurityResponse securityResponse = new SecurityResponse();
        String privateKey = securityResponse.getPrivateKey();
        String data = securityResponse.getData();





        return null;
    }

}
