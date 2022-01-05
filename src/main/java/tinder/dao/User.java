package tinder.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class User {
    private int id;
    private String name;
    private int age;
    private String email;
    private String password;
    private String urlPhoto;
    private Timestamp loginTimeStamp;
    private String loginDateTime;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, int age, String urlPhoto, Timestamp last_visit_date_time) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.urlPhoto = urlPhoto;
        this.loginTimeStamp = last_visit_date_time;
        setLoginDateTimeString_v1();  //запускаем расчет  loginTimeStamp  из loginTimeStamp
    }

    public User(int id, String email, String password, String name, int age, String urlPhoto) {
        this(id, email, password);
        this.name = name;
        this.age = age;
        this.urlPhoto = urlPhoto;
    }

    // версия1 преобразования java.sql.Timestamp -> LocalDateTime -> String
    public void setLoginDateTimeString_v1(){
        LocalDateTime ldt = loginTimeStamp.toInstant()
                .atZone(ZoneId.of("UTC+02:00"))
                .toLocalDateTime();
        this.loginDateTime = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy  hh:mm"));
    }

    // версия2 преобразования java.sql.Timestamp -> LocalDateTime -> String
    public void setLoginDateTimeString_v2(){
        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC + 02:00");
        TimeZone.setDefault(utcTimeZone);
        LocalDateTime ldt = loginTimeStamp.toLocalDateTime();
        this.loginDateTime = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm Z"));
    }

    public void setLoginTimeStamp(Timestamp loginTimeStamp) {
        this.loginTimeStamp = loginTimeStamp;
    }

    public Timestamp getLoginTimeStamp() {
        return loginTimeStamp;
    }

    public String getLoginDateTimeString(){
        return this.loginDateTime;
    }


    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                '}';
    }
}
