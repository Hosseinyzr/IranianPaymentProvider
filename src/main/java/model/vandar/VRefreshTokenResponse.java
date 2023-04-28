package model.vandar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VRefreshTokenResponse {

    /*{
    "token_type": "Bearer",
    "expires_in": 432000,
    "access_token": "bPJO9cJLGRqClDadEua7ztoCLC5E***********xYWI2NDVkZjBmIn02ZDRiMmY4YmU2MzNkNWI.eyJhdWQiOiIyKnGQQ73vXFd3dIJQ1EIInXU8-m-K7JC-jXl0TIgU9pDiCGljWPeEICTJQDrUUwHcOuXh3_hlxY096ui21s9EHdu8SiDIiwianRpIjoiYjl2teFcDbyCOlGPJOvGAYx9AY1hgCc5IyyGhWD-QaZoCAmdSfw94cWiebSEQegD6xDMN05za3xfXgRv5AlwGdvcCYsKGsDUbguByu6aP-1cAnX85DOyn_HNqrfsTEHQdf3Adf6aUs46MWDcGWd4bgo8S6EuUJ9ZACLtC4Yht_I5sWAyffikODRhNjc1MmVhZWUxMzI1NTZjNTNhMzIwMThiNGNkMDk3MDY4NzJkNGZkMzJmNDJjOGn18QEbia5mERj_68YN_vA9aAcjDVC92-GY-5wKcgR6VO7Xu-1m-plH8R15GBrE4vgx_uL-pM8e_D0fd6m6fws54JkqT7n7Ld-U9z4vZidkhndk1P3bnnLk1z50lNS8dlJzlOzfd6ZRTYhFqn2P5A7-Palt***************3CzA8Jnt7h3wNlLwThS_I_4BNkqBDEjPfZpiy-eyJ0Q1NzZkNGIyZjhiZTYzM2Q1YjFhYjY0NWRmMGYiLCJpYXQiOjE2MTE3NTQ5MjIsIm5iZiI6MTYxMTc1NDkyMiwiZXhwIjoxNjEyMTg2OTIyLCJzdWIiOiIyOSIsInNjb3BlcyI6WyIqIl19.zvR0Ipv-vSUigDdPVriqrXlPn3yNEUIIZDLfzG-xiKljif1jGS7SLncysuuXAiOiJKV1QiLCJhbGciOiJbPJO9cJLGRqClDadEua7ztoCLC5E",
    "refresh_token": "def50200c4d2462d2de167da1*******c97ef04222fa3265036e8b4e36deec1d8c084df7230e34162aea05078d4e5b641cad508676cfbb9b6bdb7e2f2b861eb0e0c5b*********9e6ededc76d94f10c9b3363be7"
}*/
    private String token_type;
    private Integer expires_in;
    private String access_token;
    private String refresh_token;

}
