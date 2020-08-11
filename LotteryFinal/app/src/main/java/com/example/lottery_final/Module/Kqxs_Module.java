package com.example.lottery_final.Module;

import java.io.Serializable;

public class Kqxs_Module implements Serializable {
   String GIAITHUONG;

    public Kqxs_Module() {
    }

    public Kqxs_Module(String GIAITHUONG) {
        this.GIAITHUONG = GIAITHUONG;
    }

    public String getGIAITHUONG() {
        return GIAITHUONG;
    }

    public void setGIAITHUONG(String GIAITHUONG) {
        this.GIAITHUONG = GIAITHUONG;
    }
}
