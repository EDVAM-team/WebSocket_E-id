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

public class Mark {
    @SerializedName("id_mark")
    private final int idMark;
    @SerializedName("id_rating")
    private final int idRating;
    @SerializedName("n")
    private final int n;
    @SerializedName("mark")
    private final int mark;

    public Mark(int idMark, int idRating, int n, int mark) {
        this.idMark = idMark;
        this.idRating = idRating;
        this.n = n;
        this.mark = mark;
    }
}
