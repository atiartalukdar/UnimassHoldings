package info.atiar.unimassholdings.dataModel;

/**
 * Created by Atiar Talukdar on 7/9/2019.
 */

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientBox {

    @SerializedName("generalInfo")
    @Expose
    private List<GeneralInfo> generalInfo = null;

    public List<GeneralInfo> getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(List<GeneralInfo> generalInfo) {
        this.generalInfo = generalInfo;
    }


    public class GeneralInfo {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("agent_id")
        @Expose
        private String agentId;
        @SerializedName("opening_date")
        @Expose
        private String openingDate;
        @SerializedName("ref_source")
        @Expose
        private String refSource;
        @SerializedName("ref_source_name")
        @Expose
        private String refSourceName;
        @SerializedName("ref_source_mobile")
        @Expose
        private String refSourceMobile;
        @SerializedName("prefix")
        @Expose
        private String prefix;
        @SerializedName("landowner_name")
        @Expose
        private String landownerName;
        @SerializedName("landowner_address")
        @Expose
        private String landownerAddress;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("email")
        @Expose
        private Object email;
        @SerializedName("profession")
        @Expose
        private Object profession;
        @SerializedName("designation")
        @Expose
        private Object designation;
        @SerializedName("business_address")
        @Expose
        private Object businessAddress;
        @SerializedName("progress_status")
        @Expose
        private String progressStatus;
        @SerializedName("f_meeting")
        @Expose
        private String fMeeting;
        @SerializedName("p_submit")
        @Expose
        private String pSubmit;
        @SerializedName("l_doc")
        @Expose
        private String lDoc;
        @SerializedName("d_doc")
        @Expose
        private String dDoc;
        @SerializedName("signing")
        @Expose
        private String signing;
        @SerializedName("archived")
        @Expose
        private String archived;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getProfession() {
            return profession;
        }

        public void setProfession(Object profession) {
            this.profession = profession;
        }

        public Object getDesignation() {
            return designation;
        }

        public void setDesignation(Object designation) {
            this.designation = designation;
        }

        public Object getBusinessAddress() {
            return businessAddress;
        }

        public void setBusinessAddress(Object businessAddress) {
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
}
