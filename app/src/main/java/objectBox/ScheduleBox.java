package objectBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import info.atiar.unimassholdings.dataModel.ScheduleDM;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Atiar Talukdar on 7/10/2019.
 */
@Entity
public class ScheduleBox {
    @Id
    Long id;

    private Integer scheduleID;
    private String generalInfosId;
    private String agentId;
    private String todoListsId;
    private String lName;
    private String interactionType;
    private String interactionDate;
    private String lastactionType;
    private String lastactionDate;
    private String details;
    private String specialNote;
    private String actionType;
    private String byWhom;
    private String date;
    private String location;
    private String done;
    private String remarks;
    private String createdAt;
    private String updatedAt;

    public ScheduleBox() {
    }

    public ScheduleBox(ScheduleDM.Schedule data) {
        this.scheduleID = data.getId();
        this.generalInfosId = data.getGeneralInfosId();
        this.agentId = data.getAgentId();
        this.todoListsId = data.getTodoListsId();
        this.lName = data.getLName();
        this.interactionType = data.getInteractionType();
        this.interactionDate = data.getInteractionDate();
        this.lastactionType = data.getLastactionType();
        this.lastactionDate = data.getLastactionDate();
        this.details = data.getDetails();
        this.specialNote = data.getSpecialNote();
        this.actionType = data.getActionType();
        this.byWhom = data.getByWhom();
        this.date = data.getDate();
        this.location = data.getLocation();
        this.done = data.getDone();
        this.remarks = data.getRemarks();
        this.createdAt = data.getCreatedAt();
        this.updatedAt = data.getUpdatedAt();
    }

    public Integer getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
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
}
