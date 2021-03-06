package info.atiar.unimassholdings.dataModel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Atiar Talukdar on 7/11/2019.
 */


public class CommunicationDM implements Serializable {

    @SerializedName("specificCommRecord")
    @Expose
    private List<SpecificCommRecord> specificCommRecord = null;

    public List<SpecificCommRecord> getSpecificCommRecord() {
        return specificCommRecord;
    }

    public void setSpecificCommRecord(List<SpecificCommRecord> specificCommRecord) {
        this.specificCommRecord = specificCommRecord;
    }

public static class SpecificCommRecord implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("general_infos_id")
    @Expose
    private String generalInfosId;
    @SerializedName("agent_id")
    @Expose
    private String agentId;
    @SerializedName("todo_lists_id")
    @Expose
    private String todoListsId;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("interaction_type")
    @Expose
    private String interactionType;
    @SerializedName("interaction_date")
    @Expose
    private String interactionDate;
    @SerializedName("lastaction_type")
    @Expose
    private String lastactionType;
    @SerializedName("lastaction_date")
    @Expose
    private String lastactionDate;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("special_note")
    @Expose
    private String specialNote;
    @SerializedName("action_type")
    @Expose
    private String actionType;
    @SerializedName("by_whom")
    @Expose
    private String byWhom;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("done")
    @Expose
    private String done;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public SpecificCommRecord() {
    }

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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getTodoListsId() {
        return todoListsId;
    }

    public void setTodoListsId(String todoListsId) {
        this.todoListsId = todoListsId;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public String getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(String interactionDate) {
        this.interactionDate = interactionDate;
    }

    public String getLastactionType() {
        return lastactionType;
    }

    public void setLastactionType(String lastactionType) {
        this.lastactionType = lastactionType;
    }

    public String getLastactionDate() {
        return lastactionDate;
    }

    public void setLastactionDate(String lastactionDate) {
        this.lastactionDate = lastactionDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getByWhom() {
        return byWhom;
    }

    public void setByWhom(String byWhom) {
        this.byWhom = byWhom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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