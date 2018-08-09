package com.pay.binaminbao.beans;

public class CardInfo {
    
    private String iCCardData;  //IC卡55域数据
    private String ICCardSeqNumber; // IC卡序列号
    private String track2Data; // 二磁道信息
    private String track3Data; // 三磁道信息
    private String POSentryModeCode;
    private String carrierAppTp = "3"; 
    private String carrierTp = "5";

    public String getiCCardData() {
        return iCCardData;
    }

    public void setiCCardData(String iCCardData) {
        this.iCCardData = iCCardData;
    }

    public String getICCardSeqNumber() {
        return ICCardSeqNumber;
    }

    public void setICCardSeqNumber(String ICCardSeqNumber) {
        this.ICCardSeqNumber = ICCardSeqNumber;
    }

    public String getTrack2Data() {
        return track2Data;
    }

    public void setTrack2Data(String track2Data) {
        this.track2Data = track2Data;
    }

    public String getTrack3Data() {
        return track3Data;
    }

    public void setTrack3Data(String track3Data) {
        this.track3Data = track3Data;
    }

    public String getPOSentryModeCode() {
        return POSentryModeCode;
    }

    public void setPOSentryModeCode(String POSentryModeCode) {
        this.POSentryModeCode = POSentryModeCode;
    }

    public String getCarrierAppTp() {
        return carrierAppTp;
    }

    public void setCarrierAppTp(String carrierAppTp) {
        this.carrierAppTp = carrierAppTp;
    }

    public String getCarrierTp() {
        return carrierTp;
    }

    public void setCarrierTp(String carrierTp) {
        this.carrierTp = carrierTp;
    }

    @Override
    public String toString() {
        return "CardInfo{" +
                "iCCardData='" + iCCardData + '\'' +
                ", ICCardSeqNumber='" + ICCardSeqNumber + '\'' +
                ", track2Data='" + track2Data + '\'' +
                ", track3Data='" + track3Data + '\'' +
                ", POSentryModeCode='" + POSentryModeCode + '\'' +
                ", carrierAppTp='" + carrierAppTp + '\'' +
                ", carrierTp='" + carrierTp + '\'' +
                '}';
    }
}
