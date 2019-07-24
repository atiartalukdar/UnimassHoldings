package info.atiar.unimassholdings.dataModel;

/**
 * Created by Atiar Talukdar on 7/9/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientProfileDM {

    @SerializedName("generalInfo")
    @Expose
    private GeneralInfo generalInfo;
    @SerializedName("reqInfo")
    @Expose
    private ReqInfo reqInfo;
    @SerializedName("otherInfo")
    @Expose
    private OtherInfo otherInfo;

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public ReqInfo getReqInfo() {
        return reqInfo;
    }

    public void setReqInfo(ReqInfo reqInfo) {
        this.reqInfo = reqInfo;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
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
    private Object refSourceName;
    @SerializedName("ref_source_mobile")
    @Expose
    private Object refSourceMobile;
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
    private String email;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("business_address")
    @Expose
    private String businessAddress;
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

    public Object getRefSourceName() {
        return refSourceName;
    }

    public void setRefSourceName(Object refSourceName) {
        this.refSourceName = refSourceName;
    }

    public Object getRefSourceMobile() {
        return refSourceMobile;
    }

    public void setRefSourceMobile(Object refSourceMobile) {
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

public class OtherInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("general_infos_id")
    @Expose
    private String generalInfosId;
    @SerializedName("spouse_name")
    @Expose
    private String spouseName;
    @SerializedName("spouse_prof")
    @Expose
    private String spouseProf;
    @SerializedName("spouse_phone")
    @Expose
    private String spousePhone;
    @SerializedName("children")
    @Expose
    private String children;
    @SerializedName("decision_maker")
    @Expose
    private String decisionMaker;
    @SerializedName("landowner_education")
    @Expose
    private String landownerEducation;
    @SerializedName("hometown")
    @Expose
    private String hometown;
    @SerializedName("age_personality")
    @Expose
    private String agePersonality;
    @SerializedName("key_factor")
    @Expose
    private String keyFactor;
    @SerializedName("market_knowledge")
    @Expose
    private String marketKnowledge;
    @SerializedName("management_evaluation")
    @Expose
    private String managementEvaluation;
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

public class ReqInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("general_infos_id")
    @Expose
    private String generalInfosId;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("landsize")
    @Expose
    private Object landsize;
    @SerializedName("road_existing")
    @Expose
    private String roadExisting;
    @SerializedName("road_proposed")
    @Expose
    private String roadProposed;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("face")
    @Expose
    private String face;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("ratio")
    @Expose
    private String ratio;
    @SerializedName("signing_money")
    @Expose
    private String signingMoney;
    @SerializedName("mouza")
    @Expose
    private String mouza;
    @SerializedName("dag_no")
    @Expose
    private String dagNo;
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

    public Object getLandsize() {
        return landsize;
    }

    public void setLandsize(Object landsize) {
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

}}