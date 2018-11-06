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

public class ScheduleStudent {
    @SerializedName("id_schedule")
    private int id_schedule;
    @SerializedName("d")
    private int d;
    @SerializedName("num")
    private int num;
    @SerializedName("schedule_subject")
    private SubjectSchedule subjectSchedule;
    @SerializedName("teacher")
    private Teacher teacher;

    public static class Teacher {
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

    public static class SubjectSchedule{
        @SerializedName("id")
        private int id;
        @SerializedName("t")
        private int t;
        @SerializedName("change")
        private int change;
        @SerializedName("list_subject")
        private SubjectList subjectList;
        @SerializedName("room")
        private Room room;

        public static class Room {
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

        public static class SubjectList{
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

        public void setId(int id) {
            this.id = id;
        }

        public void setSubjectList(SubjectList subjectList) {
            this.subjectList = subjectList;
        }

        public void setT(int t) {
            this.t = t;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public void setChange(int change) {
            this.change = change;
        }
    }

    public void setIdSchedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSubjectSchedule(SubjectSchedule subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
