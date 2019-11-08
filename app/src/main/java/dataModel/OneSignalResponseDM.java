package dataModel;

public class OneSignalResponseDM {
    public String id;
    public String recipients;
    public String external_id;

    public OneSignalResponseDM() {
    }

    public OneSignalResponseDM(String id, String recipients, String external_id) {
        this.id = id;
        this.recipients = recipients;
        this.external_id = external_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    @Override
    public String toString() {
        return "OneSignalResponseDM{" +
                "id='" + id + '\'' +
                ", recipients='" + recipients + '\'' +
                ", external_id='" + external_id + '\'' +
                '}';
    }
}
