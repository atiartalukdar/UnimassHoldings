package objectBox;


import java.io.Serializable;

import bp.Utils;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Unique;

@Entity
public class NotificationBox implements Serializable {

    @Id
    long id;

    @Unique
    String recordID;

    String sentByName;
    String sentByID;
    String sentByUserRole;
    String clientID;
    String title;
    String message;
    String isRead;
    String date;



    public NotificationBox() {
        date = Utils.getCurrentDateTime();
    }

    public NotificationBox(String sentByName, String sentByID, String sentByUserRole, String clientID, String recordID, String title, String message) {
        this.sentByName = sentByName;
        this.sentByID = sentByID;
        this.sentByUserRole = sentByUserRole;
        this.clientID = clientID;
        this.recordID = recordID;
        this.title = title;
        this.message = message;
        date = Utils.getCurrentDateTime();
    }

    public NotificationBox(String sentByName, String sentByID, String sentByUserRole, String clientID, String recordID, String title, String message, String isRead) {
        this.sentByName = sentByName;
        this.sentByID = sentByID;
        this.sentByUserRole = sentByUserRole;
        this.clientID = clientID;
        this.recordID = recordID;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        date = Utils.getCurrentDateTime();

    }

    public String getSentByName() {
        return sentByName;
    }

    public void setSentByName(String sentByName) {
        this.sentByName = sentByName;
    }

    public String getSentByID() {
        return sentByID;
    }

    public void setSentByID(String sentByID) {
        this.sentByID = sentByID;
    }

    public String getSentByUserRole() {
        return sentByUserRole;
    }

    public void setSentByUserRole(String sentByUserRole) {
        this.sentByUserRole = sentByUserRole;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getTitle() {
        return title+"";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message+"";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getDate() {
        return date+"";
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotificationBox{" +
                "sentByName='" + sentByName + '\'' +
                ", sentByID='" + sentByID + '\'' +
                ", sentByUserRole='" + sentByUserRole + '\'' +
                ", clientID='" + clientID + '\'' +
                ", recordID='" + recordID + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
