package com.gestor.cobranca.configuracao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

    public static String md5(String senha){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1,messageDigest.digest(senha.getBytes()));
            return hash.toString(16);
        }catch (Exception e){
            return "";
        }
    }

    public Date dataCadastro(){
        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.getTime();
        //return new Date();
    }
}
