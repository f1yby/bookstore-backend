package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CheckOutData {
    String username;
    String password;
    ArrayList<Integer> oiid;

    public CheckOutData() {

    }

    public CheckOutData(String username, String password, ArrayList<Integer> oiid) {
        this.username = username;
        this.password = password;
        this.oiid = oiid;
    }
}
