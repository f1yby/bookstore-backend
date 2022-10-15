package com.example.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutResult {
    String username;
    String result;

    public CheckOutResult() {

    }

    public CheckOutResult(String username, String result) {
        this.username = username;
        this.result = result;
    }
}