package objectBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ClientOtherInfoBox implements Serializable {

    @Id
    long id;

    private Integer clientID;
    private String generalInfosId;
    private String spouseName;
    private String spouseProf;
    private String spousePhone;
    private String children;
    private String decisionMaker;
    private String landownerEducation;
    private String hometown;
    private String agePersonality;
    private String keyFactor;
    private String marketKnowledge;
    private String managementEvaluation;
    private String createdAt;
    private String updatedAt;

    public ClientOtherInfoBox() {
    }

    public ClientOtherInfoBox(ClientProfileDM.OtherInfo data) {
        this.clientID = data.getId();
        this.generalInfosId = data.getGeneralInfosId();
        this.spouseName = data.getSpouseName();
        this.spouseProf = data.getSpouseProf();
        this.spousePhone = data.getSpousePhone();
        this.children = data.getChildren();
        this.decisionMaker = data.getDecisionMaker();
        this.landownerEducation = data.getLandownerEducation();
        this.hometown = data.getHometown();
        this.agePersonality = data.getAgePersonality();
        this.keyFactor = data.getKeyFactor();
        this.marketKnowledge = data.getMarketKnowledge();
        this.managementEvaluation = data.getManagementEvaluation();
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

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseProf() {
        return spouseProf;
    }

    public void setSpouseProf(String spouseProf) {
        this.spouseProf = spouseProf;
    }

    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(String decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public String getLandownerEducation() {
        return landownerEducation;
    }

    public void setLandownerEducation(String landownerEducation) {
        this.landownerEducation = landownerEducation;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getAgePersonality() {
        return agePersonality;
    }

    public void setAgePersonality(String agePersonality) {
        this.agePersonality = agePersonality;
    }

    public String getKeyFactor() {
        return keyFactor;
    }

    public void setKeyFactor(String keyFactor) {
        this.keyFactor = keyFactor;
    }

    public String getMarketKnowledge() {
        return marketKnowledge;
    }

    public void setMarketKnowledge(String marketKnowledge) {
        this.marketKnowledge = marketKnowledge;
    }

    public String getManagementEvaluation() {
        return managementEvaluation;
    }

    public void setManagementEvaluation(String managementEvaluation) {
        this.managementEvaluation = managementEvaluation;
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
