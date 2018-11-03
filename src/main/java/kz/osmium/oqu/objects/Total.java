package kz.osmium.oqu.objects;

import com.google.gson.annotations.SerializedName;

public class Total {
    @SerializedName("id")
    private int id;
    @SerializedName("subject")
    private Subject subject;
    @SerializedName("course")
    private int course;

    public static class Subject{
        @SerializedName("id_total")
        private int id;
        @SerializedName("name")
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
