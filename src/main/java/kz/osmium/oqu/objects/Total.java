package kz.osmium.oqu.objects;

import com.google.gson.annotations.SerializedName;

public class Total {
    @SerializedName("id_total")
    private int idTotal;
    @SerializedName("course")
    private int course;
    @SerializedName("subject")
    private Subject subject;

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

    public void setIdTotal(int idTotal) {
        this.idTotal = idTotal;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
