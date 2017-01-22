/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Raphaël Bussa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package rebus.header.view;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.Spanned;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by raphaelbussa on 14/01/17.
 */

public class Profile implements Parcelable {

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
    @IdValue
    private int id;
    private Uri avatarUri;
    private Drawable avatarDrawable;
    private Uri backgroundUri;
    private Drawable backgroundDrawable;
    private Spanned username;
    private Spanned email;

    private Profile(int id, Uri avatarUri, Drawable avatarDrawable, Uri backgroundUri, Drawable backgroundDrawable, Spanned username, Spanned email) {
        this.id = id;
        this.avatarUri = avatarUri;
        this.avatarDrawable = avatarDrawable;
        this.backgroundUri = backgroundUri;
        this.backgroundDrawable = backgroundDrawable;
        this.username = username;
        this.email = email;
    }

    private Profile(Parcel in) {
        id = in.readInt();
        avatarUri = (Uri) in.readValue(Uri.class.getClassLoader());
        avatarDrawable = (Drawable) in.readValue(Drawable.class.getClassLoader());
        backgroundUri = (Uri) in.readValue(Uri.class.getClassLoader());
        backgroundDrawable = (Drawable) in.readValue(Drawable.class.getClassLoader());
        username = (Spanned) in.readValue(Spanned.class.getClassLoader());
        email = (Spanned) in.readValue(Spanned.class.getClassLoader());
    }

    int getId() {
        return id;
    }

    Uri getAvatarUri() {
        return avatarUri;
    }

    Drawable getAvatarDrawable() {
        return avatarDrawable;
    }

    Uri getBackgroundUri() {
        return backgroundUri;
    }

    Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    Spanned getUsername() {
        return username;
    }

    Spanned getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", avatarUri=" + avatarUri +
                ", avatarDrawable=" + avatarDrawable +
                ", backgroundUri=" + backgroundUri +
                ", backgroundDrawable=" + backgroundDrawable +
                ", username=" + username +
                ", email=" + email +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeValue(avatarUri);
        dest.writeValue(avatarDrawable);
        dest.writeValue(backgroundUri);
        dest.writeValue(backgroundDrawable);
        dest.writeValue(username);
        dest.writeValue(email);
    }

    @RestrictTo(LIBRARY_GROUP)
    @IntRange(from = 2)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdValue {
    }

    public static class Builder {

        @IdValue
        private int id;
        private Uri avatarUri;
        private Drawable avatarDrawable;
        private Uri backgroundUri;
        private Drawable backgroundDrawable;
        private Spanned username;
        private Spanned email;

        public Builder() {
        }

        public Builder setId(@IdValue int id) {
            this.id = id;
            return this;
        }

        public Builder setAvatar(Uri avatar) {
            this.avatarUri = avatar;
            this.avatarDrawable = null;
            return this;
        }

        public Builder setAvatar(@NonNull String avatar) {
            this.avatarUri = Uri.parse(avatar);
            this.avatarDrawable = null;
            return this;
        }

        public Builder setAvatar(Drawable avatar) {
            this.avatarDrawable = avatar;
            this.avatarUri = null;
            return this;
        }

        public Builder setBackground(Uri background) {
            this.backgroundUri = background;
            this.backgroundDrawable = null;
            return this;
        }

        public Builder setBackground(@NonNull String background) {
            this.backgroundUri = Uri.parse(background);
            this.backgroundDrawable = null;
            return this;
        }

        public Builder setBackground(Drawable background) {
            this.backgroundDrawable = background;
            this.backgroundUri = null;
            return this;
        }

        public Builder setUsername(Spanned username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(Spanned email) {
            this.email = email;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = Utils.fromHtml(username);
            return this;
        }

        public Builder setEmail(String email) {
            this.email = Utils.fromHtml(email);
            return this;
        }

        public Profile build() {
            return new Profile(id, avatarUri, avatarDrawable, backgroundUri, backgroundDrawable, username, email);
        }

    }


}
