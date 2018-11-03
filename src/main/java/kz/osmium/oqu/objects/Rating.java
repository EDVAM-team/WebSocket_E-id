/*
 * Copyright 2018 Osmium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kz.osmium.oqu.objects;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Rating {
    @SerializedName("id_rating")
    private int idRating;
    @SerializedName("id_subject")
    private int idSubject;
    @SerializedName("id_student")
    private int idStudent;
    @SerializedName("rating")
    private String rating;
    @SerializedName("subject")
    private String subject;
    @SerializedName("student")
    private String student;
    @SerializedName("num")
    private int num;
    @SerializedName("mark")
    private ArrayList<Mark> mark;

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setMark(ArrayList<Mark> mark) {
        this.mark = mark;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
