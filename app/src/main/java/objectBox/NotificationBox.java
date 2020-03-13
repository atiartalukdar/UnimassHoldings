package objectBox;

import androidx.room.Entity;

import java.io.Serializable;

import io.objectbox.annotation.Id;

@Entity
public class NotificationBox implements Serializable {

    @Id
    long id;

    String sentByName;
    String sentByID;
    String sentByUserRole;
    String clientID;
    String recordID;


    public NotificationBox() {
    }

    public NotificationBox(String sentByName, String sentByID, String sentByUserRole, String clientID, String recordID) {
        this.sentByName = sentByName;
        this.sentByID = sentByID;
        this.sentByUserRole = sentByUserRole;
        this.clientID = clientID;
        this.recordID = recordID;
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
}
