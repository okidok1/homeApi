package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import utils.UserGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;




public class User {

    @NonNull
    String telegramId;
    @NonNull
    String first_name;
    @NonNull
    String username;
    String userGroup;


    public User(@NonNull String telegramId, @NonNull String first_name, @NonNull String username) {
        this.telegramId = telegramId;
        this.first_name = first_name;
        this.username = username;
        this.userGroup = null;
    }

   /* public static String calculateUserGroup(String telegramId){
        if()
    }*/
}
