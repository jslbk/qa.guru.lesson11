package qa.guru.lesson.files.model;

import com.google.gson.annotations.SerializedName;

public class JsonModel {

    private String title;

    @SerializedName("ID")
    private Integer id;

    private JsonInnerModel data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JsonInnerModel getData() {
        return data;
    }

    public void setData(JsonInnerModel data) {
        this.data = data;
    }
}
