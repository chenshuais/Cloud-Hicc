package com.hicc.cloud.teacher.db;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/11/2/002.
 */

public class ExceptionFile extends BmobObject {
    private BmobFile exceptionFile;
    private String phoneBrand;
    private String phoneBrandType;
    private String cpuName;
    private String androidVersion;

    public BmobFile getExceptionFile() {
        return exceptionFile;
    }

    public void setExceptionFile(BmobFile exceptionFile) {
        this.exceptionFile = exceptionFile;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneBrandType() {
        return phoneBrandType;
    }

    public void setPhoneBrandType(String phoneBrandType) {
        this.phoneBrandType = phoneBrandType;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }
}
