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

package kz.osmium.oqu.objects.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Rating {
    @SerializedName("id_account")
    private int idAccount;
    @SerializedName("name_account")
    private String nameAccount;
    @SerializedName("rating")
    private ArrayList<Rating.RatingChild> rating;

    public static class RatingChild{
        @SerializedName("id")
        private int id;
        @SerializedName("num")
        private int num;
        @SerializedName("subject")
        private Subject subject;
        @SerializedName("mark")
        private ArrayList<Mark> mark;

        public static class Subject {
            @SerializedName("id")
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

        public static class Mark {
            @SerializedName("id")
            private final int id;
            @SerializedName("n")
            private final int n;
            @SerializedName("mark")
            private final int mark;

            public Mark(int id, int n, int mark) {
                this.id = id;
                this.n = n;
                this.mark = mark;
            }
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public void setMark(ArrayList<Mark> mark) {
            this.mark = mark;
        }
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public void setRating(ArrayList<Rating.RatingChild> rating) {
        this.rating = rating;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }
}