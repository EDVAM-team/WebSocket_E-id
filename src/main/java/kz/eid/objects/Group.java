package kz.eid.objects;

import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("id_group")
    private final int idGroup;
    @SerializedName("name")
    private final String name;

    public Group(int idGroup, String name) {
        this.idGroup = idGroup;
        this.name = name;
    }
}
