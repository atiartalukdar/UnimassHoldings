package info.atiar.unimassholdings.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddClientGeneralInfoDM {

    @SerializedName("client_id")
    @Expose
    private int client_id;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "AddClientGeneralInfoDM{" +
                "client_id=" + client_id +
                '}';
    }
}
