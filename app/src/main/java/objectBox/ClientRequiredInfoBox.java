package objectBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ClientRequiredInfoBox {

    @Id
    long id;
    private Integer clientID;
    private String generalInfosId;
    private String area;
    private String landsize;
    private String roadExisting;
    private String roadProposed;
    private String width;
    private String length;
    private String face;
    private String unit;
    private String ratio;
    private String signingMoney;
    private String mouza;
    private String dagNo;
    private String createdAt;
    private String updatedAt;




    public ClientRequiredInfoBox() {
    }

    public ClientRequiredInfoBox(ClientProfileDM.ReqInfo data) {
        this.clientID = data.getId();
        this.generalInfosId = data.getGeneralInfosId();
        this.area = data.getArea();
        this.landsize = data.getLandsize();
        this.roadExisting = data.getRoadExisting();
        this.roadProposed = data.getRoadProposed();
        this.width = data.getWidth();
        this.length = data.getLength();
        this.face = data.getFace();
        this.unit = data.getUnit();
        this.ratio = data.getRatio();
        this.signingMoney = data.getSigningMoney();
        this.mouza = data.getMouza();
        this.dagNo = data.getDagNo();
        this.createdAt = data.getCreatedAt();
        this.updatedAt = data.getUpdatedAt();
    }


    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getGeneralInfosId() {
        return generalInfosId;
    }

    public void setGeneralInfosId(String generalInfosId) {
        this.generalInfosId = generalInfosId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandsize() {
        return landsize;
    }

    public void setLandsize(String landsize) {
        this.landsize = landsize;
    }

    public String getRoadExisting() {
        return roadExisting;
    }

    public void setRoadExisting(String roadExisting) {
        this.roadExisting = roadExisting;
    }

    public String getRoadProposed() {
        return roadProposed;
    }

    public void setRoadProposed(String roadProposed) {
        this.roadProposed = roadProposed;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getSigningMoney() {
        return signingMoney;
    }

    public void setSigningMoney(String signingMoney) {
        this.signingMoney = signingMoney;
    }

    public String getMouza() {
        return mouza;
    }

    public void setMouza(String mouza) {
        this.mouza = mouza;
    }

    public String getDagNo() {
        return dagNo;
    }

    public void setDagNo(String dagNo) {
        this.dagNo = dagNo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
