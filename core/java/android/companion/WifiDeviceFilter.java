/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.companion;

import static android.companion.BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.net.MacAddress;
import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.internal.util.DataClass;
import com.android.internal.util.Parcelling;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A filter for Wifi devices
 *
 * @see ScanFilter
 */
@DataClass(
        genParcelable = true,
        genAidl = false,
        genBuilder = true,
        genEqualsHashCode = true,
        genHiddenGetters = true)
public final class WifiDeviceFilter implements DeviceFilter<ScanResult> {

    /**
     * If set, only devices with {@link BluetoothDevice#getName name} matching the given regular
     * expression will be shown
     */
    @DataClass.ParcelWith(Parcelling.BuiltIn.ForPattern.class)
    private @Nullable Pattern mNamePattern = null;

    /**
     * If set, only devices with BSSID matching the given one will be shown
     */
    private @Nullable MacAddress mBssid = null;

    /**
     * If set, only bits at positions set in this mask, will be compared to the given
     * {@link Builder#setBssid BSSID} filter.
     */
    private @NonNull MacAddress mBssidMask = MacAddress.BROADCAST_ADDRESS;

    /** @hide */
    @Override
    public boolean matches(ScanResult device) {
        return BluetoothDeviceFilterUtils.matchesName(getNamePattern(), device)
                && (mBssid == null
                        || MacAddress.fromString(device.BSSID).matches(mBssid, mBssidMask));
    }

    /** @hide */
    @Override
    public String getDeviceDisplayName(ScanResult device) {
        return getDeviceDisplayNameInternal(device);
    }

    /** @hide */
    @Override
    public int getMediumType() {
        return MEDIUM_TYPE_WIFI;
    }



    // Code below generated by codegen v1.0.11.
    //
    // DO NOT MODIFY!
    // CHECKSTYLE:OFF Generated code
    //
    // To regenerate run:
    // $ codegen $ANDROID_BUILD_TOP/frameworks/base/core/java/android/companion/WifiDeviceFilter.java
    //
    // To exclude the generated code from IntelliJ auto-formatting enable (one-time):
    //   Settings > Editor > Code Style > Formatter Control
    //@formatter:off


    @DataClass.Generated.Member
    /* package-private */ WifiDeviceFilter(
            @Nullable Pattern namePattern,
            @Nullable MacAddress bssid,
            @NonNull MacAddress bssidMask) {
        this.mNamePattern = namePattern;
        this.mBssid = bssid;
        this.mBssidMask = bssidMask;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mBssidMask);

        // onConstructed(); // You can define this method to get a callback
    }

    /**
     * If set, only devices with {@link BluetoothDevice#getName name} matching the given regular
     * expression will be shown
     *
     * @hide
     */
    @DataClass.Generated.Member
    public @Nullable Pattern getNamePattern() {
        return mNamePattern;
    }

    /**
     * If set, only devices with BSSID matching the given one will be shown
     *
     * @hide
     */
    @DataClass.Generated.Member
    public @Nullable MacAddress getBssid() {
        return mBssid;
    }

    /**
     * If set, only bits at positions set in this mask, will be compared to the given
     * {@link Builder#setBssid BSSID} filter.
     *
     * @hide
     */
    @DataClass.Generated.Member
    public @NonNull MacAddress getBssidMask() {
        return mBssidMask;
    }

    @Override
    @DataClass.Generated.Member
    public boolean equals(@Nullable Object o) {
        // You can override field equality logic by defining either of the methods like:
        // boolean fieldNameEquals(WifiDeviceFilter other) { ... }
        // boolean fieldNameEquals(FieldType otherValue) { ... }

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked")
        WifiDeviceFilter that = (WifiDeviceFilter) o;
        //noinspection PointlessBooleanExpression
        return true
                && Objects.equals(mNamePattern, that.mNamePattern)
                && Objects.equals(mBssid, that.mBssid)
                && Objects.equals(mBssidMask, that.mBssidMask);
    }

    @Override
    @DataClass.Generated.Member
    public int hashCode() {
        // You can override field hashCode logic by defining methods like:
        // int fieldNameHashCode() { ... }

        int _hash = 1;
        _hash = 31 * _hash + Objects.hashCode(mNamePattern);
        _hash = 31 * _hash + Objects.hashCode(mBssid);
        _hash = 31 * _hash + Objects.hashCode(mBssidMask);
        return _hash;
    }

    @DataClass.Generated.Member
    static Parcelling<Pattern> sParcellingForNamePattern =
            Parcelling.Cache.get(
                    Parcelling.BuiltIn.ForPattern.class);
    static {
        if (sParcellingForNamePattern == null) {
            sParcellingForNamePattern = Parcelling.Cache.put(
                    new Parcelling.BuiltIn.ForPattern());
        }
    }

    @Override
    @DataClass.Generated.Member
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // You can override field parcelling by defining methods like:
        // void parcelFieldName(Parcel dest, int flags) { ... }

        byte flg = 0;
        if (mNamePattern != null) flg |= 0x1;
        if (mBssid != null) flg |= 0x2;
        dest.writeByte(flg);
        sParcellingForNamePattern.parcel(mNamePattern, dest, flags);
        if (mBssid != null) dest.writeTypedObject(mBssid, flags);
        dest.writeTypedObject(mBssidMask, flags);
    }

    @Override
    @DataClass.Generated.Member
    public int describeContents() { return 0; }

    /** @hide */
    @SuppressWarnings({"unchecked", "RedundantCast"})
    @DataClass.Generated.Member
    /* package-private */ WifiDeviceFilter(@NonNull Parcel in) {
        // You can override field unparcelling by defining methods like:
        // static FieldType unparcelFieldName(Parcel in) { ... }

        byte flg = in.readByte();
        Pattern namePattern = sParcellingForNamePattern.unparcel(in);
        MacAddress bssid = (flg & 0x2) == 0 ? null : (MacAddress) in.readTypedObject(MacAddress.CREATOR);
        MacAddress bssidMask = (MacAddress) in.readTypedObject(MacAddress.CREATOR);

        this.mNamePattern = namePattern;
        this.mBssid = bssid;
        this.mBssidMask = bssidMask;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mBssidMask);

        // onConstructed(); // You can define this method to get a callback
    }

    @DataClass.Generated.Member
    public static final @NonNull Parcelable.Creator<WifiDeviceFilter> CREATOR
            = new Parcelable.Creator<WifiDeviceFilter>() {
        @Override
        public WifiDeviceFilter[] newArray(int size) {
            return new WifiDeviceFilter[size];
        }

        @Override
        public WifiDeviceFilter createFromParcel(@NonNull Parcel in) {
            return new WifiDeviceFilter(in);
        }
    };

    /**
     * A builder for {@link WifiDeviceFilter}
     */
    @SuppressWarnings("WeakerAccess")
    @DataClass.Generated.Member
    public static final class Builder {

        private @Nullable Pattern mNamePattern;
        private @Nullable MacAddress mBssid;
        private @NonNull MacAddress mBssidMask;

        private long mBuilderFieldsSet = 0L;

        public Builder() {
        }

        /**
         * If set, only devices with {@link BluetoothDevice#getName name} matching the given regular
         * expression will be shown
         */
        @DataClass.Generated.Member
        public @NonNull Builder setNamePattern(@Nullable Pattern value) {
            checkNotUsed();
            mBuilderFieldsSet |= 0x1;
            mNamePattern = value;
            return this;
        }

        /**
         * If set, only devices with BSSID matching the given one will be shown
         */
        @DataClass.Generated.Member
        public @NonNull Builder setBssid(@Nullable MacAddress value) {
            checkNotUsed();
            mBuilderFieldsSet |= 0x2;
            mBssid = value;
            return this;
        }

        /**
         * If set, only bits at positions set in this mask, will be compared to the given
         * {@link Builder#setBssid BSSID} filter.
         */
        @DataClass.Generated.Member
        public @NonNull Builder setBssidMask(@NonNull MacAddress value) {
            checkNotUsed();
            mBuilderFieldsSet |= 0x4;
            mBssidMask = value;
            return this;
        }

        /** Builds the instance. This builder should not be touched after calling this! */
        public @NonNull WifiDeviceFilter build() {
            checkNotUsed();
            mBuilderFieldsSet |= 0x8; // Mark builder used

            if ((mBuilderFieldsSet & 0x1) == 0) {
                mNamePattern = null;
            }
            if ((mBuilderFieldsSet & 0x2) == 0) {
                mBssid = null;
            }
            if ((mBuilderFieldsSet & 0x4) == 0) {
                mBssidMask = MacAddress.BROADCAST_ADDRESS;
            }
            WifiDeviceFilter o = new WifiDeviceFilter(
                    mNamePattern,
                    mBssid,
                    mBssidMask);
            return o;
        }

        private void checkNotUsed() {
            if ((mBuilderFieldsSet & 0x8) != 0) {
                throw new IllegalStateException(
                        "This Builder should not be reused. Use a new Builder instance instead");
            }
        }
    }

    @DataClass.Generated(
            time = 1571960300742L,
            codegenVersion = "1.0.11",
            sourceFile = "frameworks/base/core/java/android/companion/WifiDeviceFilter.java",
            inputSignatures = "private @com.android.internal.util.DataClass.ParcelWith(com.android.internal.util.Parcelling.BuiltIn.ForPattern.class) @android.annotation.Nullable java.util.regex.Pattern mNamePattern\nprivate @android.annotation.Nullable android.net.MacAddress mBssid\nprivate @android.annotation.NonNull android.net.MacAddress mBssidMask\npublic @java.lang.Override boolean matches(android.net.wifi.ScanResult)\npublic @java.lang.Override java.lang.String getDeviceDisplayName(android.net.wifi.ScanResult)\npublic @java.lang.Override int getMediumType()\nclass WifiDeviceFilter extends java.lang.Object implements [android.companion.DeviceFilter<android.net.wifi.ScanResult>]\n@com.android.internal.util.DataClass(genParcelable=true, genAidl=false, genBuilder=true, genEqualsHashCode=true, genHiddenGetters=true)")
    @Deprecated
    private void __metadata() {}

}
