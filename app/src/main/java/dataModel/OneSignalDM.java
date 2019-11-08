package dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OneSignalDM {
    private String app_id;
    private List<String> included_segments = new ArrayList<>();
    private Data data;
    private Contents contents;


    public OneSignalDM() {
        this.app_id = "0b47f6db-c174-4970-8315-8e56c4614036";
        this.included_segments.add("All");
    }

    public OneSignalDM(Data data, Contents contents) {
        this.app_id = "0b47f6db-c174-4970-8315-8e56c4614036";
        this.included_segments.add("All");
        this.data = data;
        this.contents = contents;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public List<String> getIncluded_segments() {
        return included_segments;
    }

    public void setIncluded_segments(List<String> included_segments) {
        this.included_segments = included_segments;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }



    public class Data {
        private String foo;

        public Data(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

    }

    public class Contents {
        public Contents(String en) {
            this.en = en;
        }

        private String en;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

    }
}


