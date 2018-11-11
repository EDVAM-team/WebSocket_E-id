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

package kz.osmium.main.util;

public class TokenGen {

    private static final int MAX_SIZE = 25;
    private static final String DIGITS = "0123456789";
    private static final char[] digit = DIGITS.toCharArray();
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final char[] lower = LOWERCASE.toCharArray();
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char[] upper = UPPERCASE.toCharArray();

    public static String generate(String login) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX_SIZE - login.length(); i++) {
            int chooseType = (int) (Math.random() * 3);
            switch (chooseType) {
                case 0: // lowercase
                    sb.append(lower[randomRange(0, 25)]);
                    break;
                case 1: // uppercase
                    sb.append(upper[randomRange(0, 25)]);
                    break;
                case 2: // digits
                    sb.append(digit[randomRange(0, 9)]);
                    break;
            }
        }
        return login + sb.toString();
    }

    private static int randomRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
