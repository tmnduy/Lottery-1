package com.example.lottery_final.Module;

import java.io.Serializable;

public class KQXS_MEGA645_Module implements Serializable {
   String GIAITHUONG;

    public KQXS_MEGA645_Module() {
    }

    public KQXS_MEGA645_Module(String GIAITHUONG) {
        this.GIAITHUONG = GIAITHUONG;
    }

    public String getGIAITHUONG() {
        return GIAITHUONG;
    }

    public void setGIAITHUONG(String GIAITHUONG) {
        this.GIAITHUONG = GIAITHUONG;
    }
}
