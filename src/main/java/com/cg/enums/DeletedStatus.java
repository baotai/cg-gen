package com.cg.enums;

public enum DeletedStatus {

    NON_DELETED((byte) 0),
    DELETED((byte) 1);

    private Byte code;

    DeletedStatus(Byte code) {
        this.code = code;
    }

    public Byte getCode() {
        return this.code;
    }
}
