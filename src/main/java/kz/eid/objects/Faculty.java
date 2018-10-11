package kz.eid.objects;

import com.google.gson.annotations.SerializedName;

public class Faculty {
    @SerializedName("id_faculty")
    private final int idFaculty;
    @SerializedName("name")
    private final String name;

    public Faculty(int idFaculty, String name) {
        this.idFaculty = idFaculty;
        this.name = name;
    }
}
