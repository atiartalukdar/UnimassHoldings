package objectBox;
import java.io.Serializable;
import info.atiar.unimassholdings.dataModel.ClientBox;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ClientGeneralInfoBox implements Serializable {

    @Id
    long id;

    private Integer clientID;
    private String agentId;
    private String openingDate;
    private String refSource;
    private String refSourceName;
    private String refSourceMobile;
    private String prefix;
    private String landownerName;
    private String landownerAddress;
    private String contactNo;
    private String email;
    private String profession;
    private String designation;
    private String businessAddress;
    private String progressStatus;
    private String fMeeting;
    private String pSubmit;
    private String lDoc;
    private String dDoc;
    private String signing;
    private String archived;
    private String createdAt;
    private String updatedAt;

    public ClientGeneralInfoBox() {
    }

    public ClientGeneralInfoBox(ClientBox.GeneralInfo data) {
        this.clientID = data.getId();
        this.agentId = data.getAgentId();
        this.openingDate = data.getOpeningDate();
        this.refSource = data.getRefSource();
        this.refSourceName = data.getRefSourceName();
        this.refSourceMobile = data.getRefSourceMobile();
        this.prefix = data.getPrefix();
        this.landownerName = data.getLandownerName();
        this.landownerAddress = data.getLandownerAddress();
        this.contactNo = data.getContactNo();
        this.email = data.getEmail();
        this.profession = data.getProfession();
        this.designation = data.getDesignation();
        this.businessAddress = data.getBusinessAddress();
        this.progressStatus = data.getProgressStatus();
        this.fMeeting = data.getFMeeting();
        this.pSubmit = data.getPSubmit();
        this.lDoc = data.getLDoc();
        this.dDoc = data.getDDoc();
        this.signing = data.getSigning();
        this.archived = data.getArchived();
        this.createdAt = data.getCreatedAt();
        this.updatedAt = data.getUpdatedAt();
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getRefSource() {
        return refSource;
    }

    public void setRefSource(String refSource) {
        this.refSource = refSource;
    }

    public String getRefSourceName() {
        return refSourceName;
    }

    public void setRefSourceName(String refSourceName) {
        this.refSourceName = refSourceName;
    }

    public String getRefSourceMobile() {
        return refSourceMobile;
    }

    public void setRefSourceMobile(String refSourceMobile) {
        this.refSourceMobile = refSourceMobile;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLandownerName() {
        return landownerName;
    }

    public void setLandownerName(String landownerName) {
        this.landownerName = landownerName;
    }

    public String getLandownerAddress() {
        return landownerAddress;
    }

    public void setLandownerAddress(String landownerAddress) {
        this.landownerAddress = landownerAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getFMeeting() {
        return fMeeting;
    }

    public void setFMeeting(String fMeeting) {
        this.fMeeting = fMeeting;
    }

    public String getPSubmit() {
        return pSubmit;
    }

    public void setPSubmit(String pSubmit) {
        this.pSubmit = pSubmit;
    }

    public String getLDoc() {
        return lDoc;
    }

    public void setLDoc(String lDoc) {
        this.lDoc = lDoc;
    }

    public String getDDoc() {
        return dDoc;
    }

    public void setDDoc(String dDoc) {
        this.dDoc = dDoc;
    }

    public String getSigning() {
        return signing;
    }

    public void setSigning(String signing) {
        this.signing = signing;
    }

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
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